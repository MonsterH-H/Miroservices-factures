package com.example.servicefactures.services;

import com.example.servicefactures.entities.Facture;
import com.example.servicefactures.entities.LigneFacture;
import com.example.servicefactures.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FactureService {

    private final FactureRepository repository;
    private final RestTemplate restTemplate;

    private final String PRODUIT_SERVICE_URL = "http://SERVICE-PRODUITS/api/produits";

    public List<Facture> getAll() {
        return repository.findAll();
    }

    public Facture getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Facture create(Facture facture, List<Map<String, Object>> lignesData) {
        facture.setDateFacture(LocalDate.now());

        // Créer les lignes de facture
        for (Map<String, Object> ligneData : lignesData) {
            Long produitId = Long.valueOf(ligneData.get("produitId").toString());
            Integer quantite = Integer.valueOf(ligneData.get("quantite").toString());

            // Récupérer les infos du produit
            Map<String, Object> produitInfo = getProduitInfo(produitId);

            // Créer la ligne
            LigneFacture ligne = new LigneFacture();
            ligne.setProduitId(produitId);
            ligne.setReference((String) produitInfo.get("reference"));
            ligne.setLibelle((String) produitInfo.get("libelle"));
            ligne.setPrixUnitaire((Double) produitInfo.get("prix"));
            ligne.setQuantite(quantite);

            facture.ajouterLigne(ligne);
        }

        // Calculer le total
        facture.calculerTotal();

        return repository.save(facture);
    }

    public Facture update(Long id, Facture facture, List<Map<String, Object>> lignesData) {
        Facture existing = getById(id);
        if (existing == null) return null;

        existing.setNumeroFacture(facture.getNumeroFacture());
        existing.setClientId(facture.getClientId());
        existing.setDescription(facture.getDescription());
        existing.setStatut(facture.getStatut());

        // Vider les anciennes lignes
        existing.getLignes().clear();

        // Ajouter les nouvelles lignes
        for (Map<String, Object> ligneData : lignesData) {
            Long produitId = Long.valueOf(ligneData.get("produitId").toString());
            Integer quantite = Integer.valueOf(ligneData.get("quantite").toString());

            Map<String, Object> produitInfo = getProduitInfo(produitId);

            LigneFacture ligne = new LigneFacture();
            ligne.setProduitId(produitId);
            ligne.setReference((String) produitInfo.get("reference"));
            ligne.setLibelle((String) produitInfo.get("libelle"));
            ligne.setPrixUnitaire((Double) produitInfo.get("prix"));
            ligne.setQuantite(quantite);
            ligne.setFacture(existing);

            existing.getLignes().add(ligne);
        }

        existing.calculerTotal();
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Facture> getByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    private Map<String, Object> getProduitInfo(Long produitId) {
        String url = String.format("%s/%d", PRODUIT_SERVICE_URL, produitId);
        
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du produit ID " + produitId + ": " + e.getMessage());
        }
    }
}