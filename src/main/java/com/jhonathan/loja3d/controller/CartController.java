package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(service.getOrCreateCart());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long productId,
                                 @RequestParam Integer quantity) {
        return ResponseEntity.ok(service.addProduct(productId, quantity));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam Long productId) {
        return ResponseEntity.ok(service.removeProduct(productId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long productId,
                                    @RequestParam Integer quantity) {
        return ResponseEntity.ok(service.updateQuantity(productId, quantity));
    }
}
