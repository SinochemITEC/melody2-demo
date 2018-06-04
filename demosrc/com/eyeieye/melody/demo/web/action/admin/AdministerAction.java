package com.eyeieye.melody.demo.web.action.admin;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eyeieye.melody.demo.access.AdminAccess;
import com.eyeieye.melody.demo.domain.Administer;
import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;
import com.eyeieye.melody.demo.web.validator.AdministerLoginvalidator;

/**
 * ϵͳ����Ա��¼�˳�action
 * 
 * @author fish
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdministerAction {

	/**
	 * ��ʾ���ֱ����action��ע�������ļ������õ�ֵ
	 */
	private @Value("${system.devMode}") boolean devMode;

	private Validator loginValidator = new AdministerLoginvalidator();

	private Random random = new Random();

	/**
	 * û��@AdminAccess��ǩ,���ʾ���������
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public void loginPage(@ModelAttribute("admin") Administer admin) throws IOException {
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login(@ModelAttribute("admin") Administer admin, BindingResult result, HttpSession session)
			throws IOException {
		loginValidator.validate(admin, result);
		// �������
		if (result.hasErrors()) {
			return "/admin/login";
		}
		// У���û�������
		AdministerAgent agent = new AdministerAgent(admin);
		// ���һ���û����е�Ȩ��
		// List<FunctionsEnum> funs = Arrays.asList(FunctionsEnum.Fun2,
		// FunctionsEnum.Fun6);
		// agent.setFunctions(funs);
		// �����������Ȩ��
		agent.setFunctions(random.nextInt(5));
		agent.setFunctions(random.nextInt(5));
		session.setAttribute(AdministerAgent.AdministerTag, agent);
		return "redirect:/admin/main.htm";
	}

	/**
	 * @AdminAccess() ��ʾ��¼�û��Ϳɷ���
	 */
	@AdminAccess()
	@RequestMapping(value = "/main.htm")
	public void main(AdministerAgent agent, ModelMap model) {
		model.addAttribute("admin", agent);
	}

	/**
	 * @AdminAccess( { FunctionsEnum.Fun1, FunctionsEnum.Fun3 })
	 * ��ʾ��fun1����fun3��admin���ܷ���
	 */
	@AdminAccess({ FunctionsEnum.Fun1, FunctionsEnum.Fun3 })
	@RequestMapping(value = "/fun1orfun3.htm")
	public void fun1orfun3() {
	}

	/**
	 * ����admin����,�Ƚ���༭ҳ��
	 */
	@RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
	public void load(@RequestParam("id") String id, ModelMap model) {
		// ����id�����ݿ���load Administer ����,��demo�ͼ򵥵�ֱ��newһ����
		Administer admin = new Administer();
		admin.setId(id);
		admin.setLoginId("�Ҳ���");
		admin.setRealName("��ʵ����һ����Ա");
		model.addAttribute("admin", admin);
	}

	@RequestMapping(value = "/edit.htm", method = RequestMethod.POST)
	public void update(@ModelAttribute("admin") Administer admin, BindingResult result) {
		// ʹ��validateУ��admin����
		// ����service����user����
	}
}
