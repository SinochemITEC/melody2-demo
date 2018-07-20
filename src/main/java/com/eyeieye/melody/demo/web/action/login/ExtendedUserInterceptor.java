package com.eyeieye.melody.demo.web.action.login;

import com.eyeieye.melody.demo.cache.CacheManager;
import com.eyeieye.melos.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ExtendedUserInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    @Autowired
    private CacheManager<ExtendedUserCacheEntry> cacheManager;

    /**
     * 缓存名称
     */
    private  String cacheName = ExtendedUserCacheEntry.class.getName();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            Method handlerMethod = ((HandlerMethod)handler).getMethod();
            Extended extended = AnnotationUtils.getAnnotation(
                    handlerMethod, Extended.class);
            if(extended == null){
                extended = handler.getClass().getAnnotation(Extended.class);
            }

            if(extended == null){
                return false;
            }

            User user = (User)request.getSession().getAttribute(User.NAME);

            if(user == null) return false;

            ExtendedUserCacheEntry extendedUserCacheEntry = cacheManager.getData(cacheName,user.getUuid());

            if(extendedUserCacheEntry == null) {
                extendedUserCacheEntry = new ExtendedUserCacheEntry();
            }
            ExtendedUser exUser = extendedUserCacheEntry.getExtendedUser();
            if(exUser == null){
                exUser = new ExtendedUser();
            }
            exUser.setUser(user);

            request.getSession().setAttribute(ExtendedUser.NAME, exUser);

            return true;
        }
        return false;
    }
}
