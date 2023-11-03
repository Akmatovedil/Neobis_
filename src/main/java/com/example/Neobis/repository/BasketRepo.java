package com.example.Neobis.repository;

import com.example.Neobis.entity.Basket;
import com.example.Neobis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUser(User user);
}
