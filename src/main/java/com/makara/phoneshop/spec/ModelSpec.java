package com.makara.phoneshop.spec;

import jakarta.persistence.criteria.*;
import org.springframework.util.StringUtils;

import com.makara.phoneshop.models.entities.Brand;
import com.makara.phoneshop.models.entities.Model;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

@Data
public class ModelSpec implements Specification<Model> {
    private final ModelFilter modelFilter;
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Model> model,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {

        if(modelFilter.getId() !=null){
            predicates.add(cb.equal(model.get("id"),modelFilter.getId() ));
        }

        if(StringUtils.hasText(modelFilter.getName())){
            predicates.add(cb.like(
                    cb.lower(model.get("name")),
                    "%" +modelFilter.getName().toLowerCase() +"%"
            ));
        }

        // Integrate BrandSpec for brand-related filters
         if(modelFilter.getBrandId() !=null || modelFilter.getBrandName() !=null){
            Join<Model, Brand> brandJoin = model.join("brand");

            if(modelFilter.getBrandId() !=null){
                predicates.add(cb.equal(brandJoin.get("id"),modelFilter.getBrandId()));
            }

            if(StringUtils.hasText(modelFilter.getBrandName())){
                predicates.add(cb.like(
                   cb.lower(brandJoin.get("name")),
                        "%"+ modelFilter.getBrandName() + "%"
                ));
            }
         }

        return cb.and(predicates.toArray(new Predicate[0]));
        
    }
}
