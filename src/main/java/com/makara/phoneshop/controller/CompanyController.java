package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.mapper.CompanyMapper;
import com.makara.phoneshop.models.request.CompanyRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @PostMapping
    public BaseApi<?> crateCompany(@Valid @RequestBody CompanyRequest request){
        Company company = companyMapper.toDTO(request);
        Company save = companyService.save(company);
        CompanyResponse response = companyMapper.toEntity(save);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("company have been saved")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
