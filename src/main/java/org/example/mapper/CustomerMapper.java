package org.example.mapper;

import org.example.model.Customer;
import org.example.model.DTO.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toDTO(Customer customer);

    Customer toEntity(CustomerResponse customerDTO);
}
