package com.gdut.fifa.Form;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("RegisterForm")
@Data
public class RegisterForm {
    private String username;
    private String nickname;
    private String password;

}
