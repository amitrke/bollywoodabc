package com.babc.server.model.vo;

import com.babc.server.model.CategoryEntity;
import com.babc.server.model.PictureEntity;

public class CategoryVo {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private PictureEntity picture;
	
	private int status;
	
	public CategoryVo(Long id) {
		this.id = id;
	}

	public CategoryVo(String name, String description, String type,
			PictureEntity picture, char status) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.picture = picture;
		this.status = status;
	}

	public CategoryVo(CategoryEntity categoryEntity, PictureEntity picture) {
		super();
		this.id = categoryEntity.getId();
		this.name = categoryEntity.getName();
		this.description = categoryEntity.getDescription();
		this.type = categoryEntity.getType();
		this.picture = picture;
		this.status = categoryEntity.getStatus();
	}
	
	public String getTitleUrl(){
		return name.replace(" ", "-").replace("\'", "");
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public PictureEntity getPicture() {
		return picture;
	}

	public int getStatus() {
		return status;
	}

	
	public void setPicture(PictureEntity picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + status;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryVo other = (CategoryVo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (status != other.status)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryVo [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (picture != null) {
			builder.append("picture=");
			builder.append(picture);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
