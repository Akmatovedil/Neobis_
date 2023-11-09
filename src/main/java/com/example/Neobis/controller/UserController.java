package com.example.Neobis.controller;

import com.example.Neobis.dto.UserDto;
import com.example.Neobis.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Neobis.config.SwaggerConfig.USER;

@Api(tags = USER)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/findAll")
    public List<UserDto> findAll(){
        return userService.findAllUser();
    }

    @GetMapping("/findById")
    public UserDto findById(@RequestParam Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
