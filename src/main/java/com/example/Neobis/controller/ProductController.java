package com.example.Neobis.controller;

import com.example.Neobis.model.ProductModel;
import com.example.Neobis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/saveNew")
    public ResponseEntity saveProduct(@RequestBody ProductModel productModel){
        try {
            return ResponseEntity.ok(productService.saveProduct(productModel));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

    }

    @PutMapping("/update")
    public ProductModel updateProduct(@RequestBody ProductModel productModel, @PathVariable Long id){
        return productService.updateProduct(productModel, id);
    }

    @GetMapping("/findAll")
    public List<ProductModel> findAll(){
        return productService.findAllProducts();
    }

    @GetMapping("/findById")
    public ProductModel findById(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
    }
}
