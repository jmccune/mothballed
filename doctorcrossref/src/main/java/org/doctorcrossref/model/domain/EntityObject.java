package org.doctorcrossref.model.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Cascade;
import org.tierlon.system.helper.StringHelper;

import org.doctorcrossref.model.domain.support.EntityProperty;

@Entity
@Table(name="Entity")
public class EntityObject {

	@Id
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
	 @JoinColumn(name = "CREATOR_ID", nullable = true)
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
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "entity")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL,
        	   org.hibernate.annotations.CascadeType.SAVE_UPDATE,
       })
	private Set<EntityProperty> propertySet = new HashSet<EntityProperty>(0);

	/** Hibernate Constructor */
	@SuppressWarnings("unused")
	private EntityObject(){}
	
	// ==============================================================
	// Construction
	// ==============================================================
	public EntityObject(String label, String description, String type,
			EntityObject creator, Date startDate, Date startTime, Date endDate,
			Date endTime) {
		super();
		this.UUID = "UUID"+new Date().getTime(); 
		this.label = label;
		this.description = description;
		this.type = type;
		this.creator = creator;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		
	}
	
	// ==============================================================
	// Methods
	// ==============================================================
	public void addProperty(String key, String value) {
		this.getPropertySet().add(new EntityProperty(this,key,value));
	}
	
	
	// ==============================================================
	// Object Methods
	// ==============================================================
	public String toString() {
		
		String creatorValue= (creator!=null) ?
				creator.getLabel() : "null";

		
		return StringHelper.objectToString("EntityObject",
				"UUID",UUID,
				"label",label,
				"type",type,
				"startDate",""+startDate,
				"endDate",""+endDate,
				"creator",creatorValue);
				
	}
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

	
	/** For Hibernate Only */
	public Set<EntityProperty> getPropertySet() {
		return propertySet;
	}

	/** For Hibernate Only */
	public void setPropertySet(Set<EntityProperty> propertySet) {
		this.propertySet = propertySet;
	}

	
}
