package com.example.Neobis.service;

import com.example.Neobis.entity.Basket;
import com.example.Neobis.entity.Product;
import com.example.Neobis.entity.User;
import com.example.Neobis.mapper.BasketMapper;
import com.example.Neobis.model.BasketModel;
import com.example.Neobis.repository.BasketRepo;
import com.example.Neobis.repository.ProductRepo;
import com.example.Neobis.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {
    @Autowired
    private BasketRepo basketRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BasketMapper basketMapper;

    public BasketModel addProduct(Long productId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Basket basket = basketRepo.findByUser(user)
                .orElse(new Basket());

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        List<Product> productList = basket.getProducts();
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
        basket.setProducts(productList);
        basket.setUser(user);

        Basket savedBasket = basketRepo.save(basket);

        return basketMapper.toModel(savedBasket);
    }
}
