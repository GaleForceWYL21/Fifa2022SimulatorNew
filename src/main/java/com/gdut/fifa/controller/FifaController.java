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
        int code = userService.register(form);
        if(code == 1)return R.ok("注册成功");
        return R.error("用户名重复，请换一个");
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginForm form){
        int code = userService.login(form);
        if(code == 1)return R.ok("登录成功");
        if(code == 0)return R.error("密码错误");
        return R.error("账户不存在，请先注册");
    }
}
