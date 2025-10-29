package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Color;
import com.makara.phoneshop.models.mapper.ColorMapper;
import com.makara.phoneshop.models.response.ColorResponse;
import com.makara.phoneshop.repository.ColorRepository;
import com.makara.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper mapper;

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFountException("color",id));
    }

    @Override
    public Color deleteId(Long id) {
        Color color = getById(id);
        color.setIsDeleted(true);
        Color delete = colorRepository.save(color);
        return delete;
    }

    @Override
    public Color updateColor(Long id, Color newColor) {
        Color color = getById(id);
        //Only update if new name is provided and different
        if(newColor.getName() !=null && !newColor.getName().equals(color.getName())){
            //check for duplicate
            if(color.equals(color.getId())){
                throw new RuntimeException("color name already exits"+ newColor.getName());
            }
            color.setName(newColor.getName());
        }
        return colorRepository.save(color);
    }

    @Override
    public List<ColorResponse> listColors() {
        return colorRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
