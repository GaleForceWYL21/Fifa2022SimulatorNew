package com.gdut.fifa.Entity.Vo;


import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("PersonInfo")
@Data
public class PersonInfo {
    private String nickname;
    private double point;
    private int race;
    private double getFreeCoin;
    private double winPoint;
    private double winRate;
}
