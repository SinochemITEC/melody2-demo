//package com.eyeieye.melody.demo.web.action.login;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebArgumentResolver;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@Component
//
//public class ExtendedUserArgumentResolver implements HandlerMethodArgumentResolver {
//
//
//	@Override
//	public boolean supportsParameter(MethodParameter methodParameter) {
//		if (methodParameter.getParameterType().equals(ExtendedUser.class)) {
//			return true;
//		}
//		return false;
//
//	}
//
//	@Override
//	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//		return nativeWebRequest.getAttribute(ExtendedUser.NAME,
//				RequestAttributes.SCOPE_REQUEST);
//	}
//}