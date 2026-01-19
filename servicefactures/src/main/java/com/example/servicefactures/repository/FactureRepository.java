package com.example.servicefactures.repository;

import com.example.servicefactures.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);
    Facture findByNumeroFacture(String numeroFacture);
}