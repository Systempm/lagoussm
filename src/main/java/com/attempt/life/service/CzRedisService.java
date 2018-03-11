package com.attempt.life.service;

import org.apache.ibatis.annotations.Param;

public interface CzRedisService {

	
	public String  AddUserinRedis(String user) throws Exception;
	
	
	public Boolean AddallUserinRedis()  throws Exception;
	
	public String Findusername(String username)throws Exception;
	public String UserBehavior() throws Exception ;
}
