package com.eyeieye.melody.demo.web.resolver;

import com.eyeieye.melody.demo.web.action.login.ExtendedUser;
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
		ExtendedUser extendedUser = (ExtendedUser) nativeWebRequest.getAttribute(ExtendedUser.NAME,
                RequestAttributes.SCOPE_REQUEST);
		if(extendedUser == null){
		    extendedUser = new ExtendedUser();
        }
	    return extendedUser;
	}
}