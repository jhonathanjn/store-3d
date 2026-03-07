package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
