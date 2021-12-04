package com.example.dealershoprest.services.impl;

import com.example.dealershoprest.models.entities.Model;
import com.example.dealershoprest.models.entities.enums.Category;
import com.example.dealershoprest.models.serviceModels.ModelServiceModel;
import com.example.dealershoprest.repositories.ModelRepository;
import com.example.dealershoprest.services.BrandService;
import com.example.dealershoprest.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<ModelServiceModel> getAllModels() {
        return this.modelRepository.findAll().stream()
                .map(m -> {
                    ModelServiceModel model = this.modelMapper.map(m, ModelServiceModel.class);
                    model.setBrand(m.getBrand().getName());
                    return model;
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Model getModelById(String id) {
        return this.modelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Model with ID: %s doesn't exist!", id)));
    }

    @Override
    public Model getModelByName(String name) {
        return this.modelRepository.findByName(name);
    }

    @Override
    public ModelServiceModel createModel(ModelServiceModel modelServiceModel) {

        Model model = this.modelMapper.map(modelServiceModel, Model.class);
        model.setCategory(Category.valueOf(modelServiceModel.getCategory().toUpperCase()));
        model.setBrand(this.brandService.getBrandByName(modelServiceModel.getBrand()));

        return this.modelMapper.map(this.modelRepository.save(model), ModelServiceModel.class);
    }

    @Override
    public Model updateModel(Model model) {

        return this.modelRepository.save(model);
    }

    @Override
    public Model deleteModel(String id) {
        Model model = this.getModelById(id);
        this.modelRepository.deleteById(id);
        return model;
    }

    @Override
    public long modelsCount() {
        return this.modelRepository.count();
    }

    @Override
    public boolean modelExistByName(String name) {
        return this.modelRepository.existsByName(name);
    }

    @Override
    public void createModelsBatch(List<ModelServiceModel> sampleModels) {
        sampleModels.stream()
                .sorted(Comparator.comparing(ModelServiceModel::getName))
                .forEach(m -> {
            Model model = this.modelMapper.map(m, Model.class);
            model.setCategory(Category.valueOf(m.getCategory()));
            model.setBrand(this.brandService.getBrandByName(m.getBrand()));
            this.modelRepository.save(model);
        });
    }

}
