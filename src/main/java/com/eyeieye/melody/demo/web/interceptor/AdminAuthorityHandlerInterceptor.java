package com.eyeieye.melody.demo.web.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eyeieye.melody.demo.access.AdminAccess;
import com.eyeieye.melody.demo.access.AdminAccessDeniedException;
import com.eyeieye.melos.servlet.HandlerInterceptor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理端权限拦截控制器，根据 @AdminAccess annotation來標記這個類的方法需要權限控制，
 *
 * @author fish
 *
 */
@Component
public class AdminAuthorityHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

	private static final Integer placeholder = Integer.valueOf(0);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            Object agentObj = request.getSession().getAttribute(AdministerAgent.AdministerTag);
            AdministerAgent agent = null;
            if(agentObj != null) {
                agent = (AdministerAgent)agentObj;
            }
            if (!pass(agent,((HandlerMethod) handler).getMethod())) {
                throw new AdminAccessDeniedException();
                // 到异常控制类中去处理
            }
        }
        return true;
    }


	private Map<Method, FunctionsEnum[]> caches = new ConcurrentHashMap<Method, FunctionsEnum[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();// 没有配置AdminAccess的method

	private boolean pass(AdministerAgent user, Method handlerMethod) {
		FunctionsEnum[] funs = null;
		funs = this.caches.get(handlerMethod);
		if (funs == null) {
			if (noControlCaches.containsKey(handlerMethod)) {
				// 没有AdminAccess 配置，允许任意访问
				return true;
			}
			AdminAccess access = AnnotationUtils.getAnnotation(handlerMethod, AdminAccess.class);
			if (access == null) {
				// 没有配置AdminAccess
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			funs = access.value();
			this.caches.put(handlerMethod, funs);
		}
		if (funs.length == 0) {
			// 如果配置了缺省的AdminAccess,表示只要登录就能访问
			return user != null;
		}
		// 配置了AdminAccess
		if (user != null) {
			for (FunctionsEnum em : funs) {
				if (user.haveFunction(em)) {
					return true;
				}
			}
		}
		return false;
	}
}
