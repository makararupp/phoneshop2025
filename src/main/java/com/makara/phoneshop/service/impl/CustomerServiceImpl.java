package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.repository.CustomerRepository;
import com.makara.phoneshop.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findByIdAndIsDeletedFalse(id)
        .orElseThrow(()-> new ResourceNotFountException("customerId", id));
    }

    @Override
    public Customer deleteId(Long id) {
        Customer customer = getById(id);
        customer.setIsDeleted(true);
        Customer delete = customerRepository.save(customer);
        return delete;
    }
}
