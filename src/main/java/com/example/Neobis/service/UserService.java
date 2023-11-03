package com.example.Neobis.service;

import com.example.Neobis.entity.User;
import com.example.Neobis.exception.RecordNotFoundException;
import com.example.Neobis.mapper.UserMapper;
import com.example.Neobis.model.UserModel;
import com.example.Neobis.model.UserModel;
import com.example.Neobis.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserModel saveUser(UserModel userModel){
        User user = UserMapper.INSTANCE.toEntity(userModel);
        try {
            User userSave = userRepo.save(user);
            return UserMapper.INSTANCE.toModel(userSave);
        }catch (RuntimeException e){
            throw new RuntimeException("Не удалось сохранить пользователя в базе", e);
        }
    }

    public UserModel updateUser(UserModel userModel, Long id){
        User user = userRepo.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Пользователя с таким id не существует"));
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        return UserMapper.INSTANCE.toModel(user);
    }

    public List<UserModel> findAllUsers(){
        return UserMapper.INSTANCE.toModelList(userRepo.findAll());
    }

    public UserModel getUserById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Пользователя с таки id не существует"));
        return UserMapper.INSTANCE.toModel(user);
    }

    public void deleteUser(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Пользователя с таки id не существует"));
        userRepo.deleteById(user.getId());
    }
}
