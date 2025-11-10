package com.makara.phoneshop.spec;

import com.makara.phoneshop.models.entities.Color;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class ColorSpecification {
    //  Case-insensitive exact match on name.
    public static Specification<Color> filterByNameExactIgnoreCase(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return null; // no filter if param is empty.
            }
            return criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("name")), // lower on db column
                    name.toLowerCase() // lower on input
            );
        };
    }
    //Case-insensitive partial match on name (LIKE with %...%).
    public static Specification<Color> filterByNameContainsIgnoreCase(String name){
        return (root, query, criteriaBuilder) -> {
          if(name ==null || name.trim().isEmpty()){
              return null;
          }
          return criteriaBuilder.equal(
                  criteriaBuilder.lower(root.get("name")),
                  "%" +name.toLowerCase() +"%" // LOWER on input + wildcards
          );
        };
    }
    // Combine with other specs if needed (e.g., AND with another filter)
    public static Specification<Color> getCombinedSpec(Map<String, String> params){
        String name = params.get("name");
        return Specification.where(filterByNameContainsIgnoreCase(name));
    }
}
