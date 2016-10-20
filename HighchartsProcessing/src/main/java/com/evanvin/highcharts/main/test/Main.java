package com.evanvin.highcharts.main.test;

import java.net.URL;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	
	
	public Main(){
		URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
		
		
	}
	

}
