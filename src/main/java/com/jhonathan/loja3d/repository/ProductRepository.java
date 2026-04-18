package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        // Busca produtos que tenham uma categoria específica
        List<Product> findByCategoryId(Long id);

        // Busca produtos que tenham qualquer uma das categorias informadas
        List<Product> findByNameContainingIgnoreCase(String name);
}
