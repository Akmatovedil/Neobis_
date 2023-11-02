package com.example.Neobis.mapper;

import com.example.Neobis.entity.User;
import com.example.Neobis.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserModel model);
    UserModel toModel(User entity);
    List<UserModel> toModelList(List<User> userList);
}
