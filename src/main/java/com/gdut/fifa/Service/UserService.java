package com.gdut.fifa.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdut.fifa.Entity.UserEntity;
import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;

public interface UserService extends IService<UserEntity> {

    String register(RegisterForm form);

    String login(LoginForm form);
}
