package com.ashik.apis;

import static spark.Spark.post;

public class RecieveSMS {

	public static void main(String[] args) {
		
		post("/reply", (req, res) -> {
			String requestBody = req.body();
			System.out.println("requestBody "+requestBody);
			return "Received request body: " + requestBody;
//			Message sms = new Message.Builder().body(new Body("sdfj")).build();
		});
	}

}
