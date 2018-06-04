package com.eyeieye.melody.demo.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.eyeieye.melody.demo.domain.UserAgent;

/**
 * 
 * @author fish
 * 
 */
public class UserAgentArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(UserAgent.class)) {
			UserAgent userAgent = (UserAgent) webRequest.getAttribute(UserAgent.UserAgentTag,
					RequestAttributes.SCOPE_REQUEST);
			if (userAgent != null) {
				return userAgent;
			}
		}
		return UNRESOLVED;
	}
}
