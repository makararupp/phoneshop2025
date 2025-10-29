package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.models.dto.ModelDTO;
import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Model;
import com.makara.phoneshop.models.mapper.ModelMapper;
import com.makara.phoneshop.repository.BrandRepository;
import com.makara.phoneshop.repository.ModelRepository;
import com.makara.phoneshop.service.ModelService;
import com.makara.phoneshop.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private  final ModelMapper modelMapper;

    @Override
    @Transactional
    public Model save(Model model) {
        if (model.getId() != null) {
            // Existing model, check if brand is being changed
            Model existing = modelRepository.findById(model.getId())
                    .orElseThrow(() -> new RuntimeException("Model not found"));

            if (!existing.getBrand().getId().equals(model.getBrand().getId())) {
                throw new RuntimeException("Brand cannot be changed once the model is created.");
            }
        } else {
            // New model
            if (model.getBrand() != null && model.getBrand().getId() == null) {
                model.setBrand(brandRepository.save(model.getBrand()));
            }
        }
        return modelRepository.save(model);
    }

    @Override
    public Model getId(Long id) {
         return modelRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFountException("model", id));
    }


    @Override
    public Model updateModel(Long id, Model newModel) {
        Model model = getId(id);
        model.setName(newModel.getName());

        if(model.getBrand() !=null && newModel.getBrand() != null && newModel.getBrand().getId() !=null){
            if(!model.getBrand().getId().equals(newModel.getBrand().getId())){
                model.setBrand(brandRepository.findById(newModel.getBrand().getId())
                .orElseThrow(() -> new ResourceNotFountException("Brand", id)));
            }
            
        }
        return modelRepository.save(model);
    }

    @Override
    public Model deleteById(Long id) {
        Model model = getId(id);
        model.setIsDeleted(true);
        Model save = modelRepository.save(model);
        return save;
    }

    @Override
    public List<ModelDTO> listAll() {
        return modelRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(modelMapper::toModelDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ModelDTO> getPagination(Map<String, String> params) {
        int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtils.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
        }

        int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtils.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));

        }
        Pageable pageable = PageUtils.getPageable(pageNumber,pageLimit);
        Page<ModelDTO> page = modelRepository
                .findByIsDeletedIsFalseOrderByIdDesc(pageable)
                .map(modelMapper::toModelDto);
        return page;
    }

    @Override
    public List<Model> filterSpecification(String name) {
        return modelRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }
}
