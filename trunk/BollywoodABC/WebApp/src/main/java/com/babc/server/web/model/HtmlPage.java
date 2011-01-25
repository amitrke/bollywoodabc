package com.babc.server.web.model;


public class HtmlPage {
	
	private String title;
	private String description;
	private String keywords;
	private String expires;
	private String author;
	private Object data;
	
	public HtmlPage(String title, String description, String keywords,
			String expires, String author, Object data) {
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.expires = expires;
		this.author = author;
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getExpires() {
		return expires;
	}

	public String getAuthor() {
		return author;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HtmlPage [");
		if (author != null) {
			builder.append("author=");
			builder.append(author);
			builder.append(", ");
		}
		if (data != null) {
			builder.append("data=");
			builder.append(data);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (expires != null) {
			builder.append("expires=");
			builder.append(expires);
			builder.append(", ");
		}
		if (keywords != null) {
			builder.append("keywords=");
			builder.append(keywords);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
		}
		builder.append("]");
		return builder.toString();
	}
}
