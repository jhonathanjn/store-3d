package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Product.Category;
import com.jhonathan.loja3d.domain.Product.Product;
import com.jhonathan.loja3d.domain.Product.TypeProduct;
import com.jhonathan.loja3d.dto.ProductDTO;
import com.jhonathan.loja3d.exception.ResourceNotFoundException;
import com.jhonathan.loja3d.repository.CategoryRepository;
import com.jhonathan.loja3d.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll(){
        List<Product> products = productRepo.findAll();
        return products;
    }

    public Product findById(Long id){
        Product find = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return find;
    }

    public List<Product> findByCategory(Long id){
        return productRepo.findByCategoryId(id);
    }

    public List<Product> findName(String name){
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public Product creatProduct(ProductDTO dto){
        Product product = new Product();

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        product.setName(dto.getName());
        product.setImageUrl(dto.getImageUrl());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        product.setType(
                TypeProduct.valueOf(dto.getType().toUpperCase())
        );


        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductDTO dto){
        Product update = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        update.setName(dto.getName());
        update.setImageUrl(dto.getImageUrl());
        update.setDescription(dto.getDescription());
        update.setPrice(dto.getPrice());
        update.setStock(dto.getStock());

        return productRepo.save(update);
    }

    public void addCategory(Long productId, Long categoryId){
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found!"));

        product.getCategory().add(category);

        productRepo.save(product);
    }

    public void removeCategory(Long productId, Long categoryId){
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found!"));

        product.getCategory().remove(category);

        productRepo.save(product);
    }

    public void deleteById(Long id){
        Product productDeleted = productRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id)
                );;
        productRepo.delete(productDeleted);
    }


}


