package com.example.Neobis.controller;

import com.example.Neobis.model.UserModel;
import com.example.Neobis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveNew")
    public UserModel saveUser(@RequestBody UserModel userModel){
        return userService.saveUser(userModel);
    }

    @PutMapping("/update")
    public UserModel updateUser(@RequestBody UserModel userModel, @PathVariable Long id){
        return userService.updateUser(userModel, id);
    }

    @GetMapping("/findAll")
    public List<UserModel> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping("/findById")
    public UserModel findById(@RequestParam Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam Long id){
        userService.deleteUser(id);
    }
}
