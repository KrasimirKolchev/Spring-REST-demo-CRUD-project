package com.example.dealershoprest.models.serviceModels;

import com.example.dealershoprest.models.entities.Model;
import com.example.dealershoprest.models.entities.enums.EngineType;
import com.example.dealershoprest.models.entities.enums.TransmissionType;
import com.example.dealershoprest.models.entities.enums.VehCondition;

import java.time.LocalDateTime;

public class OfferServiceModel extends BaseServiceModel {
    private EngineType engine;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private TransmissionType transmission;
    private Integer year;
    private Model model;
    private VehCondition vehCondition;
    private LocalDateTime creationDate;

    public OfferServiceModel() {
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public VehCondition getVehCondition() {
        return vehCondition;
    }

    public void setVehCondition(VehCondition vehCondition) {
        this.vehCondition = vehCondition;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
