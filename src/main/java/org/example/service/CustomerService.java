package org.example.service;

import org.example.mapper.CustomerMapper;
import org.example.model.Customer;
import org.example.model.DTO.CustomerResponse;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public Mono<CustomerResponse> create(Customer customer) {
        return customerRepository.save(customer)
                .map(mapper::toDTO)
                .onErrorMap(ex -> new RuntimeException("Error while saving a customer", ex));
    }

    public Flux<CustomerResponse> findAll() {
        return customerRepository.findAll().map(mapper::toDTO)
                .delayElements(Duration.ofSeconds(1))
                .onErrorResume(ex -> {
                    return Flux.error(new RuntimeException("Error while fetching all customers", ex));
                });
    }

    public Mono<CustomerResponse> findById(Long id) {
        return customerRepository.findById(id).map(mapper::toDTO)
                .onErrorMap(ex -> new RuntimeException("Error while fetching customer by id", ex));
    }

    public Flux<CustomerResponse> findAllByFirstname(String firstName) {
        return customerRepository.findAllByFirstNameIgnoreCase(firstName)
                .map(mapper::toDTO)
                .onErrorResume(ex -> {
                    return Flux.error(new RuntimeException("Error fetching customers by firstname", ex));
                });
    }

    public Mono<Void> deleteById(Long id){
        return customerRepository.deleteById(id)
                .onErrorResume(ex -> {
                    return Mono.error(new RuntimeException("Invalid id provided", ex));
                });
    }
}
