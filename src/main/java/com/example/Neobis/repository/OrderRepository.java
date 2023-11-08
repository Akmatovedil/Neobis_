package com.example.Neobis.repository;

import com.example.Neobis.entity.Order;
import com.example.Neobis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
