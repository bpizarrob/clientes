package com.gestion.clientes.infrastructure.repository;

import com.gestion.clientes.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByIdAndPais(String id, String pais);
}
