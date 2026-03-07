package com.jhonathan.loja3d.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String imageUrl;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String type;
}
