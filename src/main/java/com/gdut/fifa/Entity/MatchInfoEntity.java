package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("MatchInfoEntity")
@Data
@TableName("bet.match_info")
public class MatchInfoEntity {
    @TableId(value = "matchId")
    private int id;
    @TableField(value = "scoreId")
    private String score;
    private float point;
}
