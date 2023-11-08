package com.example.Neobis.mapper;

import com.example.Neobis.dto.ProductDto;
import com.example.Neobis.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product toEntity(ProductDto model);
    ProductDto toModel(Product entity);
    List<ProductDto> toModelList(List<Product> productList);
    void update(@MappingTarget Product product, ProductDto productDto);


}
