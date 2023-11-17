package com.example.Neobis.service;

import com.example.Neobis.dto.ProductDto;
import com.example.Neobis.entity.Product;
import com.example.Neobis.exception.RecordNotFoundException;
import com.example.Neobis.mapper.ProductMapper;
import com.example.Neobis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto addProduct(ProductDto productDto){
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        try {
            Product productSave = productRepository.save(product);
            return ProductMapper.INSTANCE.toModel(productSave);
        }catch (RuntimeException e){
            throw new RuntimeException("Не удалось сохранить продукт в базе");
        }
    }

    public ProductDto updateProduct(ProductDto productDto, Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Продукта с таким id не существует"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return ProductMapper.INSTANCE.toModel(product);
    }

    public List<ProductDto> findAllProducts(){
        return ProductMapper.INSTANCE.toModelList(productRepository.findAll());
    }

    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Продукта с таки id не существует"));
        return ProductMapper.INSTANCE.toModel(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Продукта с таки id не существует"));
        productRepository.deleteById(product.getId());
    }





}
