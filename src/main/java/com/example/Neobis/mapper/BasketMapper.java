package com.example.Neobis.mapper;

import com.example.Neobis.entity.Basket;
import com.example.Neobis.model.BasketModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);
    Basket toEntity(BasketModel model);
    BasketModel toModel(Basket entity);
    List<BasketModel> toModelList(List<Basket> basketList);
}
