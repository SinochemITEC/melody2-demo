package com.eyeieye.melody.demo.web.action.login;

import com.eyeieye.melody.demo.cache.CacheManager;
import com.eyeieye.melody.demo.cache.ExtendedUserCacheEntry;
import com.eyeieye.melody.demo.domain.Extended;
import com.eyeieye.melody.demo.domain.ExtendedUser;
import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.domain.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ExtendedUserInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private CacheManager<ExtendedUserCacheEntry> cacheManager;

    /**
     * 缓存名称
     */
    private  String cacheName = ExtendedUserCacheEntry.class.getName();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        return true;
    }


    public void preInvoke(Method handlerMethod, Object handler,
                          ServletWebRequest webRequest){
        Extended extended = AnnotationUtils.getAnnotation(
                handlerMethod, Extended.class);
        if(extended == null){
            extended = handler.getClass().getAnnotation(Extended.class);
        }

        if(extended == null){
            return;
        }

        UserAgent userAgent = (UserAgent) webRequest.getAttribute(UserAgent.UserAgentTag, RequestAttributes.SCOPE_SESSION);
        if(userAgent == null) return;

        ExtendedUserCacheEntry extendedUserCacheEntry = cacheManager.getData(cacheName,userAgent.getUuid());

        if(extendedUserCacheEntry == null) {
            extendedUserCacheEntry = new ExtendedUserCacheEntry();
        }
        ExtendedUser exUser = extendedUserCacheEntry.getExtendedUser();
        if(exUser == null){
            exUser = new ExtendedUser();
        }
        exUser.setUser(userAgent);

        webRequest.setAttribute(ExtendedUser.NAME, exUser,RequestAttributes.SCOPE_REQUEST);


    }



}