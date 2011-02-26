package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.babc.server.AppConstants;
import com.babc.server.model.vo.CategoryVo;
import com.babc.server.utils.AppUtils;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Category Entity class.
 * @author Amit_K06
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class CategoryEntity extends BaseEntityImpl {
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String description;
	
	@Persistent
	private String type;
	
	@Persistent
	private Long picId;
	
	@Persistent
	private int status;

	public CategoryEntity() {}

	public CategoryEntity(String name, String description, String type, Long picId,
			char status) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.picId = picId;
		this.status = status;
	}
	
	public CategoryEntity(CategoryEntity categoryEntity) {
		this.name = categoryEntity.getName();
		this.description = categoryEntity.getDescription();
		this.type = categoryEntity.getType();
		this.picId = categoryEntity.getId();
		this.status = categoryEntity.getStatus();
	}
	
	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}
	
	public CategoryEntity(CategoryVo categoryVo) {
		super();
		this.name = categoryVo.getName();
		this.description = categoryVo.getDescription();
		this.type = categoryVo.getType();
		this.picId = categoryVo.getPicture().getId();
		this.status = categoryVo.getStatus();
	}
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "picId", picId, true);
		setProperty(entity, "name", name, false);
		setProperty(entity, "description", description, false);
		setProperty(entity, "type", type, true);
		setProperty(entity, "status", status, true);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		name = getStringProperty(entity, "name");
		description = getStringProperty(entity, "description");
		type = getStringProperty(entity, "type");
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
		picId = getLongProperty(entity, "picId");
		
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

	public Long getPicId() {
		return picId;
	}

	public int getStatus() {
		return status;
	}
	
	public String getGalleryUrl(){
		return "/photogallery/"+id+"/"+AppUtils.urlFormat(name)+".htm";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CategoryEntity other = (CategoryEntity) obj;
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
		builder.append("Category [");
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
		if (status != ' ') {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		builder.append("]");
		return builder.toString();
	}
}
