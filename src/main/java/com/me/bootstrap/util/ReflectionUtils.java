/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ReflectionUtils.java,v 1.1 2013/03/04 09:56:33 sathya Exp $
 */
package com.me.bootstrap.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;

import org.springframework.util.Assert;




public class ReflectionUtils {

	

	/**
	 * 
	* @Title: getFieldValue
	* @Description:  直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	* @param @param object
	* @param @param fieldName
	* @param @return    参数
	* @return Object    返回
	* @throws
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			System.err.println("不可能抛出的异常"+ e.getMessage());
		}
		return result;
	}

	/**
	 * 
	* @Title: setFieldValue
	* @Description: 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	* @param @param object
	* @param @param fieldName
	* @param @param value    参数
	* @return void    返回
	* @throws
	*
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			
		}
	}

	/**
	 * 
	* @Title: invokeMethod
	* @Description:直接调用对象方法, 无视private/protected修饰符.
	* @param @param object
	* @param @param methodName
	* @param @param parameterTypes
	* @param @param parameters
	* @param @return    参数
	* @return Object    返回
	* @throws
	*
	 */
	public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null)
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 
	* @Title: getDeclaredField
	* @Description: 循环向上转型, 获取对象的DeclaredField. 如向上转型到Object仍无法找到, 返回null.
	* @param @param object
	* @param @param fieldName
	* @param @return    参数
	* @return Field    返回
	* @throws
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 
	* @Title: makeAccessible
	* @Description:强行设置Field可访问.
	* @param @param field    参数
	* @return void    返回
	* @throws
	*
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 
	 * 
	* @Title: getDeclaredMethod
	* @Description: 循环向上转型,获取对象的DeclaredMethod. 如向上转型到Object仍无法找到, 返回null.
	* @param @param object
	* @param @param methodName
	* @param @param parameterTypes
	* @param @return    参数
	* @return Method    返回
	* @throws
	*
	 */
	protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		Assert.notNull(object, "object不能为空");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

   /**
	* 
	* @Title: getSuperClassGenricType
	* @Description:
	* @param @param <T>
	* @param @param clazz
	* @param @return    参数
	* @return Class<T>    返回
	* @throws
	*
	* 通过反射,获得Class定义中声明的父类的泛型参数的类型.
	* 如无法找到, 返回Object.class.
	* eg.
	* public UserDao extends HibernateDao<User>
	*
	* @param clazz The class to introspect
	* @return the first generic declaration, or Object.class if cannot be determined
	*/
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * @Title: getSuperClassGenricType
	 * @Description:
	 * @param @param clazz
	 * @param @param index
	 * @param @return    参数
	 * @return Class    返回
	 * @throws
	 *
	 *  通过反射,获得定义Class时声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 
	* @Title: convertElementPropertyToList
	* @Description:
	* @param @param collection
	* @param @param propertyName
	* @param @return    参数
	* @return List    返回
	* @throws
	*
	* 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 
	* @Title: convertElementPropertyToString
	* @Description: 
	* @param @param collection
	* @param @param propertyName
	* @param @param separator
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 *提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list.iterator(), separator);
	}

	/**
	 * 
	* @Title: convertValue
	* @Description: 
	* @param @param value
	* @param @param toType
	* @param @return    参数
	* @return Object    返回
	* @throws
	*
	*转换字符串类型到clazz的property类型的值.
	 * 
	 * @param value 待转换的字符串
	 * @param clazz 提供类型信息的Class
	 * @param propertyName 提供类型信息的Class的属性.
	 */
	public static Object convertValue(Object value, Class<?> toType) {
		try {
			DateConverter dc = new DateConverter();
			dc.setUseLocaleFormat(true);
			dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
			ConvertUtils.register(dc, Date.class);
			return ConvertUtils.convert(value);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 
	* @Title: convertReflectionExceptionToUnchecked
	* @Description: 将反射时的checked exception转换为unchecked exception.
	* @param @param e
	* @param @return    参数
	* @return RuntimeException    返回
	* @throws
	*
	*  
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException)
			return new IllegalArgumentException("Reflection Exception.", e);
		else if (e instanceof InvocationTargetException)
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
}
