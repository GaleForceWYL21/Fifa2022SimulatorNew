package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("BetInfoEntity")
@Data
@TableName("bet.bet_info")
public class BetInfoEntity {
    @TableId(value = "id")
    private int id;//betting unique id
    @TableField(value = "uid")
    private String username;//username
    @TableField(value = "matchId")
    private int matchid;//match id
    private float odds;//odds of this score in this match
    private String state;//whether bet is settled
    private double point;//how much coin a user bet for
    private double earn;//how much coin a user will get
    @TableField(value = "scoreInfo")
    private String scoreinfo;//odds name of this score
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;// betting date
}
