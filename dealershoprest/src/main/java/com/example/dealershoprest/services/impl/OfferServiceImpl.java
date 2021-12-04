package com.example.dealershoprest.services.impl;

import com.example.dealershoprest.exception.ResourceNotFoundException;
import com.example.dealershoprest.models.bindingModels.OfferAddBindingModel;
import com.example.dealershoprest.models.bindingModels.OfferUpdateBindingModel;
import com.example.dealershoprest.models.entities.Offer;
import com.example.dealershoprest.models.entities.enums.EngineType;
import com.example.dealershoprest.models.entities.enums.TransmissionType;
import com.example.dealershoprest.models.entities.enums.VehCondition;
import com.example.dealershoprest.models.serviceModels.OfferServiceModel;
import com.example.dealershoprest.models.viewModels.OfferViewModel;
import com.example.dealershoprest.repositories.OfferRepository;
import com.example.dealershoprest.services.ModelService;
import com.example.dealershoprest.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelService modelService,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<OfferViewModel> getAllOffers() {
        return this.offerRepository.findAll()
                .stream().map(this::getOfferViewModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public OfferViewModel getOfferById(String id) throws ResourceNotFoundException {
        Offer offer = this.offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Offer with ID: %s doesn't exist!", id)));

        return getOfferViewModel(offer);
    }

    private OfferViewModel getOfferViewModel(Offer offer) {
        OfferViewModel offerViewModel = this.modelMapper.map(offer, OfferViewModel.class);

        offerViewModel.setModel(offer.getModel().getName());
        offerViewModel.setEngineType(offer.getEngine().getDisplayValue());
        offerViewModel.setTransmissionType(offer.getTransmission().getDisplayValue());
        offerViewModel.setVehicleCondition(offer.getVehCondition().getDisplayValue());

        return offerViewModel;
    }

    @Override
    public OfferServiceModel createOffer(OfferAddBindingModel offerAddBindingModel) {

        OfferServiceModel offerServiceModel = this.modelMapper.map(offerAddBindingModel, OfferServiceModel.class);
        offerServiceModel.setCreationDate(getCreationDate(offerAddBindingModel));
        offerServiceModel.setVehCondition(VehCondition.valueOf(offerAddBindingModel.getVehicleCondition().toUpperCase()));
        offerServiceModel.setEngine(EngineType.valueOf(offerAddBindingModel.getEngineType().toUpperCase()));
        offerServiceModel.setTransmission(TransmissionType.valueOf(offerAddBindingModel.getTransmissionType().toUpperCase()));

        Offer offer = this.modelMapper.map(offerServiceModel, Offer.class);
        offer.setModel(this.modelService.getModelById(offerAddBindingModel.getModel()));

        this.offerRepository.saveAndFlush(offer);

        return offerServiceModel;
    }

    private LocalDateTime getCreationDate(OfferAddBindingModel offerAddBindingModel) {
        return Instant
                .parse(offerAddBindingModel.getCreationDate())
                .atOffset(ZoneOffset.of("+2"))
                .toLocalDateTime();
    }

    @Override
    public OfferServiceModel updateOffer(String id, OfferUpdateBindingModel offerUpdate) throws ResourceNotFoundException {
        Offer offer = this.offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Offer with ID: %s doesn't exist!", id)));
        offer.setVehCondition(VehCondition.valueOf(offerUpdate.getVehicleCondition().toUpperCase()));
        offer.setEngine(EngineType.valueOf(offerUpdate.getEngineType().toUpperCase()));
        offer.setTransmission(TransmissionType.valueOf(offerUpdate.getTransmissionType().toUpperCase()));
        offer.setModel(this.modelService.getModelByName(offerUpdate.getModel()));
        offer.setMileage(offerUpdate.getMileage());
        offer.setPrice(offerUpdate.getPrice());
        offer.setYear(offerUpdate.getYear());
        offer.setImageUrl(offerUpdate.getImageUrl());

        return this.modelMapper.map(this.offerRepository.save(offer), OfferServiceModel.class);
    }

    @Override
    public void deleteOffer(String id) throws ResourceNotFoundException {
        Offer offer = this.offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Offer with ID: %s doesn't exist!", id)));
        this.offerRepository.delete(offer);
    }

    @Override
    public long offersCount() {
        return this.offerRepository.count();
    }
}
