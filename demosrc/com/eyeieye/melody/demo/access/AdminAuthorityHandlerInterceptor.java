package com.eyeieye.melody.demo.access;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * 管理端权限拦截控制器，根据 @AdminAccess annotation來標記這個類的方法需要權限控制，
 * 
 * @author fish
 * 
 */
@Component
public class AdminAuthorityHandlerInterceptor extends HandlerInterceptorAdapter
		implements com.eyeieye.melos.servlet.HandlerInterceptor {

	private static final Integer placeholder = Integer.valueOf(0);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		AdministerAgent agent = (AdministerAgent) request.getSession()
				.getAttribute(AdministerAgent.AdministerTag);
		if (!pass(agent, handler)) {
			throw new AdminAccessDeniedException();
			// 到异常控制类中去处理
		}
		return true;
	}

	private Map<Object, FunctionsEnum[]> caches = new ConcurrentHashMap<Object, FunctionsEnum[]>();

	private Map<Object, Integer> noControlCaches = new ConcurrentHashMap<Object, Integer>();// 没有配置AdminAccess的method

	private boolean pass(AdministerAgent user, Object handler) {
		FunctionsEnum[] funs = null;
		funs = this.caches.get(handler);
		if (funs == null) {
			if (noControlCaches.containsKey(handler)) {
				// 没有AdminAccess 配置，允许任意访问
				return true;
			}
			AdminAccess access = findAdminAccess(handler);
			if (access == null) {
				// 没有配置AdminAccess
				noControlCaches.put(handler, placeholder);
				return true;
			}
			funs = access.value();
			this.caches.put(handler, funs);
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

	/**
	 * 作为一个demo,这里逻辑简略了
	 * 
	 * @param handler
	 * @return
	 */
	private AdminAccess findAdminAccess(Object handler) {
		if (handler instanceof HandlerMethod) {
			Method m = ((HandlerMethod) handler).getMethod();
			return AnnotationUtils.getAnnotation(m, AdminAccess.class);
		}
		return AnnotationUtils.findAnnotation(handler.getClass(),
				AdminAccess.class);
	}
}
