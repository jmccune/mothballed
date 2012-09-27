package org.doctorcrossref.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Sentence_Associations") 
public class SentenceAssociation {

	@Id
	@GeneratedValue
	@Column
	private long 	 id;
	
	
	/**
	 * The Sentence the entities herein are associated with.
	 * REQUIRED
	 * The ROOT node is one that points to itself.
	 */
	 @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
	 @JoinColumn(name = "SENTENCE_ID", nullable = true)
	 @Fetch(FetchMode.SELECT)
	 @Cascade({ org.hibernate.annotations.CascadeType.ALL,
	            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	           })
	private Sentence sentence;
	 
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "ENTITY_ID", nullable = true)
	@Fetch(FetchMode.SELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE, }) 
	private EntityObject entityObject;
	
	@Column
	private long contextId;
	

	// ==============================================================
	// Getters & Setters
	// ==============================================================
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	public EntityObject getEntityObject() {
		return entityObject;
	}

	public void setEntityObject(EntityObject entityObject) {
		this.entityObject = entityObject;
	}

	public long getContextId() {
		return contextId;
	}

	public void setContextId(long contextId) {
		this.contextId = contextId;
	}
	
}
