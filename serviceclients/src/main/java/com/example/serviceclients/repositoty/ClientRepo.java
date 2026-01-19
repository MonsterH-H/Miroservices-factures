package com.example.serviceclients.repositoty;

import com.example.serviceclients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo  extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
