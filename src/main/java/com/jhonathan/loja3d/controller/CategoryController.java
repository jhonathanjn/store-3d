package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.domain.Category;
import com.jhonathan.loja3d.dto.CategoryDto;
import com.jhonathan.loja3d.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> crate(@RequestBody CategoryDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(value = "id") Long id, @RequestBody CategoryDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
