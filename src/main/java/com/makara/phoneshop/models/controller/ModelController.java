package com.makara.phoneshop.models.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.dto.ModelDTO;
import com.makara.phoneshop.models.dto.PageDTO;
import com.makara.phoneshop.models.entities.Model;
import com.makara.phoneshop.models.mapper.ModelMapper;
import com.makara.phoneshop.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService service;
    private final ModelMapper mapper;

    @PostMapping
    public BaseApi<?> save(@Valid @RequestBody ModelDTO dto){
        Model model = mapper.toModel(dto);
        Model modelDTO = service.save(model);
       ModelDTO dto1= mapper.toModelDto(modelDTO);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("model have been save successfully!")
                .timestamp(LocalDateTime.now())
                .data(dto1)
                .build();

    }

    @GetMapping("/{Id}")
     public BaseApi<?> findById(@Valid @PathVariable("Id") Long Id){
            Model model = service.getId(Id);
            ModelDTO dto = mapper.toModelDto(model);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("model have been found")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
     }

     @PutMapping("update/{id}")
     public BaseApi<?> update(@Valid @PathVariable("id") Long id, @RequestBody ModelDTO dto) {
        Model model = mapper.toModel(dto);
        Model updated = service.updateModel(id, model);
        ModelDTO modelDTO = mapper.toModelDto(updated);

         return BaseApi.builder()
                 .status(true)
                 .code(HttpStatus.OK.value())
                 .message("model have been updated successfully!")
                 .timestamp(LocalDateTime.now())
                 .data(modelDTO)
                 .build();
     }

     @DeleteMapping("/{id}")
     public BaseApi<?> delete(@Valid @PathVariable Long id){
        Model model = service.deleteById(id);
        ModelDTO dto = mapper.toModelDto(model);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("model have been updated successfully!")
                .timestamp(LocalDateTime.now())
                .data(dto)
                .build();
     }

     @GetMapping("/list-all")
    public BaseApi<?> list(){
         List<ModelDTO> dtoList = service.listAll();

         return BaseApi.builder()
                 .status(true)
                 .code(HttpStatus.OK.value())
                 .message("models have been found")
                 .timestamp(LocalDateTime.now())
                 .data(dtoList)
                 .build();
     }
     @GetMapping("/page")
    public BaseApi<?> getPagination(@Valid @RequestParam Map<String,String > params){
         Page<ModelDTO> page = service.getPagination(params);
         PageDTO dto = new PageDTO(page);
         return BaseApi.builder()
                 .status(true)
                 .code(HttpStatus.OK.value())
                 .message("models have been found")
                 .timestamp(LocalDateTime.now())
                 .data(dto)
                 .build();

     }

    @GetMapping
    public BaseApi<?> getNameJoin(@Valid @RequestParam String name){
        List<Model> models = service.filterSpecification(name);
         return BaseApi.builder()
                 .status(true)
                 .code(HttpStatus.OK.value())
                 .message("models have been found filter by name")
                 .timestamp(LocalDateTime.now())
                 .data(models)
                 .build();
     }

}
