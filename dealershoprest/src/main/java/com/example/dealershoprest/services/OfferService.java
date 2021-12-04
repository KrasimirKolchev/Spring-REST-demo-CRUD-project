package com.example.dealershoprest.services;

import com.example.dealershoprest.exception.ResourceNotFoundException;
import com.example.dealershoprest.models.bindingModels.OfferAddBindingModel;
import com.example.dealershoprest.models.bindingModels.OfferUpdateBindingModel;
import com.example.dealershoprest.models.serviceModels.OfferServiceModel;
import com.example.dealershoprest.models.viewModels.OfferViewModel;

import java.util.ArrayList;

public interface OfferService {
    ArrayList<OfferViewModel> getAllOffers();

    OfferViewModel getOfferById(String id) throws ResourceNotFoundException;

    OfferServiceModel createOffer(OfferAddBindingModel offerAddBindingModel);

    OfferServiceModel updateOffer(String id, OfferUpdateBindingModel offer) throws ResourceNotFoundException;

    void deleteOffer(String id) throws ResourceNotFoundException;

    long offersCount();
}
