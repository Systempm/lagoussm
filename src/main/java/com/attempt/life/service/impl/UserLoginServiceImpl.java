package com.attempt.life.service.impl;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.attempt.life.mapper.UserMapperCustom;
import com.attempt.life.po.LoginVo;
import com.attempt.life.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	
	@Autowired
	private UserMapperCustom userMapperCustom;
	
	//登录用户 
	public boolean UserLogin(LoginVo lg) throws Exception {
		// TODO Auto-generated method stub
		if(userMapperCustom!=null ) {
	 if (	userMapperCustom.UserLogin(lg)==null || userMapperCustom.UserLogin(lg)==0)
	 {
		 return false;
	 
	 
	 }
	 else 
	 {//修改登录次数加一
		 userMapperCustom.loginone(lg);
		 return true;
	 }	
	 }
	 
		return false;
	}
//注册用户 
	public String  UserRegistered(LoginVo lg) throws Exception {
		// TODO Auto-generated method stub
		//先查找一下用户  数据验证 
		if (userMapperCustom.finduser(lg)==0)
			//查找用户不存在 可以注册 
		{
		int result=userMapperCustom.UserRegistered(lg);
		
			
		if (result==0)
			 { return "{\"info\":\"注册失败\"}";
			 }
			 else {
				
				 return "{\"info\":\"注册成功\"}";
			 }	
		}
			return "{\"info\":\"注册失败\"}";
		
	}
	public boolean user_update_password(LoginVo lg) throws Exception {
		// TODO Auto-generated method stub
		
		userMapperCustom.UserUpdatepassword(lg);
		
		
		return false;
	}
	public boolean validationlg(LoginVo lg) {
		// TODO Auto-generated method stub
		if(lg.getUsername()!=""&&lg.getUsername()!=null&&lg.getPassword()!=""&&lg.getPassword()!=null) {
		return true;}
		else
		{
			return false;
		}
	}
}
