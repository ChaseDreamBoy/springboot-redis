package com.sun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.sun.empty.User;
import com.sun.service.iface.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String addUser(User user) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set("testuser", gson.toJson(user));
		return "OK";
	}

	@Override
	public User queryUserByKey(String key) {
		Gson gson = new Gson();
		User user = null;
		String userJson = this.redisTemplate.opsForValue().get(key);
		if (!StringUtils.isNullOrEmpty(userJson)) {
			user = gson.fromJson(userJson, User.class);
		}
		return user;
	}

}
