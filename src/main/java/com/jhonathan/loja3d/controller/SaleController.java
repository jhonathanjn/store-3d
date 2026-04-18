package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.service.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public ResponseEntity checkout(){
        return ResponseEntity.status(HttpStatus.OK).body(service.checkout());
    }
}
