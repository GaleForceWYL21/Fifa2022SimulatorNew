package com.gdut.fifa.Form;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("LoginForm")
@Data
public class LoginForm {
    private String username;
    private String password;
}
