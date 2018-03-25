package com.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sun.empty.User;
import com.sun.service.iface.IUserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 
	 * http://localhost:8090/user/addUser?userName=xs&age=23
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("addUser")
	public String addUser (User user) {
		return this.userService.addUser(user);
	}
	
	/**
	 * 
	 * http://localhost:8090/user/queryUserByKey?key=testuser
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("queryUserByKey")
	public String queryUserByKey (String key) {
		User user = this.userService.queryUserByKey(key);
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
}
