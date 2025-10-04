package com.makara.phoneshop.service;

import com.makara.phoneshop.models.entities.Company;

public interface CompanyService {
    Company save(Company company);
    Company getId(Long id);
}
