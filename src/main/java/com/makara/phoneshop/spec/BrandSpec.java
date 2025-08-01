package com.makara.phoneshop.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import com.makara.phoneshop.models.entities.Brand;

import java.util.ArrayList;
import java.util.List;
@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter brandFilter;
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public  Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if(brandFilter.getName() !=null){
            Predicate name = builder.like(builder.upper(brand.get("name")),
                    "%"+brandFilter.getName().toUpperCase() + "%");
            predicates.add(name);
        }
        if(brandFilter.getId() !=null){
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }
        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
