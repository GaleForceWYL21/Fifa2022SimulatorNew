package com.gdut.fifa.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdut.fifa.Dao.*;
import com.gdut.fifa.Entity.*;
import com.gdut.fifa.Form.BettingForm;
import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;
import com.gdut.fifa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity>implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MatchDao matchDao ;

    @Autowired
    private MatchInfoDao matchInfoDao ;

    @Autowired
    private FreeCoinDao freeCoinDao ;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BetInfoDao betInfoDao;

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

    @Override
    public List<MatchEntity> match() {
        //return all match basic info
        return matchDao.selectList(null);
    }

    @Override
    public List<MatchInfoEntity> matchInfo(int id) {
        //return match detail info
        return matchInfoDao.selectList(
                new LambdaQueryWrapper<MatchInfoEntity>()
                        .eq(MatchInfoEntity::getId,id)
        );
    }

    @Override
    public int freeCoin(int id, String username) {
        int count = freeCoinDao.selectCount(
                new LambdaQueryWrapper<FreeCoinEntity>()
                        .eq(FreeCoinEntity::getMid,id)
                        .eq(FreeCoinEntity::getUsername,username)
        ).intValue();
        if(count == 0){
            FreeCoinEntity freeCoinEntity = new FreeCoinEntity();
            UserInfoEntity entity = userInfoDao.selectOne(
                    new LambdaQueryWrapper<UserInfoEntity>()
                            .eq(UserInfoEntity::getUsername,username)
            );
            //set point receive info of this match to db
            freeCoinEntity.setMid(id);
            freeCoinEntity.setUsername(username);
            freeCoinDao.insert(freeCoinEntity);
            //set point to the user account
            entity.setGetFree(entity.getGetFree()+1);
            entity.setPoint(entity.getPoint()+10000);
            userInfoDao.update(entity,new LambdaQueryWrapper<UserInfoEntity>()
                    .eq(UserInfoEntity::getUsername,username));
            return 1;
        }
        //if user already receive the free coin of this match
        return 0;
    }

    @Override
    public int betting(BettingForm form) {
        Date Utime = new Date();
        Long formatTime = Utime.getTime()/1000*1000;
        Date time = new Date(formatTime);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //examine whether betting time exceeded
        MatchEntity timeEntity = matchDao.selectOne(
                new LambdaQueryWrapper<MatchEntity>()
                        .eq(MatchEntity::getId,form.getMatch())
        );
        //if time exceeded ,returns
        if(time.compareTo(timeEntity.getTime())>=0){
            return -1;
        }

        //examine if user have enough coin to bet
        UserInfoEntity moneyEntity = userInfoDao.selectOne(
                new LambdaQueryWrapper<UserInfoEntity>()
                        .eq(UserInfoEntity::getUsername,form.getUsername())
        );

        ////if balance is not enough to bet
        if(moneyEntity.getPoint() < form.getCoin()){
            return -2;
        }

        //if balance is enough to bet
        //deducting the balance
        moneyEntity.setPoint(moneyEntity.getPoint() -form.getCoin());
        userInfoDao.update(moneyEntity,new LambdaQueryWrapper<UserInfoEntity>()
                .eq(UserInfoEntity::getUsername,moneyEntity.getUsername())
        );

        //record bet info in normal
        BetInfoEntity entity = new BetInfoEntity();
        entity.setMatchid(form.getMatch());
        entity.setScoreinfo(form.getBetInfo());
        entity.setUsername(form.getUsername());
        entity.setPoint(form.getCoin());
        entity.setOdds(form.getBetPoint());
        entity.setTime(time);
        betInfoDao.insert(entity);

        //return id of a successful bet
        BetInfoEntity curEntity = betInfoDao.selectOne(new LambdaQueryWrapper<BetInfoEntity>()
                .eq(BetInfoEntity::getUsername,form.getUsername())
                .eq(BetInfoEntity::getTime,format1.format(time))
                .eq(BetInfoEntity::getMatchid,form.getMatch()));
        return curEntity.getId();
//        if(curEntity.get(12).getTime().equals(time)) return 2;
//        return 1;
    }

    @Override
    public List<BetInfoEntity> bettingInfo(String username) {
        return betInfoDao.selectList(
                new LambdaQueryWrapper<BetInfoEntity>()
                        .eq(BetInfoEntity::getUsername,username)
        );
    }
}

