package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.models.dto.BrandDTO;
import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Brand;
import com.makara.phoneshop.models.mapper.BrandMapper;
import com.makara.phoneshop.repository.BrandRepository;
import com.makara.phoneshop.service.BrandService;
import com.makara.phoneshop.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;
    @Override
    public Brand creat(Brand brand) {
        return brandRepository.save(brand);
    }
    @Override
    public Brand getById(Long Id) {
        return brandRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFountException("Brand",Id));
    }
    @Override
    public void deleteId(Long Id) {
        brandRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFountException("Brand not found with id: ",Id));
        brandRepository.deleteById(Id);
    }

    @Override
    public Brand update(Long Id, Brand updateBrand) {
        Brand brand = getById(Id);
        brand.setName(updateBrand.getName()+"U");
        //brand.setName("Makara");
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getBrands(String name) {
        return brandRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<BrandDTO> listAll() {
        return brandRepository.findAll()
                .stream()
                .map(mapper::toBrandDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BrandDTO> getWithPagination(Map<String, String> params) {
            int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
            //check page limit =2
        if(params.containsKey(PageUtils.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
        }
        //check page numbers;
        int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtils.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
        }

        Pageable pageable = PageUtils.getPageable(pageNumber, pageLimit);
        Page<BrandDTO> page = brandRepository
                .findAllByOrderByIdDesc(pageable)
                .map(mapper::toBrandDto);
        return page;
    }
}
