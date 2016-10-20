package com.evanvin.highcharts.dao;

import java.util.Arrays;

public class LineChartSeries {

	private String name;
	private Number[] data;
	
	public LineChartSeries(){}
	public LineChartSeries(String name, Number[] data) {
		super();
		this.name = name;
		this.data = data;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number[] getData() {
		return data;
	}
	public void setData(Number[] data) {
		this.data = data;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineChartSeries other = (LineChartSeries) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "LineChartSeries [name=" + name + ", data="
				+ Arrays.toString(data) + "]";
	}
	
	
}
