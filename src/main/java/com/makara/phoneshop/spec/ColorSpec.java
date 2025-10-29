package com.makara.phoneshop.spec;

import com.makara.phoneshop.models.entities.Color;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class ColorSpec implements Specification<Color> {
    private final  ColorFilter colorFilter;

    List<Predicate> predicates = new ArrayList<>();
    @Override
    public Predicate toPredicate(Root<Color> root,
                                 CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return null;
    }
}
