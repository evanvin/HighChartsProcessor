package com.evanvin.highcharts.main.test;

import java.util.List;

import com.evanvin.highcharts.dao.LineChartSeries;
import com.evanvin.highcharts.service.ProcessTextFiles;
import com.evanvin.highcharts.test.LineChartTestParams;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	
	
	public Main(){
		ProcessTextFiles ptf = new ProcessTextFiles();
		
		
		
		//TEST FOR LINE CHART
		List<LineChartSeries> lcs = ptf.processLineChartTestObject();
		new LineChartTestParams().LineChartTestParams1(lcs);
		
		
	}
	

}
