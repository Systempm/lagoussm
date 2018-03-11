package com.attempt.life.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.attempt.life.po.LoginVo;

public interface CzRedisMapper {
	public List<LoginVo> adduserToRedis()throws Exception;
	public Integer findusers(@Param("username")String username)throws Exception;
}
