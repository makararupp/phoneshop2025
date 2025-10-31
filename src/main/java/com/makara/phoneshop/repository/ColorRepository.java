package com.makara.phoneshop.repository;

import com.makara.phoneshop.models.entities.Color;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>,
        JpaSpecificationExecutor<Color> {
   Optional<Color> findByIdAndIsDeletedFalse(Long id);
   List<Color> findByIsDeletedIsFalseOrderByIdDesc();
   Optional<Color> findByNameIgnoreCase(String name);
   Page<Color> findAllByOrderByIdDesc(Pageable pageable);
}
