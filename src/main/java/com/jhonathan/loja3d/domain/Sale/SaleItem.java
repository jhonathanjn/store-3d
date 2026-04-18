package com.jhonathan.loja3d.domain.Sale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhonathan.loja3d.domain.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonIgnore
    private Sale sale;

    private Integer quantity;

    private BigDecimal price;

}
