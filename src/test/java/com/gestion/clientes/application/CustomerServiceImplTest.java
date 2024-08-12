package com.gestion.clientes.application;

import com.gestion.clientes.domain.Customer;
import com.gestion.clientes.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCliente() {

        Customer customer = new Customer();
        customer.setNombre("Braulio Pizarro");

        when(customerRepository.save(customer)).thenReturn(customer);
        Customer createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer);
        assertEquals("Braulio Pizarro", createdCustomer.getNombre());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testObtenerClientes() {
        Customer customer1 = new Customer();
        customer1.setNombre("Braulio Pizarro");

        Customer customer2 = new Customer();
        customer2.setNombre("Milton Pizarro");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getCustomers();

        assertEquals(2, customers.size());
        assertEquals("Braulio Pizarro", customers.get(0).getNombre());
        assertEquals("Milton Pizarro", customers.get(1).getNombre());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testObtenerClientePorId() {
        Customer customer = new Customer();
        customer.setNombre("Braulio Pizarro");

        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById("1");

        assertEquals("Braulio Pizarro", foundCustomer.getNombre());
        verify(customerRepository, times(1)).findById("1");
    }

    @Test
    void testObtenerClientePorId_ClienteNoEncontrado() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerById("1");
        });

        assertEquals("Customer not found", thrown.getMessage());
        verify(customerRepository, times(1)).findById("1");
    }

    @Test
    void testObtenerClientePorIdYpaisFound() {
        String id = "123";
        String pais = "Chile";
        Customer customer = new Customer();
        customer.setPais(pais);
        customer.setNombre("Braulio Pizarro");

        when(customerRepository.findByIdAndPais(id, pais)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerByIdAndCountry(id, pais);

        assertTrue(result.isPresent());
        assertEquals("Braulio Pizarro", result.get().getNombre());
        verify(customerRepository, times(1)).findByIdAndPais(id, pais);
    }

    @Test
    void testObtenerClientePorIdYpaisNotFound() {
        String id = "123";
        String pais = "Chile";

        when(customerRepository.findByIdAndPais(id, pais)).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.getCustomerByIdAndCountry(id, pais);

        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findByIdAndPais(id, pais);
    }
}