package com.example.servicefactures.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroFacture;

    private LocalDate dateFacture;

    private Long clientId;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut = StatutFacture.EN_ATTENTE;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneFacture> lignes = new ArrayList<>();

    public enum StatutFacture {
        EN_ATTENTE,
        PAYEE,
        ANNULEE
    }

    // Méthode pour calculer le total
    public void calculerTotal() {
        this.montantTotal = BigDecimal.ZERO;

        for (LigneFacture ligne : this.lignes) {
            ligne.calculerMontant();
            this.montantTotal = this.montantTotal.add(ligne.getMontant());
        }
    }

    // Méthode pour ajouter une ligne
    public void ajouterLigne(LigneFacture ligne) {
        ligne.setFacture(this);
        this.lignes.add(ligne);
    }
}