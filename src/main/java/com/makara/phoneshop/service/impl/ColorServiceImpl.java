package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.exception.ResourceNotFountException;
import com.makara.phoneshop.models.entities.Color;
import com.makara.phoneshop.repository.ColorRepository;
import com.makara.phoneshop.service.ColorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFountException("color",id));
    }
}
