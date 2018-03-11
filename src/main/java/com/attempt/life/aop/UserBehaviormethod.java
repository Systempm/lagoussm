package com.attempt.life.aop;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.attempt.life.po.AlldbPageVo;
import com.attempt.life.po.LoginVo;
import com.attempt.life.po.UserhighselectVo;
import com.attempt.life.po.UsersBehaviorVo;

import redis.clients.jedis.Jedis;

@Component
@Aspect
public class UserBehaviormethod {
	//
	@Pointcut("execution(* com.attempt.life.service.impl.*.*(..))")
	public void aspect() {
	}

	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		System.out.println("执行before.....");
	}

	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		System.out.println("执行after.....");
	}

	// 用户 登录和注册切面 1
	@Pointcut("execution(* com.attempt.life.service.impl.UserLoginServiceImpl.*(..))")
	public void userbehavior1() {
	}

	// 用户搜索行为 ！ 2
	@Pointcut("execution(* com.attempt.life.service.impl.UserOperationServiceImpl.user_highselect(..))")
	public void userbehavior2() {
	}

	// 在查询城市的时候 如果单查 城市 从缓存里面取出 ：
	// 如果没有 的话 数据库搜索 加入缓存 监控的是2 环绕
	@Around("userbehavior2()")
	public AlldbPageVo selectcityinRedis(ProceedingJoinPoint joinPoint) throws Throwable {
		UserhighselectVo uv = (UserhighselectVo) joinPoint.getArgs()[2];
		if (uv != null) {
			int page = (Integer) joinPoint.getArgs()[0];
			// 获得参数 仅仅搜索了城市 放到 hash 中 key为页数
			if (uv.onlycity()) {
				Jedis jd = new Jedis();
				// 中文转码
				System.out.println(uv.getCity());
				String cityed = "select_city_" + toGBK(uv.getCity());
				if (!jd.hexists(cityed, page + "")) {
					// 没有这个城市page list
					try {
						// 查找
						AlldbPageVo a = (AlldbPageVo) joinPoint.proceed();
						// 把a 打包成 Json 新建成这个list;
						// 添加
						jd.hset(cityed, page + "", JSON.toJSONString(a));
						System.out.println("哇  完成 切面缓存 搜索     里面没有 ");
						// 返回
						return a;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					// 有这个城市list
					String city = uv.getCity();
					// 从 REdis 里查看
					// json 解析成 object
					// JSON.parseObject("aa", new AlldbPageVo())
					// UserGroup group2 = JSON.parseObject(jsonString, UserGroup.class);
					AlldbPageVo list = JSON.parseObject(jd.hget(cityed, page + ""), AlldbPageVo.class);
					System.out.println(jd.hget(cityed, page + ""));
					list.getPageNum();
					System.out.println("哇  完成 切面缓存 搜索     里面有 直接返回 ");
					return list;

				}
			} else {
				AlldbPageVo a = (AlldbPageVo) joinPoint.proceed();
				return a;
			}

		}
		System.out.println("哇  完成 切面 没有进入if only city 搜索");
		return (AlldbPageVo) joinPoint.proceed();

	}

	// 添加用户行为 包括上面 1 和2
	@After("userbehavior1()||userbehavior2()")
	public void userbehavior(JoinPoint joinPoint) throws Exception {
		UserBehaviormethod uv = new UserBehaviormethod();
		// 获取方法名
		String behavior = joinPoint.getSignature().getName();
		// 如果用户行为是搜索 那么
		if (behavior == "user_highselect") {
			String username = (String) joinPoint.getArgs()[1];
			UserhighselectVo uservo = (UserhighselectVo) joinPoint.getArgs()[2];
			// 根据搜索条件选择 是第一次搜索还是 搜索了
			if (!uservo.isnull()) {
				uv.addusersbehavior(username, "搜索：" + uservo.toString());
				System.out.println("执行了用户搜索行为 ");
			} else {
				uv.addusersbehavior(username, "访问了搜索页面");
			}
		} else {
			// 获取用户名
			LoginVo vo = (LoginVo) joinPoint.getArgs()[0];

			System.out.println("拦截到：" + joinPoint.getSignature().getName());

			switch (behavior) {
			case "UserLogin":
				uv.addusersbehavior(vo.getUsername(), "登录");
				System.out.println("执行了用户登录行为");
				break;

			case "UserRegistered":
				uv.addusersbehavior(vo.getUsername(), "注册");
				System.out.println("执行了用户注册行为");

				break;
			}
		}
		System.out.println(" OVOVOVO！！！！");
	}

	// 用户 添加 行为 到 usersBehavior

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

	// 中文转码
	public static String toGBK(String source) {
		StringBuilder sb = new StringBuilder();
		byte[] bytes;
		try {
			System.out.println(source);
			bytes = source.getBytes("GBK");
			for (byte b : bytes) {
				sb.append("%" + Integer.toHexString((b & 0xff)).toUpperCase());
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return sb.toString();
	}

}
