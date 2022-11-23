package com.gdut.fifa.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdut.fifa.Dao.UserDao;
import com.gdut.fifa.Entity.UserEntity;
import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;
import com.gdut.fifa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity>implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int register(RegisterForm form) {
        int count = 0;
        count = userDao.selectCount(
                new LambdaQueryWrapper<UserEntity>().
                        eq(UserEntity::getUsername,form.getUsername())
        ).intValue();
        if(count ==0){
            UserEntity entity = new UserEntity();
            entity.setNickname(form.getNickname());
            entity.setPassword(form.getPassword());
            entity.setUsername(form.getUsername());
            userDao.insert(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public int login(LoginForm form) {
        int count = userDao.selectCount(new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUsername, form.getUsername())
                .eq(UserEntity::getPassword, form.getPassword())).intValue();
        int userCount = userDao.selectCount(new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUsername, form.getUsername())).intValue();
        if (count == 1) return 1;
        if (userCount != 0) return 0;
        return 2;
    }
}

