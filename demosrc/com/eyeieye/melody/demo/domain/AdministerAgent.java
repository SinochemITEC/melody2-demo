package com.eyeieye.melody.demo.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * ��̨�û���cookie�־û�����
 * 
 * @author fish
 * 
 */
public class AdministerAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9163698778206514702L;

	public static final String AdministerTag = "administerAgent";

	private String loginId;

	private BigInteger functions;// ��2����λ��¼����Ա�û���Ȩ��

	public AdministerAgent() {
		super();
	}

	public AdministerAgent(Administer admin) {
		super();
		this.loginId = admin.getLoginId();
	}

	/**
	 * ��ָ����2����λ���Ƿ���Ȩ��
	 * 
	 * @param index
	 * @return
	 */
	public boolean haveFunction(int index) {
		return this.functions.testBit(index);
	}

	/**
	 * �Ƿ�ӵ��Ȩ��
	 * 
	 * @param fe
	 * @return
	 */
	public boolean haveFunction(FunctionsEnum fe) {
		return haveFunction(fe.ordinal());
	}

	/**
	 * �����û���Ȩ��,ʵ��Ӧ�ÿ���ʹ��������Ȩ�ޱ�id��Ψһ�����������������滻�����enum.ordinal
	 * 
	 * @param funs
	 */
	public void setFunctions(List<FunctionsEnum> funs) {
		this.functions = new BigInteger("0");
		for (FunctionsEnum en : funs) {
			this.functions = this.functions.setBit(en.ordinal());
		}
	}

	public void setFunctions(int pos) {
		if (this.functions == null) {
			this.functions = new BigInteger("0");
		}
		this.functions = this.functions.setBit(pos);
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public BigInteger getFunctions() {
		return functions;
	}

	public void setFunctions(BigInteger functions) {
		this.functions = functions;
	}
}
