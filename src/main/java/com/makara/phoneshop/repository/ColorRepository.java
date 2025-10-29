package com.makara.phoneshop.repository;

import com.makara.phoneshop.models.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
   Optional<Color> findByIdAndIsDeletedFalse(Long id);
   List<Color> findByIsDeletedIsFalseOrderByIdDesc();
}
