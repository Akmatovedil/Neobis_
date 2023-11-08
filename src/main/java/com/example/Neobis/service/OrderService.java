package com.example.Neobis.service;

import com.example.Neobis.dto.OrderDto;
import com.example.Neobis.entity.Order;
import com.example.Neobis.entity.Product;
import com.example.Neobis.entity.User;
import com.example.Neobis.mapper.OrderMapper;
import com.example.Neobis.repository.OrderRepository;
import com.example.Neobis.repository.ProductRepository;
import com.example.Neobis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderMapper orderMapper;

    public OrderDto addProduct(Long productId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderRepository.findByUser(user)
                .orElse(new Order());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        List<Product> productList = order.getProducts();
        if (productList != null) {
            boolean productExists = false;
            for (Product searchProduct : productList) {
                if (searchProduct.getId().equals(productId)) {
                    productExists = true;
                    break;
                }
            }
            if (!productExists)
                productList.add(product);
        } else
            productList = Arrays.asList(product);
        order.setProducts(productList);
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toModel(savedOrder);
    }
}
