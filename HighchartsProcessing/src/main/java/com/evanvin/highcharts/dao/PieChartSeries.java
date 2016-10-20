package com.evanvin.highcharts.dao;

public class PieChartSeries {

	private String name;
	private Number y;
	private Boolean sliced;
	private Boolean selected;
	
	
	public PieChartSeries(){}
	public PieChartSeries(String name, Number y) {
		super();
		this.name = name;
		this.y = y;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getY() {
		return y;
	}
	public void setY(Number y) {
		this.y = y;
	}
	public Boolean getSliced() {
		return sliced;
	}
	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
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
		PieChartSeries other = (PieChartSeries) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "PieChartSeries [name=" + name + ", y=" + y + ", sliced="
				+ sliced + ", selected=" + selected + "]";
	}
		
}
