package com.makara.phoneshop.service;

import java.util.List;

import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.response.CompanyResponse;

public interface CompanyService {
    Company save(Company company);
    Company getId(Long id);
    Company deletedById(Long id);
    List<CompanyResponse> getAll();
}
