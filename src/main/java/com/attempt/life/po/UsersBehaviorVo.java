package com.attempt.life.po;

public class UsersBehaviorVo {

	private String username;
	private String time ;
	private String behavior;
	
	public UsersBehaviorVo(String username ,String time ,String behavior )
	{
		this.username=username;
		this .time =time ;
		this .behavior =behavior;
		
	}
	public UsersBehaviorVo() {}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
}
