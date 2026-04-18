package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Sale.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
