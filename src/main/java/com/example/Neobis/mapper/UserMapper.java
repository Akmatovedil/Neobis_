package com.example.Neobis.mapper;

import com.example.Neobis.dto.UserDto;
import com.example.Neobis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDto model);
    UserDto toModel(User entity);
    List<UserDto> toModelList(List<User> userList);
}
