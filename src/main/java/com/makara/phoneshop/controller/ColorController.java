package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.entities.Color;
import com.makara.phoneshop.models.mapper.ColorMapper;
import com.makara.phoneshop.models.request.ColorRequest;
import com.makara.phoneshop.models.response.ColorResponse;
import com.makara.phoneshop.service.ColorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("colors")
@Slf4j
public class ColorController {
    @Autowired
    private ColorService colorService;
    @Autowired
    private ColorMapper colorMapper;

    @PostMapping
    public BaseApi<?> createColor(@Valid @RequestBody ColorRequest request) {
        Color color = colorMapper.toEntity(request);
        Color save = colorService.save(color);
        ColorResponse responseData = colorMapper.toDto(save);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("color has been saved!")
                .timestamp(LocalDateTime.now())
                .data(responseData)
                .build();
    }
    @GetMapping("{id}")
    public BaseApi<?> getByColorId(@PathVariable("id") Long colorId){
        Color color = colorService.getById(colorId);
        ColorResponse response = colorMapper.toDto(color);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("color have been found!")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    }
}
