package com.example.serviceclients.services;

import com.example.serviceclients.entities.Client;
import com.example.serviceclients.repositoty.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepo repository;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}