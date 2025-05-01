package com.tutorial.orderprocessor.repositories;

import org.springframework.stereotype.Repository;
import com.tutorial.orderprocessor.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
