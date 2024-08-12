package com.gestion.clientes.application;

import com.gestion.clientes.domain.Customer;
import com.gestion.clientes.infrastructure.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Optional<Customer> getCustomerByIdAndCountry(String id, String pais) {
        return customerRepository.findByIdAndPais(id, pais);
    }
}
