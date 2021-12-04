package com.example.dealershoprest.repositories;

import com.example.dealershoprest.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {


}
