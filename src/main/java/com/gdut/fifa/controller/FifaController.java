package com.gdut.fifa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdut.fifa.Dao.MatchInfoDao;
import com.gdut.fifa.Entity.MatchInfoEntity;
import com.gdut.fifa.Form.BettingForm;
import com.gdut.fifa.Form.LoginForm;
import com.gdut.fifa.Form.RegisterForm;
import com.gdut.fifa.Service.UserService;
import com.gdut.fifa.Utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fifa")
public class FifaController {
    @Autowired
    private UserService userService;

    @Autowired
    private MatchInfoDao matchInfoDao;

    @PostMapping("/register")
    public R register(@RequestBody RegisterForm form){
        int code = userService.register(form);
        if(code == 1)return R.ok(500,"注册成功");
        return R.error("用户名重复，请换一个");
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginForm form){
        int code = userService.login(form);
        if(code == 1)return R.ok(500,"登录成功");
        if(code == 0)return R.error("密码错误");
        return R.error("账户不存在，请先注册");
    }

    //get match basic info
    @GetMapping("/match")
    public R match(){
        return R.ok(userService.match());
    }

    //get match betting info
    @GetMapping("/matchInfo")
    public R matchInfo(@RequestParam("id") int id){
        int count = matchInfoDao.selectCount(
                new LambdaQueryWrapper<MatchInfoEntity>()
                        .eq(MatchInfoEntity::getId,id)
        ).intValue();
        if(count > 0)return R.ok(500,userService.matchInfo(id));
        return R.error("场次信息不存在");
    }

    //free coin for each match
    @GetMapping("/freeCoin")
    public R freeCoin(@RequestParam("id") int id, @RequestParam("username") String username){
        int count = userService.freeCoin(id,username);
        if(count == 1)return R.ok(500,"领取成功");
        return R.error("不可重复领取");
    }

    @PostMapping("/betting")
    public R betting(@RequestBody BettingForm form){
            int info = userService.betting(form);
            if(info == -1)return R.error("球赛已经开始了，无法下注");
            if(info == -2)return R.error("余额不足，请重新下注");
            return R.ok(500,info);
    }

    //show betting info
    @GetMapping("/bettingInfo")
    public R bettingInfo(@RequestParam("username")String username){
        return R.ok(500,userService.bettingInfo(username));
    }


}
