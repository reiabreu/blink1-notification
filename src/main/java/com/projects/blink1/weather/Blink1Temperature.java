package com.projects.blink1.weather;

import java.awt.Color;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import thingm.blink1.Blink1;

public class Blink1Temperature {
	
	private  Log log = LogFactory.getLog(Blink1Temperature.class); 
	private  Blink1 blink1=new Blink1();
	private  double MAX_TEMP=50;
	private  double MIN_TEMP=-30;
	
	
	private  Color getColour(double temperature)
	{
		//this is rubbish.
		
		if ((temperature >= 30) && (temperature <= MAX_TEMP)) return Color.RED;
		if ((temperature >= 20) && (temperature < 30)) return Color.ORANGE;
		if ((temperature >= 10) && (temperature < 20)) return Color.CYAN;
		if ((temperature >= 0) && (temperature < 10)) return Color.BLUE;
		if ((temperature >= MIN_TEMP) && (temperature <0)) return Color.PINK;
		
		return Color.gray;
	}
	
	public void setTemperature(double getColour)
	{
		blink1.setRGB(getColour(getColour));
	}
	
	public void openBlink1()
	{
		log.info("Opening blink1...");
		blink1.open();
	}
	
	public void closeBlink1()
	{
		log.info("Shutting down blink1...");
		blink1.setRGB(Color.BLACK);
		blink1.close();
	}

}
