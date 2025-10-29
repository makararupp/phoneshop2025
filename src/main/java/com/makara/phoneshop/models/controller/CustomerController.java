package com.makara.phoneshop.models.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.dto.PageDTO;
import com.makara.phoneshop.models.entities.Customer;
import com.makara.phoneshop.models.mapper.CustomerMapper;
import com.makara.phoneshop.models.request.CustomerRequest;
import com.makara.phoneshop.models.response.CustomerResponse;
import com.makara.phoneshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


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
    public BaseApi<?> getCutomerId(@PathVariable("id") Long id) {
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
    public BaseApi<?> delete(@PathVariable("id") Long id){
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
    public BaseApi<?> updateCustomer(@PathVariable("id")Long id,
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
    @GetMapping("/pagination")
    public BaseApi<?> getWithPagination(@RequestParam Map<String, String> params){
        Page<CustomerResponse> page = customerService.findWithPagination(params);
        PageDTO dto = new PageDTO(page);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("customer pagination have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
    }
    @GetMapping("specFitter")
    public BaseApi<?> getAll(@RequestParam Map<String, String > params){
        List<CustomerResponse> list = customerService.getAll(params);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("customer specification fitter have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
}
