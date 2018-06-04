package com.eyeieye.melody.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * ÓÃ»§Domain
 * 
 * @author zhengdd
 * @version $Id: User.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2310223785405914685L;

	public static final String name = "userAgent";

	private String realName;
	private Date birthday;
	private Integer age;
	private NativePlace nativePlace;
	private String password;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public NativePlace getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(NativePlace nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
