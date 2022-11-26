package com.gdut.fifa.Dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdut.fifa.Entity.UserInfoEntity;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
}
