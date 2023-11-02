package com.example.Neobis.mapper;

import com.example.Neobis.entity.Product;
import com.example.Neobis.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product toEntity(ProductModel model);
    ProductModel toModel(Product entity);
    List<ProductModel> toModelList(List<Product> productList);
    void update(@MappingTarget Product product, ProductModel productModel);


}
