package com.activitize.springmvc.Models;

public class JsonResponse {

	private String status = "";
	private String errorMessage = "";

	public JsonResponse(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
	
}
