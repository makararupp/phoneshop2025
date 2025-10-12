package com.makara.phoneshop.models.mapper;

import com.makara.phoneshop.models.entities.Company;
import com.makara.phoneshop.models.request.CompanyRequest;
import com.makara.phoneshop.models.response.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRequest request);
    CompanyResponse toDTO(Company company);
}
