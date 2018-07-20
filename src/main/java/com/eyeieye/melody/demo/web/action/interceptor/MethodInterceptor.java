package com.eyeieye.melody.demo.web.action.interceptor;

import com.eyeieye.melos.servlet.HandlerInterceptor;
import com.eyeieye.melos.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class MethodInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MethodInterceptor.class);

    public void preInvoke(Method handlerMethod, Object handler, ServletWebRequest webRequest) {
        preAction(handler);

    }

    public void postInvoke(Method handlerMethod, Object handler, ServletWebRequest webRequest, ModelAndView mav) {
        postAction(handler,mav);
    }

    public void preAction(Object handler) {
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod)handler).getMethod();
            StringBuffer sf = new StringBuffer();

            sf.append("\n")
                    .append("        *-*-*-*-*-*-*-*-*-*-*-*[方法级别拦截器]*-*-*-*-*-*-*-*-*-*-*-*")
                    .append("\n")
                    .append("        *-*-*-*-*-*-*-*-*-*-*-*-[方法执行开始]-*-*-*-*-*-*-*-*-*-*-*-*")
                    .append("\n")
                    .append("        *                                                           *")
                    .append("\n")
                    .append("        *                                                           *")
                    .append("\n")
                    .append("        *                       方法名                              *")
                    .append("\n")
                    .append(method.getName())
                    .append("\n")
                    .append("        *                       方法参数类型                         *")
                    .append("\n")
                    .append(ArrayUtil.toString(method.getParameterTypes()))
                    .append("\n")
                    .append("        *                     方法返回值类型                         *")
                    .append("\n")
                    .append(method.getReturnType().getName())
                    .append("\n")
                    .append("        *                        所在类名                            *")
                    .append("\n")
                    .append(handler.getClass().toString())
                    .append("\n");
            logger.debug(sf.toString());
        }
    }

    public void postAction(Object handler, ModelAndView mav){
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod)handler).getMethod();
            StringBuffer sf = new StringBuffer();

            sf.append("\n").append("        *                     返回页面的参数                         *").append("\n");

            if (mav == null || mav.getModelMap() == null) {
                sf.append("null").append("\n");
            } else {
                for (Map.Entry entry : mav.getModelMap().entrySet()) {
                    sf.append(entry.getKey() + " : " + entry.getValue()).append("\n");
                }
            }

            sf.append("        *                                                           *").append("\n")
                    .append("        *                                                           *").append("\n")
                    .append("        *                                                           *").append("\n")
                    .append("        *                                                           *").append("\n")
                    .append("        *-*-*-*-*-*-*-*-*-*-*-*-[方法执行结束]-*-*-*-*-*-*-*-*-*-*-*-*").append("\n");
            logger.debug(sf.toString());
        }
    }
}
