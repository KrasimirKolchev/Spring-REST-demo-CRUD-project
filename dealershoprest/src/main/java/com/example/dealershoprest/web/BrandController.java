package com.example.dealershoprest.web;

import com.example.dealershoprest.models.bindingModels.BrandAddBindingModel;
import com.example.dealershoprest.models.serviceModels.BrandServiceModel;
import com.example.dealershoprest.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<BrandAddBindingModel> addBrandConf(
            @Valid @RequestBody BrandAddBindingModel brandAddBindingModel) {

        if (this.brandService.brandExistByName(brandAddBindingModel.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        this.brandService.createBrand(this.modelMapper
                .map(brandAddBindingModel, BrandServiceModel.class));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<List<BrandServiceModel>> allBrands() {
        return ResponseEntity.ok(this.brandService.getAllBrands());
    }
}

