package com.projects.blink1.weather;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpQuery {

	private  Log log = LogFactory.getLog(HttpQuery.class); 
	private  HttpClient httpclient = new DefaultHttpClient();

	public  double getTemperature(String apiKey,String country,String city) throws ClientProtocolException, IOException
	{
		HttpGet httpget = new HttpGet("http://api.wunderground.com/api/" +apiKey + "/geolookup/conditions/q/" + country + "/" + city + ".json");

		log.info("executing HTTP request " + httpget.getURI());

		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = httpclient.execute(httpget, responseHandler);

		JSONObject json = (JSONObject) JSONSerializer.toJSON( responseBody.trim() );
		JSONObject json_current_observation = json.getJSONObject("current_observation");
		double temperature = new Double(json_current_observation.getString ( "temp_c" ));
		log.info("Current temperature: "+temperature + " Celsius");

		return temperature;

	}

	public void destroyConnection()
	{
		log.info("Shutting down http client");
		httpclient.getConnectionManager().shutdown();
	}
}
