package com.babc.server.model;

import java.io.Serializable;

public class PageCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String data;

	/**
	 * @param data
	 */
	public PageCache(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	
	
}
