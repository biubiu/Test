package com.shawn.guru;

import java.lang.reflect.Field;

public class Builder {

	static void classBuilder(Class clazz){
		Field[] fields = clazz.getFields();
		if(fields!=null && fields.length>0){
			for (Field field:fields) {
				Class typeClass = field.getType();
				String fieldName = field.getName();
				System.out.println();
			}
		}
	}
}
