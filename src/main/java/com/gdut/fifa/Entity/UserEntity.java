package com.gdut.fifa.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("UserEntity")
@Data
@TableName("user")
public class UserEntity implements Serializable {
    @TableId
    private String username;
    private String nickname;
    private String password;
    private String note;
}
