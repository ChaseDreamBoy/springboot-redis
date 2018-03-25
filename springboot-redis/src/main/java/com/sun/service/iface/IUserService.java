package com.sun.service.iface;

import com.sun.empty.User;

public interface IUserService {
	
	String addUser (User user);
	
	User queryUserByKey(String key);

}
