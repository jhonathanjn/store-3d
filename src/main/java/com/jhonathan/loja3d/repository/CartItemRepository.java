package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Sale.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
