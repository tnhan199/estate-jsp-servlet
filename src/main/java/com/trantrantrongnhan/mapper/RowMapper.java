package com.trantrantrongnhan.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.commons.beanutils.BeanUtils;

import com.trantrongnhan.annotations.Column;
import com.trantrongnhan.annotations.Entity;

public class RowMapper<T> {
	public T mapRow(ResultSet result, Class clazz) {
		
		if (clazz.isAnnotationPresent(Entity.class)) {
			try {
				T obj=(T)clazz.newInstance();
				System.out.println(result.getInt("id"));
				Field[] fields=clazz.getDeclaredFields();
				for(int i=0;i<fields.length;i++) {
					if(result.getObject(fields[i].getName())!=null)
						BeanUtils.setProperty(obj, fields[i].getName(), result.getObject(fields[i].getName()));
				}
				clazz=clazz.getSuperclass();
				while(clazz!=null) {
					fields=clazz.getDeclaredFields();
					for(int i=0;i<fields.length;i++) {
						if(result.getObject(fields[i].getName())!=null)
							BeanUtils.setProperty(obj, fields[i].getName(), result.getObject(fields[i].getName()));
					}
					clazz=clazz.getSuperclass();
				}
				return obj;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}
