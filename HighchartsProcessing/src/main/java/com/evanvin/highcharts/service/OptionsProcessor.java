package com.evanvin.highcharts.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.Options;

public class OptionsProcessor {

	
	public Options processOptions(HashMap<String, Object> params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException{
		Options options = new Options();
		Iterator iter = params.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry pair = (Map.Entry)iter.next();
	        String[] attributes = pair.getKey().toString().split("\\:");
	        
	        String getString = WordUtils.capitalize(attributes[0]);
	        String setString = WordUtils.capitalize(attributes[0]);
	        boolean isxAxis = false;
	        boolean isyAxis = false;
	        if(attributes[0].equals("xAxis")){
	        	getString = "SingleXAxis";
	        	setString = "xAxis";
	        	isxAxis = true;
	        }
	        else if(attributes[0].equals("yAxis")){
	        	getString = "SingleYAxis";
	        	setString = "yAxis";
	        	isyAxis = true;
	        }
	        	
	        
	        if(attributes.length > 1){
	        	Class<?> c1 = options.getClass();
	        	Method method = c1.getDeclaredMethod("get" + getString);
				
				Class<?> c2 = Class.forName(method.getReturnType().getName());
				Object o1 = method.invoke(options) == null ? c2.newInstance() : method.invoke(options);
								
				//finally set the final part
				
				Field f = c1.getDeclaredField(attributes[0]);
				f.setAccessible(true);
				Method setter = c1.getMethod(("set" + setString), f.getType());
				Object x = step(Arrays.copyOfRange(attributes, 1, attributes.length), o1, pair.getValue());
				
				if(isxAxis)
					options.setxAxis((Axis)x);
				else if(isyAxis)
					options.setyAxis((Axis)x);
				else
					setObjectValue(x, f.getType(), options, setter);
        	
	        }      
	    }
		
		
		return options;
	}
	
	
	
	
	public Object step(String[] attributes, Object mainObject, Object value) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException{
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
	
	
	




	public void setObjectValue(Object value, Class<?> class1, Object mainObject, Method setter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{		
		System.out.println(">>>>>"+mainObject.getClass().getCanonicalName());
		
		setter.invoke(mainObject, class1.cast(value));
		try{
			
		} catch(ClassCastException cce){
			//List<Object> o = new List<Object>(value) {
			System.out.println("class cast exception");
			
			//setter.invoke(mainObject, o.getClass().cast(value).get(0));
		}
		
//		if(class1.getName().equals("java.lang.Integer"))
//			setter.invoke(mainObject, (Integer)value);
//		else if(class1.getName().equals("java.lang.String"))
//			setter.invoke(mainObject, value.toString());
//		else if(class1.getName().equals("com.googlecode.wickedcharts.highcharts.options.Title"))
//			setter.invoke(mainObject, (Title)value);
//		else if(class1.getName().equals("com.googlecode.wickedcharts.highcharts.options.Axis"))
//			setter.invoke(mainObject, (Axis)value);
//		else if(class1.getName().equals("java.util.List"))
//			setter.invoke(mainObject, class1.cast(value));
//		else
//			System.out.println("Class ( " + class1.getName() + " ) is not handled yet");
	}
	
}
