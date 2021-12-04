package com.example.dealershoprest.web;

import com.example.dealershoprest.exception.ResourceNotFoundException;
import com.example.dealershoprest.models.bindingModels.OfferAddBindingModel;
import com.example.dealershoprest.models.bindingModels.OfferUpdateBindingModel;
import com.example.dealershoprest.models.serviceModels.ModelServiceModel;
import com.example.dealershoprest.models.serviceModels.OfferServiceModel;
import com.example.dealershoprest.models.viewModels.OfferViewModel;
import com.example.dealershoprest.services.ModelService;
import com.example.dealershoprest.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelService modelService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ResponseEntity<Collection<ModelServiceModel>> addOfferData() {
        return ResponseEntity.ok(this.modelService.getAllModels());
    }

    @PostMapping("/add")
    public ResponseEntity<OfferServiceModel> addOfferConf(@Valid @RequestBody OfferAddBindingModel offerAddBindingModel,
                                                          UriComponentsBuilder uriComponentsBuilder) {

        OfferServiceModel offer = this.offerService.createOffer(offerAddBindingModel);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/offers/details/{id}").buildAndExpand(offer.getId()).toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferViewModel> getOffer(@PathVariable(name = "id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.offerService.getOfferById(id));
    }

    @GetMapping()
    public ResponseEntity<List<OfferViewModel>> allOffers() {
        return ResponseEntity.ok(this.offerService.getAllOffers());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<OfferViewModel> offerDetails(@PathVariable(name = "id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.modelMapper.map(this.offerService.getOfferById(id), OfferViewModel.class));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OfferServiceModel> update(@PathVariable("id") String id,
                                                    @Valid @RequestBody OfferUpdateBindingModel offerUpdateBindingModel,
                                                    UriComponentsBuilder uriComponentsBuilder)
                                                    throws ResourceNotFoundException {
        OfferServiceModel offer = this.offerService.updateOffer(id, offerUpdateBindingModel);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/offers/details/{id}").buildAndExpand(offer.getId()).toUri()).build();
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> offerDelete(@PathVariable(name = "id") String id) throws ResourceNotFoundException {
        this.offerService.deleteOffer(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

}
