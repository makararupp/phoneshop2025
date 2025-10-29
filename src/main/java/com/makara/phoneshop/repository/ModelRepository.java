package com.makara.phoneshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.makara.phoneshop.models.entities.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> ,JpaSpecificationExecutor<Model>{
    Optional<Model> findByIdAndIsDeletedFalse(Long id);
    List<Model> findByIsDeletedIsFalseOrderByIdDesc();
    Page<Model> findByIsDeletedIsFalseOrderByIdDesc(Pageable page);
    List<Model> findByNameContainingIgnoreCase(String name);

    List<Model> findByBrandId(Long brandId);
    
}
