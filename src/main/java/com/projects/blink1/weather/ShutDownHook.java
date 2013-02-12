package com.projects.blink1.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ShutDownHook extends Thread {
	
	private Log log = LogFactory.getLog(ShutDownHook.class);

	private HttpQuery httpQuery;
	private Blink1Temperature blink1Temperature;
	
	public ShutDownHook(HttpQuery httpQuery, Blink1Temperature blink1Temperature) {
		super();
		this.httpQuery = httpQuery;
		this.blink1Temperature = blink1Temperature;
	}
	
	public void run() {
		log.info("Shutdown hook called.");
		this.httpQuery.destroyConnection();
		this.blink1Temperature.closeBlink1();
	}

}
