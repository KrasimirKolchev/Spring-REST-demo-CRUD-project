package com.example.dealershoprest.repositories;

import com.example.dealershoprest.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Brand getByName(String name);

    boolean existsByName(String name);

}
