package com.example.securityjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/products")
    public ResponseEntity getProducts() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products")
    public ResponseEntity registerProducts() {
        return ResponseEntity.ok().build();
    }
}
