package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.mapper.CustomerMapper;
import com.makara.phoneshop.models.response.CustomerResponse;
import com.makara.phoneshop.repository.CustomerRepository;
import com.makara.phoneshop.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

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
    @Override
    public Customer update(Long id, Customer newCustomer) {
        return customerRepository.findByIdAndIsDeletedFalse(id)
                .map(existingCustomer  ->{
                    existingCustomer.setCustomerLocalName(newCustomer.getCustomerLocalName());
                    existingCustomer.setCustomerEngName(newCustomer.getCustomerEngName());
                    existingCustomer.setCustomerEmail(newCustomer.getCustomerEmail());
                    existingCustomer.setCustomerPhone(newCustomer.getCustomerPhone());
                    existingCustomer.setCustomerAddress(newCustomer.getCustomerAddress());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(()-> new ResourceNotFountException("Customer not found:", id));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }
}
