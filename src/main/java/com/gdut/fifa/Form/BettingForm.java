package com.gdut.fifa.Form;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("BettingForm")
@Data
public class BettingForm {
    private String username;
    private double coin;
    private int match;
    private float betPoint;
    private String betInfo;
}
