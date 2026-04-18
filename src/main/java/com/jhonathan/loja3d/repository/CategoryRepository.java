package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
