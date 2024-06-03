package com.masantello.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masantello.demo.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
