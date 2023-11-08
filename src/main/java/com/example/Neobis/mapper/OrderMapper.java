package com.example.Neobis.mapper;

import com.example.Neobis.dto.OrderDto;
import com.example.Neobis.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order toEntity(OrderDto model);
    OrderDto toModel(Order entity);
    List<OrderDto> toModelList(List<Order> orderList);
}
