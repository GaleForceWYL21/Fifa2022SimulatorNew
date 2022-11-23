package com.gdut.fifa.controller;

import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;
import com.gdut.fifa.Service.UserService;
import com.gdut.fifa.Utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fifa")
public class FifaController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public R register(@RequestBody RegisterForm form){
        return R.ok(userService.register(form));
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginForm form){
        return R.ok(userService.login(form));
    }
}
