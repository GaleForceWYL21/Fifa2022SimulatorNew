package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("MatchEntity")
@Data
@TableName("bet.match")
public class MatchEntity {
    @TableId(value = "matchId")
    private int id;
    private String home;
    private String away;
    private int homePoint;
    private int awayPoint;
    private String info;
    private Date time;
}
