package com.gdut.fifa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdut.fifa.Entity.BetInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BetInfoDao extends BaseMapper<BetInfoEntity> {
}
