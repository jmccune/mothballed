package org.doctorcrossref.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Entity")
public class EntityObject {

	@Id
	@GeneratedValue
	@Column
	/** The unique ID for this entity 
	 * REQUIRED
	 */
	private String UUID;
	
	/** The label (human readable) for this entity 
	 * REQUIRED
	 */
	@Column
	private String label;
	
	/** The description (human readable) for this entity. 
	 * OPTIONAL
	 */
	@Lob
	private String description;
	
	
	/** The type (ontology) of this entity
	 * REQUIRED 
	 */
	@Column
	private String type;
	
	/**
	 * The creator of this information.
	 * REQUIRED
	 * The ROOT node is one that points to itself.
	 */
	 @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional = true)
	 @JoinColumn(name = "CREATOR_ID", nullable = false)
	 @Fetch(FetchMode.SELECT)
	 @Cascade({ org.hibernate.annotations.CascadeType.ALL,
	            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	           })
	private EntityObject creator;	
	
	/** 
	 * When this entity was created/started.
	 * OPTIONAL
	 */
	@Temporal(TemporalType.DATE)
	private Date	startDate;
	
	/** 
	 * When this entity was created/started.
	 * OPTIONAL
	 */
	@Temporal(TemporalType.TIME)
	private Date	startTime;
	
	/** 
	 * When this entity was destroyed/finished.
	 * OPTIONAL
	 */
	@Temporal(TemporalType.DATE)
	private Date    endDate;
	
	/**
	 * When this entity was destroyed/finished.
	 * OPTIONAL
	 */
	@Temporal(TemporalType.TIME)
	private Date	endTime;
	
	
	/**
	 * The Property Map for this entity
	 * OPTIONAL
	 */
	@Lob
	private String  propertyMap;

	
	// ==============================================================
	// Getters & Setters
	// ==============================================================
	
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public EntityObject getCreator() {
		return creator;
	}

	public void setCreator(EntityObject creator) {
		this.creator = creator;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public Date getEndTime() {
		return endTime;
	}

	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	public String getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(String propertyMap) {
		this.propertyMap = propertyMap;
	}	
}
