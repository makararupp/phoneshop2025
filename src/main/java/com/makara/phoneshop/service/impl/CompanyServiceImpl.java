package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.mapper.CompanyMapper;
import com.makara.phoneshop.models.request.CustomerRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.models.response.CustomerResponse;
import com.makara.phoneshop.repository.CompanyRepository;
import com.makara.phoneshop.service.CompanyService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.makara.phoneshop.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new ResourceNotFountException("Company", id));
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

    @Override
    public Company update(Long id, Company newCompany) {
        Company company = getId(id);
        company.setCompanyLocalName(newCompany.getCompanyLocalName());
        company.setCompanyEngName(newCompany.getCompanyEngName());
        company.setCompanyEmail(newCompany.getCompanyEmail());
        company.setCompanyPhone(newCompany.getCompanyPhone());
        company.setCompanyAddress(newCompany.getCompanyAddress());
        company.setVatNumber(newCompany.getVatNumber());
        company.setImagePath(newCompany.getImagePath());
        company.setImage(newCompany.getImage());
        return companyRepository.save(company);
    }

    @Override
    public Page<CompanyResponse> findWithPagination(Map<String, String> params) {
        //Check page Limit:
        int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtils.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
        }
        //Check page numbers:
        int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtils.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
        }
        // Assuming this is inside a method like getCompanies(int pageNumber, int pageLimit)
        Pageable pageable = PageUtils.getPageable(pageNumber,pageLimit);
        Page<CompanyResponse> page = companyRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable)
                .map(companyMapper::toDTO);
        return page;
    }
}
