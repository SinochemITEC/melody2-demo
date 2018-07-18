package com.eyeieye.melody.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.eyeieye.melody.demo.service.UserService;
import com.eyeieye.melody.demo.web.action.login.User;
import com.eyeieye.melos.util.StringUtil;
import com.eyeieye.melos.util.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private Map<String, User> users = new HashMap<String, User>();

	@Override
	public User register(User user) {
		user.setUuid(UUIDGenerator.generate());
		users.put(user.getRealName(), user);
		return user;
	}

	@Override
	public User getUserByNamePasswd(String realName, String password) {
		User user = users.get(realName);
		return user;
	}

	@Override
	public boolean arithmeticCheck(User user, String token) {
		String sessionToken = user.getLastToken();
		if (StringUtil.isEmpty(sessionToken)) {
			return false;
		}

		int answer = 0;
		if(sessionToken.contains("+")){
			answer = Integer.valueOf(sessionToken.split("\\+")[0]) + Integer.valueOf(sessionToken.split("\\+")[1]);
		}else if(sessionToken.contains("-")){
			answer = Integer.valueOf(sessionToken.split("-")[0]) - Integer.valueOf(sessionToken.split("-")[1]);
		}else{
			return false;
		}

		if (String.valueOf(answer).equals(token)) {
			return true;
		}
		return false;
	}

}
