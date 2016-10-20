package com.evanvin.highcharts.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.evanvin.highcharts.dao.LineChartSeries;
import com.evanvin.highcharts.service.OptionsProcessor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.wickedcharts.highcharts.options.Options;

public class LineChartTestParams {

	
	
	
	public void LineChartTestParams1(List<LineChartSeries> series){
		 HashMap<String,Object> params = new HashMap<String, Object>();
		 params.put("title:text", "Monthly Average Temperature");
		 params.put("title:x", -20);
		 
		// params.put("xAxis:cetagories", Arrays.asList(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
		 Options o = null;
		 
		try {
			o = new OptionsProcessor().processOptions(params);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 mapper.setSerializationInclusion(Include.NON_NULL);
			System.out.println(mapper.writeValueAsString(o));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
