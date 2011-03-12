package com.babc.server.web.admin.model;

import com.babc.server.utils.AppUtils;

public class KeyValuePair {
	
	private Long key;
	private String value;
	
	public KeyValuePair(Long key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Long getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public String getUrlEscapeValue(){
		return AppUtils.urlFormat(value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValuePair [");
		if (key != null) {
			builder.append("key=");
			builder.append(key);
			builder.append(", ");
		}
		if (value != null) {
			builder.append("value=");
			builder.append(value);
		}
		builder.append("]");
		return builder.toString();
	}
}
