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

    public BasketModel addProduct(Long productId, Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Not Found"));
        Basket basket = basketRepo.findById(user.getId())
                .orElse(new Basket(user.getId(),user,null));
        Product product =productRepo.findById(productId)
                .orElseThrow(()-> new RuntimeException("Not Found"));
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
        }else
                productList = Arrays.asList(product);
            basket.setProducts(productList);
            Basket savedBasket = basketRepo.save(basket);
            return basketMapper.toModel(savedBasket);
    }
}
