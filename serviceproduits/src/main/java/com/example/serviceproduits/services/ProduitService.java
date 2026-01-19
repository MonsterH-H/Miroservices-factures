
package com.example.serviceproduits.services;

import com.example.serviceproduits.entities.Produit;
import com.example.serviceproduits.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository repository;

    public List<Produit> getAll() {
        return repository.findAll();
    }

    public Produit getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produit save(Produit produit) {
        return repository.save(produit);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}