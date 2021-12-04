package com.example.dealershoprest.services.impl;

import com.example.dealershoprest.models.entities.Brand;
import com.example.dealershoprest.models.serviceModels.BrandServiceModel;
import com.example.dealershoprest.repositories.BrandRepository;
import com.example.dealershoprest.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    public final BrandRepository brandRepository;
    public final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<BrandServiceModel> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Brand::getName))
                .map(b -> this.modelMapper.map(b, BrandServiceModel.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Brand getBrandById(String id) {
        return this.brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Brand with ID: %s doesn't exist!", id)));
    }

    @Override
    public Brand getBrandByName(String name) {
        return this.brandRepository.getByName(name);
    }

    @Override
    public BrandServiceModel createBrand(BrandServiceModel brandServiceModel) {
        Brand brand = this.modelMapper.map(brandServiceModel, Brand.class);
        return this.modelMapper.map(this.brandRepository.save(brand), BrandServiceModel.class);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        Brand br = this.getBrandById(brand.getId());
        return this.brandRepository.save(br);
    }

    @Override
    public Brand deleteBrand(String id) {
        Brand brand = this.getBrandById(id);
        this.brandRepository.deleteById(id);

        return brand;
    }

    @Override
    public long brandsCount() {
        return this.brandRepository.count();
    }

    @Override
    public boolean brandExistByName(String name) {
        return this.brandRepository.existsByName(name);
    }

    @Override
    public void createBrandsBatch(List<Brand> brands) {
        this.brandRepository.saveAll(brands);
    }
}
