package com.evanvin.highcharts.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.evanvin.highcharts.dao.LineChartSeries;

public class ProcessTextFiles {
	
	public ProcessTextFiles() {}

	
	
	
	public <E> void printObjectList(List<E> list){
		for(Object o : list)
			System.out.println(o.toString());
	}
	
	
	public List<LineChartSeries> processLineChartTestObject() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("testing_objects/monthlyAverageTemps.txt").getFile());
		List<LineChartSeries> lcs = new ArrayList<LineChartSeries>();		
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()){
				String[] s = sc.nextLine().split("\t");
				String name = s[0];
				String[] v = s[1].split(",");
				Number[] data = new Number[v.length];
				for(int i = 0; i < data.length; i++)
					data[i] = (Number)(Double.parseDouble(v[i]));
				lcs.add(new LineChartSeries(name, data));				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lcs;
	}
	
	
	
	
	

	
	
	
}
