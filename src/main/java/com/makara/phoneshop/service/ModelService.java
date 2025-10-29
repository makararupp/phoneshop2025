package com.makara.phoneshop.service;

import com.makara.phoneshop.models.dto.ModelDTO;
import com.makara.phoneshop.models.entities.Model;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ModelService{
    Model save(Model model);
    Model getId(Long id);
    Model updateModel (Long id, Model newModel);
    Model deleteById(Long id);
    List<ModelDTO> listAll();
    Page<ModelDTO> getPagination(Map<String, String> params);

    List<Model> filterSpecification(String name);
    List<Model> getByBrand(Long brandId);
}
