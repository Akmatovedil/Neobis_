package com.example.Neobis.controller;

import com.example.Neobis.dto.OrderDto;
import com.example.Neobis.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.Neobis.config.SwaggerConfig.ORDER;

@Api(tags = ORDER)
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get")
    public OrderDto getOrder(){
        return orderService.getOrder();
    }

    @PostMapping("/addProductToOrder")
    public OrderDto addProduct(@RequestParam Long productId){
        return orderService.addProduct(productId);
    }

    @PostMapping("/deleteProductInOrder")
    public OrderDto deleteProduct(@RequestParam Long productId){
        return orderService.deleteProduct(productId);
    }

    @DeleteMapping("/clear")
    public OrderDto deleteBasket(){
        return orderService.clearOrder();
    }

}
