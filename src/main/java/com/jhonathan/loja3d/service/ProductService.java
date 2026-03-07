package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Product;
import com.jhonathan.loja3d.domain.TypeProduct;
import com.jhonathan.loja3d.dto.ProductDTO;
import com.jhonathan.loja3d.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAll(){
        List<Product> products = repository.findAll();
        return products;
    }

    public Product findById(Long id){
        Product find = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return find;
    }

    public Product creatProduct(ProductDTO dto){
        Product product = new Product();

        product.setName(dto.getName());
        product.setImageUrl(dto.getImageUrl());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        product.setType(
                TypeProduct.valueOf(dto.getType().toUpperCase())
        );

        return repository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductDTO dto){
        Product update = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        update.setName(dto.getName());
        update.setImageUrl(dto.getImageUrl());
        update.setDescription(dto.getDescription());
        update.setPrice(dto.getPrice());
        update.setStock(dto.getStock());

        return repository.save(update);
    }

    public void deleteById(Long id){
        Product productDeleted = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id)
                );;
        repository.delete(productDeleted);
    }


}


