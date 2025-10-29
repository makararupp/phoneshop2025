package com.makara.phoneshop.models.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.dto.PageDTO;
import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.mapper.CompanyMapper;
import com.makara.phoneshop.models.request.CompanyRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import com.makara.phoneshop.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @PostMapping
    public BaseApi<?> crateCompany(@Valid @RequestBody CompanyRequest request) {
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
    public BaseApi<?> getCompanyId(@PathVariable("id") Long companyId) {
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
    public BaseApi<?> getAllCompany() {
        List<CompanyResponse> compnies = companyService.getAll();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("list of company")
                .data(compnies)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseApi<?> deletedId(@PathVariable("id") Long id) {
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

    @PutMapping("/{id}")
    public BaseApi<?> updatCompanies(@Valid @PathVariable Long id,
            @RequestBody CompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        Company update = companyService.update(id, company);
        CompanyResponse response = companyMapper.toDTO(update);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("companies has been updated!")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }

    @GetMapping("pagination")
    public BaseApi<?> getPagination(@RequestParam Map<String, String> params) {
        Page<CompanyResponse> responsePage = companyService.findWithPagination(params);
        PageDTO pageDTO = new PageDTO(responsePage);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("companies get the specification")
                .timestamp(LocalDateTime.now())
                .data(pageDTO)
                .build();
    }

    @PostMapping("image/{id}")
    public BaseApi<?> uploadImagePath(@PathVariable Long id,
            @RequestParam MultipartFile file)
            throws Exception {
        if (file.isEmpty()) {
            return BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Please select a file to upload.")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        // if user upload from PDF EXCEL Can't to uploads.
        if (!file.getContentType().startsWith("image")) {
            return BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Please uploda an image files only.")
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        // log.info("file saved",file);
        Company saveImage = companyService.saveImage(id, file);
        CompanyResponse response = companyMapper.toDTO(saveImage);


        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("file have been uploaded!...")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }

}
