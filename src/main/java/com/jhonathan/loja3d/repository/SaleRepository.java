package com.jhonathan.loja3d.repository;

import com.jhonathan.loja3d.domain.Sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
