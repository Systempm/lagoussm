package com.attempt.life.mq;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class testmq {

	 
	    @Test  
	    public void testSubscribe() throws Exception{  
	        Jedis jedis = new Jedis("localhost");  
	        Listener listener = new Listener();  
	        jedis.subscribe(listener, "room1");  
	        //other code  
	        System.out.println();
	    }  
	
}
