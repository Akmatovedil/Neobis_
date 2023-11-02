package com.example.Neobis.service;

import com.example.Neobis.entity.Product;
import com.example.Neobis.exception.ProductAlreadyExistException;
import com.example.Neobis.exception.ProductNotFoundException;
import com.example.Neobis.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product create(Product product) throws ProductAlreadyExistException {
        if (productRepo.findByName(product.getName()) != null){
            throw new ProductAlreadyExistException("Продукт с таким именем уже существует");
        }
        return productRepo.save(product);
    }

    public Product getOne(Long id) throws ProductNotFoundException{
        Product product = productRepo.findById(id).get();
        if (product == null){
            throw new ProductNotFoundException("Продукта с так id не существует");
        }
        return product;
    }

    public Product update(Product product, Long id){
        Product entity = productRepo.findById(id);
        if (entity != null){
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
            return productRepo.save(entity);
        }else
    }



}
