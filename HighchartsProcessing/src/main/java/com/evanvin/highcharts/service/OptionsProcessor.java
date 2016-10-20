package com.evanvin.highcharts.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.Title;

public class OptionsProcessor {

	
	public static Options processOptions(HashMap<String, Object> params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException{
		Options options = new Options();
		
		Iterator iter = params.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry pair = (Map.Entry)iter.next();
	        String[] attributes = pair.getKey().toString().split("\\:");
	        
	        if(attributes.length > 1){
	        	Class<?> c1 = options.getClass();
	        	Method method = c1.getDeclaredMethod("get" + WordUtils.capitalize(attributes[0]));
				
				Class<?> c2 = Class.forName(method.getReturnType().getName());
				Object o1 = method.invoke(options) == null ? c2.newInstance() : method.invoke(options);
								
				//finally set the final part
				Field f = c1.getDeclaredField(attributes[0]);
				f.setAccessible(true);
				Method setter = c1.getMethod(("set" + WordUtils.capitalize(attributes[0])), f.getType());
				Object x = step(Arrays.copyOfRange(attributes, 1, attributes.length), o1, pair.getValue());
				setObjectValue(x, f.getType(), options, setter);
        	
	        }      
	    }
		
		
		return options;
	}
	
	
	
	
	public static Object step(String[] attributes, Object mainObject, Object value) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException{
		Class<?> c1 = mainObject.getClass();
    	Method method = null;
    	
		if(attributes.length > 1){
			//recursion
			method = c1.getDeclaredMethod("get" + WordUtils.capitalize(attributes[0]));
			Class<?> c2 = Class.forName(method.getReturnType().getName());
			Object o1 = method.invoke(mainObject) == null ? c2.newInstance() : method.invoke(mainObject);
			mainObject = step(Arrays.copyOfRange(attributes, 1, attributes.length), o1,value);
		}
		
		//set the final variable
		Field f = c1.getDeclaredField(attributes[0]);
		f.setAccessible(true);
		Method setter = c1.getMethod(("set" + WordUtils.capitalize(attributes[0])), f.getType());
		setObjectValue(value, f.getType(), mainObject, setter);
		
		 return mainObject;
	}
	
	
	public static void setObjectValue(Object value, Class<?> class1, Object mainObject, Method setter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{		
		if(class1.getName().equals("java.lang.Integer"))
			setter.invoke(mainObject, (Integer)value);
		else if(class1.getName().equals("java.lang.String"))
			setter.invoke(mainObject, value.toString());
		else if(class1.getName().equals("com.googlecode.wickedcharts.highcharts.options.Title"))
			setter.invoke(mainObject, (Title)value);
	}
	
}
