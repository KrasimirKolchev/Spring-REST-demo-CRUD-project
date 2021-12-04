package com.example.dealershoprest.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OfferAddBindingModel {
    private String engineType;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private String transmissionType;
    private Integer year;
    private String model;
    private String vehicleCondition;
    private String creationDate;

    public OfferAddBindingModel() {
    }

    @NotBlank(message = "Engine type must be selected!")
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }


    @Length(min = 2, max = 512, message = "Add image url!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    @Positive(message = "Mileage must be above 0!")
    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    @NotNull
    @Positive(message = "Price must be above 0!")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotBlank(message = "Transmission type must be selected!")
    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @NotNull
    @Min(value = 1900, message = "Manufacturing year must be after 1900y.")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @NotBlank(message = "Model must be selected!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotBlank(message = "Condition must be selected!")
    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    @NotNull
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
