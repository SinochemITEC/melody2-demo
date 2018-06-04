package com.eyeieye.melody.demo.service;

import com.eyeieye.melody.demo.domain.User;

/**
 * �û�Service�ӿ�
 * 
 * @author zhengdd
 * @version $Id: UserService.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public interface UserService {

	/**
	 * ע���û�����ȡע������Ϣ
	 * 
	 * @param user
	 * @return User
	 */
	public User register(User user);

	/**
	 * �����û����ƺ����Ŀ������û�
	 * 
	 * @param realName
	 * @param password
	 * @return
	 */
	public User getUserByNamePasswd(String realName, String password);

}
