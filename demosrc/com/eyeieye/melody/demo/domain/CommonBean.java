package com.eyeieye.melody.demo.domain;

import java.util.Locale;

import com.eyeieye.melos.web.locale.velocity.LocaleRender;

/**
 * ��ʾ�����ҳ�����Զ����ݵ�ǰ�����۵�locale���
 * 
 * @author fish
 * 
 */
public class CommonBean {
	// ��������
	private String nameGb;

	// Ӣ������
	private String nameEn;

	/**
	 * �õ��Զ�����İ�װ��,demo��ֻ����������,û�����ֹ���
	 * 
	 * @return
	 */
	public LocaleRender getNameLocale() {
		return new LocaleRender() {
			@Override
			public String getByLocale(Locale locale) {
				// �����ǰ����ΪӢ�������nameEn
				if (locale.getLanguage().equals("en")) {
					return nameEn;
				}
				// ����Ǻ���,����ȱʡҲ���������
				return nameGb;
			}
		};
	}

	public String getNameGb() {
		return nameGb;
	}

	public void setNameGb(String nameGb) {
		this.nameGb = nameGb;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
}
