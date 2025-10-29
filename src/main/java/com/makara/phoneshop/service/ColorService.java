package com.makara.phoneshop.service;

import com.makara.phoneshop.models.entities.Color;

public interface ColorService {
    Color save(Color color);
    Color getById(Long id);
}
