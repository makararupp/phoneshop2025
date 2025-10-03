package com.makara.phoneshop.spec;

import com.makara.phoneshop.models.entities.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Data
public class CustomerSpecification {
    public static Specification<Customer> hasNameContaining(String name){
        return (Root<Customer>root, CriteriaQuery<?>query, CriteriaBuilder criteriaBuilder) -> {
            if(name == null || name.isEmpty()){
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+name.toLowerCase());
        };
    }
    //fitter to include only non-deleted entities
    public static Specification<Customer> isNotDeleted(){
        return (Root<Customer>root, CriteriaQuery<?>query, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.isNull(root.get("isDeleted"));
    }
    //fitter to include only entities with a null parent
    public static Specification<Customer> isNullParent(){
        return (Root<Customer>root, CriteriaQuery<?>query, CriteriaBuilder criteriaBuilder)
                -> criteriaBuilder.isNull(root.get("parent"));
    }
    //build complete specification base on dynamic parent and default fitters
    public static Specification<Customer> buildSpecification(Map<String, String> params){
        return (Root<Customer>root, CriteriaQuery<?>query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction(); //start with a "true" predicate

            //default conditions
            predicate =  criteriaBuilder.and(predicate, isNotDeleted().toPredicate(root, query, criteriaBuilder));
            predicate =  criteriaBuilder.and(predicate, isNullParent().toPredicate(root, query, criteriaBuilder));

            //Dynamic fitter base on parameters
            if(params.containsKey("name")){
                String name = params.get("name");
                Predicate namePredicate = hasNameContaining(name).toPredicate(root, query, criteriaBuilder);
                predicate = criteriaBuilder.and(predicate, namePredicate);
            }
            return  predicate;
        };
    }
}
