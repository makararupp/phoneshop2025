package com.makara.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.makara.phoneshop.models.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
     Optional<Customer> findByIdAndIsDeletedFalse(Long id);
}
