package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.mapper.CompanyMapper;
import com.makara.phoneshop.models.request.CompanyRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private   CompanyMapper companyMapper;

    @PostMapping
    public BaseApi<?> crateCompany(@Valid @RequestBody CompanyRequest request){
        Company company = companyMapper.toEntity(request);
        Company save = companyService.save(company);
        CompanyResponse response = companyMapper.toDTO(save);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("company have been saved")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{id}")
    public BaseApi<?> getCompanyId(@PathVariable("id") Long companyId){
         Company company = companyService.getId(companyId);
         CompanyResponse response = companyMapper.toDTO(company);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("company have been saved")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @GetMapping
    public BaseApi<?> getAllCompany(){
        List<CompanyResponse> compnies = companyService.getAll() ;
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("list of company")
                .data(compnies)
                .timestamp(LocalDateTime.now())
                .build();
    }   

    @DeleteMapping("/{id}")
    public BaseApi<?> deletedId(@PathVariable("id") Long id){
        Company byId = companyService.deletedById(id);
        CompanyResponse response = companyMapper.toDTO(byId);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("company id has been deleted")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
