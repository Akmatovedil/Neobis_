package com.example.Neobis.controller;

import com.example.Neobis.model.BasketModel;
import com.example.Neobis.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;
    @PostMapping("/addProductBasket")
    public ResponseEntity addProduct(@RequestParam Long productId, @RequestParam Long userId){
        try {
            return ResponseEntity.ok(basketService.addProduct(productId, userId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
