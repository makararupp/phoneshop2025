package com.makara.phoneshop.models.mapper;

import org.mapstruct.Mapper;

import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.request.CustomerRequest;
import com.makara.phoneshop.models.response.CustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest request);
    CustomerResponse toDto(Customer customer);
    
}
