package com.attempt.life.service;

public interface UserRegister {
public boolean CheckUsers(String username)throws Exception;
public boolean AddallUserinRedis()   throws Exception;
}
