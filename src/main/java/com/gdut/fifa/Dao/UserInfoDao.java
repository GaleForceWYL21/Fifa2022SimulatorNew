package com.gdut.fifa.Dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdut.fifa.Entity.UserInfoEntity;
import com.gdut.fifa.Entity.Vo.PersonInfo;
import com.gdut.fifa.Entity.Vo.PersonInfo;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
    List<PersonInfo> personal(String username);

    List<UserInfoEntity> top10();
}
