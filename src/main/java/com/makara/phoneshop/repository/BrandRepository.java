package com.makara.phoneshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.makara.phoneshop.models.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>{
    List<Brand> findByNameContainingIgnoreCase(String name);
    Page<Brand> findAllByOrderByIdDesc(Pageable pageable);

}
