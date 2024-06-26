package com.masantello.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masantello.demo.models.OrderService;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {

}
