package com.eyeieye.melody.demo.web.action;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.domain.UserAgent;
import com.eyeieye.melody.demo.service.UserService;
import com.eyeieye.melody.demo.web.validator.UserLoginValidator;

/**
 * 
 * @author fish
 * 
 */

@Controller
public class UserLoginoutAction {
	@Autowired
	private UserService userService;

	private Validator loginValidator = new UserLoginValidator();

	@RequestMapping(value = "/login.htm", method = GET)
	public void loginPage(@ModelAttribute("user") User user) {
	}

	@RequestMapping(value = "/login.htm", method = POST)
	public String login(@ModelAttribute("user") User user,
			BindingResult result, HttpSession session) {
		loginValidator.validate(user, result);
		// 错误回显
		if (result.hasErrors()) {
			return "login";
		}
		// 逻辑检查
		User u = userService.getUserByNamePasswd(user.getRealName(),
				user.getPassword());
		// 错误回显
		if (u == null) {
			return "login";
		}
		UserAgent userAgent = new UserAgent(u);
		userAgent.setLoginTime(new Date());
		session.setAttribute(UserAgent.UserAgentTag, userAgent);
		return "redirect:/user_main.htm";
	}

	@RequestMapping(value = "/logout.htm")
	public String logout(HttpSession session) {
		session.removeAttribute(UserAgent.UserAgentTag);
		return "redirect:/login.htm";
	}

	/**
	 * 偷懒一下,user main就写在loginoutAction
	 */
	@RequestMapping("/user_main.htm")
	public void main(HttpSession session, ModelMap model) {
		model.addAttribute("agent",
				session.getAttribute(UserAgent.UserAgentTag));
		// 显示首页需要的逻辑...
	}

}
