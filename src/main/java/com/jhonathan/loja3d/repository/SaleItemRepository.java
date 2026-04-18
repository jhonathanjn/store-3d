package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Sale.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
