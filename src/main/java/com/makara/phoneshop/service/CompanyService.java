package com.makara.phoneshop.service;

import java.util.List;
import java.util.Map;

import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.request.CustomerRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.models.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CompanyService {
    Company save(Company company);
    Company getId(Long id);
    Company deletedById(Long id);
    List<CompanyResponse> getAll();
    Company update(Long id, Company newCompany);
    Page<CompanyResponse> findWithPagination(Map<String, String> params);
}
