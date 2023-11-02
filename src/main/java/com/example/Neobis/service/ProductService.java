package com.example.Neobis.service;

import com.example.Neobis.entity.Product;
import com.example.Neobis.exception.RecordNotFoundException;
import com.example.Neobis.mapper.ProductMapper;
import com.example.Neobis.model.ProductModel;
import com.example.Neobis.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductModel saveProduct(ProductModel productModel){
        Product product = ProductMapper.INSTANCE.toEntity(productModel);
        try {
            Product productSave = productRepo.save(product);
            return ProductMapper.INSTANCE.toModel(productSave);
        }catch (RuntimeException e){
            throw new RuntimeException("Не удалось сохранить продукт в базе");
        }
    }

    public ProductModel updateProduct(ProductModel productModel, Long id){
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Продукта с таким id не существует"));
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        return ProductMapper.INSTANCE.toModel(product);
    }

    public List<ProductModel> findAllProducts(){
        return ProductMapper.INSTANCE.toModelList(productRepo.findAll());
    }

    public ProductModel getProductById(Long id){
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Продукта с таки id не существует"));
        return ProductMapper.INSTANCE.toModel(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Продукта с таки id не существует"));
        productRepo.deleteById(product.getId());
    }





}
