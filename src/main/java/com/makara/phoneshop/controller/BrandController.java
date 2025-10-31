package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.dto.BrandDTO;
import com.makara.phoneshop.models.dto.ModelDTO;
import com.makara.phoneshop.models.dto.PageDTO;
import com.makara.phoneshop.models.entities.Brand;
import com.makara.phoneshop.models.entities.Model;
import com.makara.phoneshop.models.mapper.BrandMapper;
import com.makara.phoneshop.models.mapper.ModelMapper;
import com.makara.phoneshop.service.BrandService;
import com.makara.phoneshop.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequestMapping("brands")
@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper mapper;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public BaseApi<?> createBrand(@Valid @RequestBody BrandDTO dto){
        Brand brand = mapper.toEntity(dto);
        Brand saveBrand = brandService.creat(brand);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand have been saved")
                .timestamp(LocalDateTime.now())
                .data(saveBrand)
                .build();
    }

    @GetMapping("/{Id}")
    public BaseApi<?> getBrand(@Valid @PathVariable("Id") Long brandId){
        Brand brand = brandService.getById(brandId);
        BrandDTO dto = mapper.toBrandDto(brand);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Brand have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
    }

    @DeleteMapping("/{Id}")
    public BaseApi<?> delete(@Valid @PathVariable Long Id){
        brandService.deleteId(Id);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.NO_CONTENT.value())
                .message("brand have been delete")
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

    @PutMapping("/update/{Id}")
    public BaseApi<?> update(@Valid @PathVariable("Id")Long Id,
                             @RequestBody BrandDTO dto){
        Brand brand = mapper.toEntity(dto);
        Brand updateBrand = brandService.update(Id, brand);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brand have been update")
                .timestamp(LocalDateTime.now())
                .data(updateBrand)
                .build();
    }

    @GetMapping
    public BaseApi<?> getBrands(@Valid @RequestParam String name){
        List<Brand> brands = brandService.getBrands(name);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brands fetched successfully")
                .timestamp(LocalDateTime.now())
                .data(brands)
                .build();
    }

    @GetMapping("listAll")
    public BaseApi<?> listAllBrand(){
       List<BrandDTO> list= brandService.listAll();
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("brands have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }

    @GetMapping("page")
    public BaseApi<?> getPage(@Valid @RequestParam Map<String , String> params){
        Page<BrandDTO> page = brandService.getWithPagination(params);
        PageDTO dto  = new PageDTO(page);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("specification and pagination have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
    }

    @GetMapping("/nameFilter")
    public BaseApi<?> getBrands(@RequestParam Map<String, String> params){
        Page<BrandDTO> page = brandService.getWithPagination(params);
        PageDTO pageDTO = new PageDTO(page);
        return BaseApi.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("specification and pagination have been found with filter by name")
                .timestamp(LocalDateTime.now())
                .data(pageDTO)
                .build();
    }
    @GetMapping("{id}/models")
    public BaseApi<?> getModelsByBrand(@PathVariable("id") Long brandId){
        List<Model> brands = modelService.getByBrand(brandId);
        List<ModelDTO> list = brands.stream()
                .map(modelMapper::toModelDto)
                .toList();

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("models get by brand have been found")
                .timestamp(LocalDateTime.now())
                .data(list)
                .build();
    }
}
