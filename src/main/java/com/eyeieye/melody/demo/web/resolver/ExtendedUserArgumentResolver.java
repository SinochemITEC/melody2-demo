package com.eyeieye.melody.demo.web.resolver;

import com.eyeieye.melody.demo.web.action.login.ExtendedUser;
import com.eyeieye.melody.demo.web.action.login.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component

public class ExtendedUserArgumentResolver implements HandlerMethodArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		if (methodParameter.getParameterType().equals(ExtendedUser.class)) {
			return true;
		}
		return false;


	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		//组合两个user,有一半的user存在cookie,一半的ExtendUser存在redis,但获取方式相同
		User user = (User) nativeWebRequest.getAttribute(User.NAME,
				RequestAttributes.SCOPE_SESSION);

		ExtendedUser extendedUser = (ExtendedUser) nativeWebRequest.getAttribute(ExtendedUser.NAME,
                RequestAttributes.SCOPE_SESSION);
		if(extendedUser == null){
		    extendedUser = new ExtendedUser();
        }
        extendedUser.setUser(user);

	    return extendedUser;
	}
}