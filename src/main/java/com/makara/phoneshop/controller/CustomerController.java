package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.mapper.CustomerMapper;
import com.makara.phoneshop.models.request.CustomerRequest;
import com.makara.phoneshop.models.response.CustomerResponse;
import com.makara.phoneshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody CustomerRequest request){
        Customer customer = customerMapper.toEntity(request);
        Customer save = customerService.create(customer);
        CustomerResponse responseData = customerMapper.toDto(save);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Customer has been saved")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    
    @GetMapping("/{id}")
    public BaseApi<?> getCutomerId(@Valid @PathVariable("id") Long id) {
        Customer customer = customerService.getById(id);
        CustomerResponse getCusid = customerMapper.toDto(customer);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("customer has been found")
                .timestamp(LocalDateTime.now())
                .data(getCusid)
                .build(); 
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> delete(@Valid @PathVariable("id") Long id){
         customerService.deleteId(id);
         return BaseApi.builder()
                .status(true)
                .code(HttpStatus.NO_CONTENT.value())
                .message("customer has been deleted")
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> updateCustomer(@Valid @PathVariable("id")Long id,
                                     @RequestBody CustomerRequest request){
        Customer customer = customerMapper.toEntity(request);
        Customer newCus = customerService.update(id, customer);
        CustomerResponse responseUpdate = customerMapper.toDto(newCus);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("customer has been updated")
                .timestamp(LocalDateTime.now())
                .data(responseUpdate)
                .build();
    }
    @GetMapping
    public BaseApi<?> getAllCustomer(){
        List<CustomerResponse> list = customerService.findAll();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("customer have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }

}
