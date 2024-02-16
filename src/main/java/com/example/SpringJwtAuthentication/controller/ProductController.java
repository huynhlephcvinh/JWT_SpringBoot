package com.example.SpringJwtAuthentication.controller;

import com.example.SpringJwtAuthentication.model.Product;
import com.example.SpringJwtAuthentication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("listAll")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(productRepository.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Product product){
        return ResponseEntity.ok().body(productRepository.save(product));
    }


}
