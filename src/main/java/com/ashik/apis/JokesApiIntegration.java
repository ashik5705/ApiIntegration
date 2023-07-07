package com.ashik.apis;

import java.net.URI;
import java.net.http.HttpRequest;

public class JokesApiIntegration {
	
	
	public HttpRequest getHttpRequest() {
		
		return HttpRequest.newBuilder()
				.uri(URI.create("https://jokes-by-api-ninjas.p.rapidapi.com/v1/jokes"))
				.header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
				.header("X-RapidAPI-Key", "2f0df8a093msh9cb1cc2253131d5p134085jsn660d8d70b9d2")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
	}
	
	
	
}
