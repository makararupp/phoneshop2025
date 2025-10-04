package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.repository.CompanyRepository;
import com.makara.phoneshop.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getId(Long id) {
            return companyRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFountException("Company",id));
    }
}
