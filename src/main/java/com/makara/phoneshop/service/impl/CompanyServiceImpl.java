package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.mapper.CompanyMapper;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.repository.CompanyRepository;
import com.makara.phoneshop.service.CompanyService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getId(Long id) {
            return companyRepository.findByIdAndIsDeletedFalse(id)
                    .orElseThrow(()-> new ResourceNotFountException("Company",id));
    }

    @Override
    public Company deletedById(Long id) {
       Company company = getId(id);
       company.setIsDeleted(true);
       Company save = companyRepository.save(company);
       return save;
    }

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }
}
