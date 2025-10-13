package com.makara.phoneshop.service;


import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.response.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {
  Customer create(Customer customer);
  Customer getById(Long id);
  Customer deleteId(Long id);
  Customer update(Long id,Customer newCustomer);
  List<CustomerResponse> findAll();
  Page<CustomerResponse> findWithPagination(Map<String, String> params);
  List<CustomerResponse> getAll(Map<String, String > params);

} 