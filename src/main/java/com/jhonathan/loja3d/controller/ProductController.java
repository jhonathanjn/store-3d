package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.dto.ProductDTO;
import com.jhonathan.loja3d.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.creatProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
