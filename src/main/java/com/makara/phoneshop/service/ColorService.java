package com.makara.phoneshop.service;

import com.makara.phoneshop.models.entities.Color;
import com.makara.phoneshop.models.response.ColorResponse;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;


public interface ColorService {
    Color save(Color color);
    Color getById(Long id);
    Color deleteId(Long id);
    Color updateColor(Long id, Color newColor);
    List<ColorResponse> listColors();
    List<ColorResponse> findByName(String name);
    Page<ColorResponse> getWithPagination(Map<String, String> params);
    Page<ColorResponse> getAllPagination(Map<String, String> params);
}
