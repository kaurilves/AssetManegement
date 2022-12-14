package com.internship.assetmanagement.apis;

import com.internship.assetmanagement.dtos.user.AccessTokens;
import com.internship.assetmanagement.dtos.user.UserCreate;
import com.internship.assetmanagement.dtos.user.UserLogIn;
import com.internship.assetmanagement.entities.user.UserEntity;
import com.internship.assetmanagement.services.user.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @Transactional
    public AccessTokens addUser(@RequestBody UserCreate userCreate) {
        UserEntity userEntity = userService.addUser(userCreate);
        return userService.getToken(userEntity);
    }

    @PostMapping("/login")
    public AccessTokens login(@RequestBody UserLogIn userLogIn) throws Exception {
        return userService.logIn(userLogIn);
    }

    @GetMapping("/refresh")
    public AccessTokens refreshTokens(@RequestParam String token) {
        return userService.refresh(token);
    }

}
