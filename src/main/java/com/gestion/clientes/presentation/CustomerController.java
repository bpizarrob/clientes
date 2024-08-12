package com.gestion.clientes.presentation;

import com.gestion.clientes.application.CustomerService;
import com.gestion.clientes.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<Customer> crearCliente(@Valid @RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> obtenerClientes() {
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> obtenerClientePorId(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> obtenerClientePorIdYpais(
            @RequestParam("id") String id,
            @RequestParam("pais") String pais) {

        Optional<Customer> customer = customerService.getCustomerByIdAndCountry(id, pais);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Customer not found for ID: " + id + " and country: " + pais);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
