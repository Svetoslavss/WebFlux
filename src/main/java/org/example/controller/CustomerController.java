package org.example.controller;


import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.model.DTO.*;
import org.example.model.*;

import java.util.LongSummaryStatistics;
import java.util.StringJoiner;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
     Mono<CustomerResponse> create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
     Flux<CustomerResponse> getAllCustomers(){
        return customerService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Mono<CustomerResponse> fetCustomerById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{firstname}")
     Flux<CustomerResponse> getAllByFirstname(@RequestParam String firstname){
        return customerService.findAllByFirstname(firstname);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
     Mono<?> deleteCustomer(Long id){
        return customerService.deleteById(id);
    }
}
