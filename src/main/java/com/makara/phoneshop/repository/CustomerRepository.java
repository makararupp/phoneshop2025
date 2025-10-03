package com.makara.phoneshop.repository;

import java.util.List;
import java.util.Optional;

import com.makara.phoneshop.models.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.makara.phoneshop.models.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer>{
     Optional<Customer> findByIdAndIsDeletedFalse(Long id);
     List<Customer> findByIsDeletedIsFalseOrderByIdDesc();
     Page<Customer> findByIsDeletedIsFalseOrderByIdDesc(Pageable page);
     //List<Customer> findByNameContainingIgnoreCase(String name);


}
