package com.ashik.apis;

import java.net.URI;
import java.net.http.HttpRequest;

public class WeatherApiIntegration {

public HttpRequest getHttpRequest() {
		
		return HttpRequest.newBuilder()
				.uri(URI.create("http://api.weatherapi.com/v1/current.json?q=Dhaka"))
				.header("key", "0b4d16204c814178a4b52643232006")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
	}

}
