package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.mapper.CustomerMapper;
import com.makara.phoneshop.models.response.CustomerResponse;
import com.makara.phoneshop.repository.CustomerRepository;
import com.makara.phoneshop.service.CustomerService;

import com.makara.phoneshop.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Override
    public Page<CustomerResponse> findWithPagination(Map<String, String> params) {
        int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
        //check page limit
        if(params.containsKey(PageUtils.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
        }
        //check  page number
        int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtils.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
        }
        // Assuming this is inside a method like getCustomers(int pageNumber, int pageLimit)
        Pageable pageable = PageUtils.getPageable(pageNumber,pageLimit);
        Page<CustomerResponse> page = customerRepository
                .findByIsDeletedIsFalseOrderByIdDesc(pageable)
                .map(customerMapper::toDto);
        return page;
    }
}
