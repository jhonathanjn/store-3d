package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.dto.ProductDTO;
import com.jhonathan.loja3d.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

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

    @GetMapping("/category/{id}")
    public ResponseEntity findByCategory(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCategory(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity findByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findName(name));
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.creatProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(id, dto));
    }

    @PutMapping("/{id}/{add}")
    public  ResponseEntity<Void> addCategory(@PathVariable(value = "id") Long id, @PathVariable(value = "add") Long add){
        service.addCategory(id, add);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/{add}")
    public  ResponseEntity<Void> removeCategory(@PathVariable(value = "id") Long id, @PathVariable(value = "add") Long add){
        service.removeCategory(id, add);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
