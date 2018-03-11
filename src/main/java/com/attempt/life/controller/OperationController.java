package com.attempt.life.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attempt.life.po.LoginVo;


@Controller
public class OperationController {

	@RequestMapping("/gowelcome")
	public String gowelcome()throws Exception {
		   return "welcome";
	}
	
	@RequestMapping("/in")
	public String in()throws Exception {
		   return "login";
	}
	@RequestMapping("/user_behavior")
	public String user_behavior()throws Exception {
		   return "user_behavior";
	}

	
	
	@RequestMapping("/userupdatepassword")
	public String userupdatepassword(HttpServletRequest request,Model model ,String username)throws Exception {
		  System.out.println(username+"!!!!!!!!!!!");
		  model.addAttribute("username",username);
		   return "user_update_password";
	}
	@RequestMapping("/goerr")
	public String goerr()throws Exception {
		   return "err";
	}
	@RequestMapping("/gouser_my")
	public String gouser_my()throws Exception {
		   return "user_my";
	}
	
}
