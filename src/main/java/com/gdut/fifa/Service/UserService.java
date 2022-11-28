package com.gdut.fifa.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdut.fifa.Entity.BetInfoEntity;
import com.gdut.fifa.Entity.MatchEntity;
import com.gdut.fifa.Entity.MatchInfoEntity;
import com.gdut.fifa.Entity.UserEntity;
import com.gdut.fifa.Form.BettingForm;
import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;

import java.util.List;

public interface UserService extends IService<UserEntity> {

    int register(RegisterForm form);

    int login(LoginForm form);

    List<MatchEntity> match();

    List<MatchInfoEntity> matchInfo(int id);

    int freeCoin(int id, String username);

    int betting(BettingForm form);


    List<BetInfoEntity> bettingInfo(String username);
}
