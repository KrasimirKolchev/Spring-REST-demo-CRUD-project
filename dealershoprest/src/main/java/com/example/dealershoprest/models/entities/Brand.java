package com.example.dealershoprest.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    private String name;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
