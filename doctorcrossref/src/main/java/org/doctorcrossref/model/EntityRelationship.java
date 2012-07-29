package org.doctorcrossref.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Entity_Relationships")
public class EntityRelationship {

	/** The relationship id. */
	@Id
	@GeneratedValue
	@Column
	private String uuid;
	
	 @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
	 @JoinColumn(name = "ENTITY_1_ID", nullable = false)
	 @Fetch(FetchMode.SELECT)
	private EntityObject entity1;
	 
	 @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
	 @JoinColumn(name = "ENTITY_2_ID", nullable = false)
	 @Fetch(FetchMode.SELECT)
	private EntityObject entity2;
	 
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = true)
	@JoinColumn(name = "RELATIONSHIP_ID", nullable = true)
	@Fetch(FetchMode.SELECT)
	private EntityObject relationship;

	// ==============================================================
	// Getters & Setters
	// ==============================================================
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public EntityObject getEntity1() {
		return entity1;
	}

	public void setEntity1(EntityObject entity1) {
		this.entity1 = entity1;
	}

	public EntityObject getEntity2() {
		return entity2;
	}

	public void setEntity2(EntityObject entity2) {
		this.entity2 = entity2;
	}

	public EntityObject getRelationship() {
		return relationship;
	}

	public void setRelationship(EntityObject relationship) {
		this.relationship = relationship;
	}	
}
