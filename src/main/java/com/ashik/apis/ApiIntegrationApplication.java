package com.ashik.apis;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegrationApplication.class, args);
		

		HttpResponse<String> response = null;
		
		if (EnableApis.DISABLE_JOKES_API) {
			JokesApiIntegration request = new JokesApiIntegration();
			try {
				response = HttpClient.newHttpClient().send(request.getHttpRequest(),HttpResponse.BodyHandlers.ofString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(response.body());
		}
		
		if (EnableApis.ENABLE_WEATHER_API) {
			WeatherApiIntegration request = new WeatherApiIntegration();
			try {
				response = HttpClient.newHttpClient().send(request.getHttpRequest(),HttpResponse.BodyHandlers.ofString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(response.body());
		}
		
	}
}
