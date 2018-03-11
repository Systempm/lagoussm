package com.attempt.life.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attempt.life.mq.Listener;
import com.attempt.life.service.impl.CzRedisServiceImpl;

import redis.clients.jedis.Jedis;

@Controller
public class CzRedisController {
@Autowired 
private CzRedisServiceImpl czRedisServiceImpl;


/*
 * 把userlogin表的数据加载到 Redis  
 * 
*/
	@ResponseBody
	 @RequestMapping("/UserdataToRedis")
		public  Boolean UserdataToRedis()throws Exception {
		//先查找 
	czRedisServiceImpl.AddallUserinRedis();
	System.out.println("aa");
			return true;
		}
//username  用于 前台 注册时查询 是否已经有此用户名了
	
	@ResponseBody
	 @RequestMapping("/findusername")
		public  String  Findusername(String username)throws Exception {
		//先查找 
	System.out.println("查找username 是否存在");
			return czRedisServiceImpl.Findusername(username);
		}
	@ResponseBody
	@RequestMapping("/UserBehavior")
    public String UserBehavior() throws Exception{
		
		return czRedisServiceImpl.UserBehavior();
		
	}	
	
	/*@ResponseBody
	@RequestMapping(value= "/userss/{id}" ,method=RequestMethod.GET)
	public String ind(@PathVariable String id,String username,Response response)throws Exception {
		System.out.println(id);
		//id{}房间号
		System.out.println("RESTFUL风格 ");
		//调用rd 的方法 
	    Jedis jedis = new Jedis("localhost");  
		 Listener listener = new Listener();  //从线程池里拿 
	        jedis.subscribe(listener, "room1");  
		System.out.println(listener.mess);
		   return "aaa";
	}*/
	
	
	
	
	
	
	
	
	



	
	
	
}
	
	
