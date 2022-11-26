package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("UserInfoEntity")
@Data
@TableName("bet.user_info")
public class UserInfoEntity {
    private String username;
    private double point;
    private int race;
    private int getFree;
    private double winPoint;
}
