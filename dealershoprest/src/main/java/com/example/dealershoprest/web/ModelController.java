package com.example.dealershoprest.web;

import com.example.dealershoprest.models.bindingModels.ModelAddBindingModel;
import com.example.dealershoprest.models.serviceModels.BrandServiceModel;
import com.example.dealershoprest.models.serviceModels.ModelServiceModel;
import com.example.dealershoprest.services.BrandService;
import com.example.dealershoprest.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService, ModelMapper modelMapper) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ResponseEntity<Collection<BrandServiceModel>> addModel() {
        return ResponseEntity.ok(this.brandService.getAllBrands());
    }

    @PostMapping("/add")
    public ResponseEntity<ModelAddBindingModel> addModelConf(@Valid @RequestBody ModelAddBindingModel modelAddBindingModel) {
        this.modelService.createModel(this.modelMapper.map(modelAddBindingModel, ModelServiceModel.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ModelServiceModel>> getAllModels() {
        return ResponseEntity.ok(this.modelService.getAllModels());
    }
}
