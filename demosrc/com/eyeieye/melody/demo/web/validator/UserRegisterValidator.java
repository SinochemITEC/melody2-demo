package com.eyeieye.melody.demo.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.eyeieye.melody.demo.domain.User;

/**
 * ��½У�飬ʵ��Spring��Validator�ӿڣ����ñ������ʽ��֤��
 * 
 * @author zhengdd
 * @version $Id: UserRegisterValidator.java,v 1.3 2012/02/20 15:13:46 fish Exp $
 */
public class UserRegisterValidator implements Validator {

    public boolean supports(Class<?> clz) {
        return User.class.equals(clz);
    }

    /**
     * ��֤����
     * @param obj
     * @param err
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors err) {
        Assert.notNull(obj);
        Assert.isInstanceOf(User.class, obj);
        User user = (User) obj;
        String realName = user.getRealName();
        if (StringUtils.isBlank(realName)) {
            // 4�������ֱ�Ϊ����֤�������������������Ϣ���롢������Ϣ������Ĭ�ϴ�����Ϣ��
            // ������Ϣ����ͨ������MessageResource��ָ��������Ϣ���룬�������ڴ�����д��������Ϣ��
            err.rejectValue("realName", "user.register.realName.empty", null, "����д����");
        } else {
            if (realName.length() > 16) {
                err.rejectValue("realName", "user.register.realName.long", new Integer[]{16}, "�������ܳ���{0}���ַ�");
            }
        }
        if(StringUtils.isBlank(user.getPassword())){
        	 err.rejectValue("password", null, null, "���������");
        }
    }

}
