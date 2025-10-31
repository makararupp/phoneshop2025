package com.makara.phoneshop.spec;

import com.makara.phoneshop.models.entities.Color;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ColorSpec implements Specification<Color> {
    private final ColorFilter colorFilter;
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Color> color,
            CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (colorFilter.getId() != null) {
            predicates.add(cb.equal(color.get("id"), colorFilter.getId()));
        }

        if (StringUtils.hasText(colorFilter.getName())) {
            predicates.add(cb.equal(cb.upper(color.get("name")), colorFilter.getName().toUpperCase()));
        }

        // if no pridicates, return and always true
        if (predicates.isEmpty()) {
            return cb.conjunction();
        }

        // Combine predicates with AND (use cb.or(...) if you want OR semantics)
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
