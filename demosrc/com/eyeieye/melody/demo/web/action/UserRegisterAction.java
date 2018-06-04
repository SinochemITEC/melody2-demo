package com.eyeieye.melody.demo.web.action;

import static com.eyeieye.melody.demo.enums.ResourceType.CITY;
import static com.eyeieye.melody.demo.enums.ResourceType.PROVINCE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.demo.domain.Resource;
import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.service.ResourceService;
import com.eyeieye.melody.demo.service.UserService;
import com.eyeieye.melody.demo.web.validator.UserRegisterValidator;

/**
 * ע��Action��������Ҫ������ʾһ�������ı���ʼ����У��ʹ�����̡�
 * 
 * @author zhengdd
 * @version $Id: UserRegisterAction.java,v 1.1 2011/06/20 07:43:15 fish Exp $
 */
@Controller
public class UserRegisterAction {

	// ~ Validator
	// ======================================================================

	/** ����ʵ�ֵ�ע��Validator */
	private Validator registerValidator = new UserRegisterValidator();
	/**
	 * Spring
	 * Valangʵ�ֵ�ע��Validator����֤������ο�/WEB-INF/conf/spring/web/web-validator.xml
	 * ��idΪregisterValidator��bean��
	 */
	/*
	 * @Autowired private Validator registerValidator;
	 */

	// ~ Service
	// ========================================================================

	/** Service��ע������ */
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;

	// ~ ����ʼ��
	// =======================================================================

	/**
	 * ��ʼ���󶨡�
	 * <p>
	 * 
	 * @InitBinder���ע�����ڰ󶨸���PropertyEditor���������ύ�������ַ����� 
	 *                                                   ����ע�ᵽWebDataBinder�е�PropertyEditorת��Ϊ�����ĳһ�����
	 *                                                   ��
	 *                                                   <p>
	 *                                                   ���磺CustomDateEditor ������
	 *                                                   "yyyy-MM-dd"
	 *                                                   ��ʽ���ַ���ת��ΪData���͡�
	 *                                                   <p>
	 *                                                   <b>ע�⣺<br>
	 *                                                   
	 *                                                   1���÷�������Controller��ʼ����ʱ��ִ��һ��
	 *                                                   ��<br>
	 *                                                   
	 *                                                   2������и�ע��ķ�����α���Ҫ��WebDataBinder
	 *                                                   ��</b>
	 * 
	 * @param binder
	 */
	@SuppressWarnings("unused")
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		// ע�����ڸ�ʽ������: yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * ��ʼ��ʡ�����ݡ�
	 * <p>
	 * 
	 * @ModelAttribute���ע�����ڷ��������ϣ����ᱻ�÷���������Controller������ 
	 *                                                   ��ע��@RequestMapping�ķ����Զ����ã�
	 *                                                   ���ҽ�����ֵ����@ModelAttribute��ָ����
	 *                                                   value��ӵ�Model�С�
	 * 
	 * @return List<Resource>
	 */
	@SuppressWarnings("unused")
	@ModelAttribute("provinces")
	private List<Resource> buildProvinces() {
		return resourceService.getResourcesByType(PROVINCE);
	}

	/**
	 * ��ʼ���������ݡ�
	 * 
	 * @return List<Resource>
	 */
	@SuppressWarnings("unused")
	@ModelAttribute("cities")
	private List<Resource> buildCities() {
		return resourceService.getResourcesByType(CITY);
	}

	/**
	 * ע���û���Ϣ��ʼ����
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/register.htm", method = GET)
	public void registerInit(@ModelAttribute("user") User user) {

		// TODO; ���������Զ���ĳ�ʼ������
	}

	// ~ ����֤������
	// ==================================================================

	/**
	 * ע���û���Ϣ��
	 * 
	 * @param user
	 * @param result
	 * @return String
	 */

	@RequestMapping(value = "/register.htm", method = POST)
	public String register(@ModelAttribute("user") User user,
			BindingResult result) {

		// У��ע���û���Ϣ
		registerValidator.validate(user, result);
		// �������
		if (result.hasErrors()) {
			return "register";
		}

		// ע���û�
		userService.register(user);
		// TODO; ����������ҵ���߼�

		// �ɹ���ת
		return "success";
	}

}
