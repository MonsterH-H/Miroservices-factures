package com.example.serviceproduits.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produits")
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String libelle;
    private String description;
    private Double prix;
    private Integer quantiteStock;
}
