package com.example.Neobis.controller;

import com.example.Neobis.entity.Product;
import com.example.Neobis.exception.ProductAlreadyExistException;
import com.example.Neobis.exception.ProductNotFoundException;
import com.example.Neobis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity createProduct(@RequestBody Product product){
        try {
            productService.create(product);
            return ResponseEntity.ok("Продукт был добавлен");
        }catch (ProductAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity findProduct(@RequestParam Long id){
        try {
            return ResponseEntity.ok(productService.getOne(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
}
