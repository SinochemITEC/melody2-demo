package com.eyeieye.melody.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * SNA�ܹ���,�˶����ʾ��cookie�г־û��Ļ�Ա,�˶���ֻ�������ʹ�õĻ�Ա����,�����ڽṹ��һ����ƽ�ļ򵥶���
 * 
 * @author fish
 * 
 */
public class UserAgent implements Serializable {
	private static final long serialVersionUID = -1830859706881331312L;

	public static final String UserAgentTag = "userAgent";

	private String realName;// ����
	private String province;// User.NativePlace.province
	private String city;// //User.NativePlace.city
	private Date loginTime;// ��¼ʱ��

	public UserAgent() {
		super();
	}

	public UserAgent(User u) {
		super();
		this.setRealName(u.getRealName());
		this.setProvince(u.getNativePlace().getProvince());
		this.setCity(u.getNativePlace().getCity());
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	private static final char Seq = '|';

	/**
	 * ���Լ��־û���string��
	 */
	public String lieDown() {
		// ע��,��demo�����û���,�������ַ����в������ | ����ַ�,���м򵥵�ʹ����Ϊ�ָ���
		StringBuilder sb = new StringBuilder();
		sb.append(this.realName).append(Seq);
		sb.append(this.province).append(Seq);
		sb.append(this.city).append(Seq);
		sb.append(Long.toString(this.loginTime.getTime(), 32));// 32���Ʊ�ʾlong,���ȱȽ϶�
		return sb.toString();
	}
}
