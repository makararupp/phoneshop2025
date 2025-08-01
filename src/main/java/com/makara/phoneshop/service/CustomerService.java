package com.makara.phoneshop.service;


import com.makara.phoneshop.models.entities.Customer;

public interface CustomerService {
  Customer create(Customer customer);
  Customer getById(Long id);
  Customer deleteId(Long id);
  Customer update(Long id,Customer newCustomer);
} 