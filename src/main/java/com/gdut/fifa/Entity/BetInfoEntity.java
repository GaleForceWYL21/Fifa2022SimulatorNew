package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("BetInfoEntity")
@Data
@TableName("bet.bet_info")
public class BetInfoEntity {
    @TableField(value = "uid")
    private String username;
    @TableField(value = "matchId")
    private int matchid;
    @TableField(value = "socreId")
    private String scoreid;
    private String state;
    private double point;
    private double earn;
}
