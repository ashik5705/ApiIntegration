package com.ashik.apis;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

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
		
		if (EnableApis.DISABLE_WEATHER_API) {
			WeatherApiIntegration request = new WeatherApiIntegration();
			try {
				response = HttpClient.newHttpClient().send(request.getHttpRequest(),HttpResponse.BodyHandlers.ofString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(response == null) {
				return;
			}
			extrackAndPrintResponse(response.body());
		}
		
		if(EnableApis.ENABLE_TWILIO_SDK) {
			Twilio.init(EnableApis.TWILIO_ACCOUNT_SID, EnableApis.TWILIO_AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+8801301707917"),
//	                new com.twilio.type.PhoneNumber("+8801858799182"),
	                new com.twilio.type.PhoneNumber(EnableApis.MY_TWILIO_NUMBER),
	                "Hi again from Twilio")
	            .create();

	        System.out.println(message.getSid());
		}
		
		if (EnableApis.ENABLE_TWILIO_SDK_FOR_CALL) {
			Twilio.init(EnableApis.TWILIO_ACCOUNT_SID, EnableApis.TWILIO_AUTH_TOKEN);

			String from = EnableApis.MY_TWILIO_NUMBER;
			String to = "+8801301707917";

			Call call = null;
			try {
				call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
						new URI("http://demo.twilio.com/docs/voice.xml")).create();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(call.getSid());
		}
		
	}

	private static void extrackAndPrintResponse(String jsonResponse) {
		
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        String locationName = null;
        String country = null;
        double temperatureC = 0.0;
        double temperatureF = 0.0;
        String conditionText = null;
		try {
			jsonNode = objectMapper.readTree(jsonResponse);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        // Extract specific fields
		if(jsonNode!=null) {
			locationName = jsonNode.get("location").get("name").asText();
	        country = jsonNode.get("location").get("country").asText();
	        temperatureC = jsonNode.get("current").get("temp_c").asDouble();
	        temperatureF = jsonNode.get("current").get("temp_f").asDouble();
	        conditionText = jsonNode.get("current").get("condition").get("text").asText();
		}
        // Print the extracted data
        System.out.println("Location Name: " + locationName);
        System.out.println("Country: " + country);
        System.out.println("Temperature (C): " + temperatureC);
        System.out.println("Temperature (F): " + temperatureF);
        System.out.println("Condition: " + conditionText);
	}
}
