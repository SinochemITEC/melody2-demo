package com.eyeieye.melody.demo.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * administerȨ�޿���,ȱʡֵΪ��¼�û�
 * demo����Ȩ��ͶƱ��Ϊ'��'�߼�
 * 
 * @author fish
 * 
 */
@Target( { ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminAccess {
	FunctionsEnum[] value() default {};
}