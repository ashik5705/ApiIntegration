package com.ashik.jokes.api;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegrationApplication.class, args);
		JokesApiIntegration request = new JokesApiIntegration();

		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(response.body());
	}
}
