package com.example.dealershoprest.models.bindingModels;

import org.hibernate.validator.constraints.Length;

public class BrandAddBindingModel {
    private String name;

    public BrandAddBindingModel() {
    }

    @Length(min = 3, max = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
