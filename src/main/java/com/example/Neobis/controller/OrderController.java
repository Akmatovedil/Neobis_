package com.example.Neobis.controller;

import com.example.Neobis.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.Neobis.config.SwaggerConfig.ORDER;

@Api(tags = ORDER)
@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addProductBasket")
    public ResponseEntity addProduct(@RequestParam Long productId, @RequestParam Long userId){
        try {
            return ResponseEntity.ok(orderService.addProduct(productId, userId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
