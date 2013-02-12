package com.projects.blink1.weather;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;


public class App 
{

	private static Log log = LogFactory.getLog(App.class); 

	public static void main( String[] args ) throws InterruptedException, ClientProtocolException, IOException
	{
		log.info("Starting up weather notification...");
		
		//reading app configuration properties
		Properties prop = new Properties();
		prop.load(new FileInputStream("conf/config.properties"));
		String apiKey=prop.getProperty("apiKey");
		String country=prop.getProperty("country");
		String city=prop.getProperty("city");
		String interval= prop.getProperty("interval");
		log.info("Loading properties from config.properties:\n" +
				"Wunderground API key=" + apiKey + "\n" +
				"Country=" + country + "\n" +
				"City=" + city + "\n" +
				"Refresh interval (minutes)=" + interval);
		
		Blink1Temperature bltemp = new Blink1Temperature();
		bltemp.openBlink1();
		HttpQuery httpQuery= new HttpQuery();
		
		log.info("Registering shutdown hook...");
		ShutDownHook hook = new ShutDownHook(httpQuery,bltemp);
		Runtime runtime = Runtime.getRuntime(); 
		runtime.addShutdownHook(hook);


		while(true)
		{
			double temperature =httpQuery.getTemperature(apiKey,country,city);
			bltemp.setTemperature(temperature);
			//sleep before making another temperature inquiry to the weather server
			Thread.sleep(new Integer(interval)*60*1000 );
		}
	}
}
