package com.gestion.clientes.application;

import com.gestion.clientes.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getCustomers();
    Customer getCustomerById(String id); // cliente por pais
    Optional<Customer> getCustomerByIdAndCountry(String id, String pais); // Método adicional
}
