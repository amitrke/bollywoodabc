package com.babc.server.web.admin.model;


public class AdminHomeModel {
	
	private String dateTime;

	public AdminHomeModel(String title, String description, String keywords,
			String username, String dateTime) {
		
		this.dateTime = dateTime;
	}

	public AdminHomeModel(String username, String dateTime) {
		
		this.dateTime = dateTime;
	}

	public String getDateTime() {
		return dateTime;
	}

	
	
}
