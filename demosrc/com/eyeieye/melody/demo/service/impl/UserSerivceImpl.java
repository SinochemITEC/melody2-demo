package com.eyeieye.melody.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.service.UserService;

@Service("userService")
public class UserSerivceImpl implements UserService {

	private Map<String, User> users = new HashMap<String, User>();

	public User register(User user) {
		return users.put(user.getRealName(), user);
	}

	public User getUserByNamePasswd(String realName, String password) {
		return users.get(realName);
	}

}
