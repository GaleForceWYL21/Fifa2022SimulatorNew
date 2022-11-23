package com.gdut.fifa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdut.fifa.Entity.MatchEntity;
import com.gdut.fifa.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MatchDao extends BaseMapper<MatchEntity> {
}
