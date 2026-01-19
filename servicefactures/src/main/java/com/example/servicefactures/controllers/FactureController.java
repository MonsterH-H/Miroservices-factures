package com.example.servicefactures.controllers;

import com.example.servicefactures.entities.Facture;
import com.example.servicefactures.services.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService service;

    @GetMapping
    public List<Facture> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Facture getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Facture create(@RequestBody Map<String, Object> request) {

        // Extraire les données de la requête
        Facture facture = new Facture();
        facture.setNumeroFacture((String) request.get("numeroFacture"));
        facture.setClientId(Long.valueOf(request.get("clientId").toString()));
        facture.setDescription((String) request.get("description"));

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> lignes = (List<Map<String, Object>>) request.get("lignes");

        return service.create(facture, lignes);
    }

    @PutMapping("/{id}")
    public Facture update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Facture facture = new Facture();
        facture.setNumeroFacture((String) request.get("numeroFacture"));
        facture.setClientId(Long.valueOf(request.get("clientId").toString()));
        facture.setDescription((String) request.get("description"));

        if (request.get("statut") != null) {
            facture.setStatut(Facture.StatutFacture.valueOf(request.get("statut").toString()));
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> lignes = (List<Map<String, Object>>) request.get("lignes");
        return service.update(id, facture, lignes);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Facture> getByClient(@PathVariable Long clientId) {
        return service.getByClientId(clientId);
    }
}