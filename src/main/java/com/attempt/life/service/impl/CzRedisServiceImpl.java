package com.attempt.life.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.attempt.life.mapper.CzRedisMapper;
import com.attempt.life.mapper.UserloginMapper;
import com.attempt.life.mq.Listener;
import com.attempt.life.po.LoginVo;
import com.attempt.life.po.UsersBehaviorVo;
import com.attempt.life.service.CzRedisService;

import redis.clients.jedis.Jedis;

@Service
public class CzRedisServiceImpl implements CzRedisService {

	@Autowired
	private CzRedisMapper czRedisMapper;

	public String AddUserinRedis(String User) throws Exception {

		System.out.println("服务正常 ");
		/*
		 * List l= czRedisMapper.adduserToRedis(); for (Iterator iterator =
		 * l.iterator(); iterator.hasNext();) {
		 * 
		 * Object object = (Object) iterator.next(); System.out.println(object);
		 */

		return "b";
	}

	// 把username 放到 redis中

	public Boolean AddallUserinRedis() throws Exception {
		// TODO Auto-generated method stub
		List<LoginVo> li = czRedisMapper.adduserToRedis();
		Jedis j = new Jedis();
		String a = j.set("userlist", JSON.toJSONString(li));
		System.out.println(a);
		return true;
	}

	// 查看用户是不是存在
	public String Findusername(String username) throws Exception {
		// TODO Auto-generated method stub

		int num = czRedisMapper.findusers(username);

		if (num == 0) {
			return "{\"info\": \"true\"}";
		}

		return "{\"info\": \"false\"}";// 不可用

	}
	// 包装 usersbehavior行为 加入到Redis

	public boolean addusersbehavior(String username, String behavior) throws Exception {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		UsersBehaviorVo ub = new UsersBehaviorVo(username, df.format(day), behavior);
		String s = JSON.toJSONString(ub);
		Jedis j = new Jedis();
		// list 里面的数据大于45 删除
		if (j.llen("userbehavior") > 45) {
			for (int i = 0; i < 10; i++) {
				j.lpop("userehavior");
			}
		}
		long a = j.lpush("userbehavior", s);
		System.out.println("用户行为插入,:" + a);
		return false;
	}

	// 获取 用户行为 在REdis 中 并且打包成json
	public String UserBehavior() throws Exception {
		// TODO Auto-generated method stub
		Jedis jd = new Jedis();
		List l = jd.lrange("userbehavior", 0, -1);
		return JSON.toJSONString(l);
	}
	//进入redis 的订阅 
	 public void getRidesmq(String room ,String username) {
		 
		  Jedis jedis = new Jedis("localhost");  
	        Listener listener = new Listener();  
	        jedis.subscribe(listener, room);  
	 }

}
