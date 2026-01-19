package com.example.servicefactures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "lignes_facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneFacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    private Long produitId;

    private String reference;
    private String libelle;
    private Double prixUnitaire;

    private Integer quantite;

    @Column(precision = 10, scale = 2)
    private BigDecimal montant;

    // Méthode pour calculer le montant
    public void calculerMontant() {
        this.montant = BigDecimal.valueOf(this.prixUnitaire)
                .multiply(BigDecimal.valueOf(this.quantite));
    }
}