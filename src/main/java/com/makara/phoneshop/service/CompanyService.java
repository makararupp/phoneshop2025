package com.makara.phoneshop.service;

import java.util.List;
import java.util.Map;

import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.response.CompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CompanyService {
    Company save(Company company);
    Company getId(Long id);
    Company deletedById(Long id);
    List<CompanyResponse> getAll();
    Company update(Long id, Company newCompany);
    Page<CompanyResponse> findWithPagination(Map<String, String> params);
    Company saveImage(Long id, MultipartFile file) throws Exception;
}
