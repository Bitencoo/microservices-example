package com.microservicesexample.orderservice.repository;

import com.microservicesexample.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findByOrderNumber(String orderNumber);
}
