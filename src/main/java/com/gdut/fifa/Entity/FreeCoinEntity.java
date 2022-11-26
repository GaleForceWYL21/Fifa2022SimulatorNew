package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("FreeCoinEntity")
@Data
@TableName("bet.free_coin")
public class FreeCoinEntity {

    @TableField(value = "matchId")
    private int mid;

    private String username;
}
