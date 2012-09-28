package org.doctorcrossref.model.domain.support;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.doctorcrossref.model.domain.EntityObject;

@Entity
@Table(name = "Entity_Property")
public class EntityProperty {

	@Id
	@GeneratedValue
	@Column
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTITY_ID", nullable = false)
	private EntityObject entity;

	@Column
	private String key;

	@Column
	private String value;

	@Column
	private String valueType;

	// ==============================================================
	// Construction
	// ==============================================================
	/** For Hibernate*/
	@SuppressWarnings("unused")
	private EntityProperty() {}
	
	public EntityProperty(EntityObject entity, String key,String value) {
		this.entity = entity;
		this.key = key;
		this.value = value;
		this.valueType=null;
	}
	
	// ==============================================================
	// Getters & Setters
	// ==============================================================

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public void setEntity(EntityObject entity) {
		this.entity = entity;
	}

	public EntityObject getEntity() {
		return this.entity;
	}
}
