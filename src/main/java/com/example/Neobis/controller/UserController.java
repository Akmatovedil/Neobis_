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
    private final UserService userServiceImpl;
    @PostMapping("/save")
    public UserDto saveUser(@RequestBody UserDto userDto){

        return userServiceImpl.saveUser(userDto);
    }
    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable long id){
        return userServiceImpl.updateUser(userDto, id);
    }
    @GetMapping("/findAll")
    public List<UserDto> findAll(){
        return userServiceImpl.findAllUser();
    }
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userServiceImpl.deleteUser(id);
    }

}
