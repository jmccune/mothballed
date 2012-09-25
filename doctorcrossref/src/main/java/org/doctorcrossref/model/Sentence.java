package org.doctorcrossref.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Represents a sentence or a segment thereof that is
 * a common unit (e.g. a verse in scripture may sometimes
 * be composed of only a sentence fragment).
 * 
 * @author justinanddiana
 *
 */
@Entity
@Table(name="Sentence")
public class Sentence {
	
	private String id;
	/**
	 * The normative presentation of the sentence-
	 * e.g. with grammar and capatilzation.  This
	 * is what a human user would expect to see.
	 */
	private String sentence;
	
	/** 
	 * The computer version of the sentence--e.g.
	 * the sentence with grammar and capitalization removed.
	 */
	private String rawValue;
	
	/** The Hash code of the raw string-- used to quickly
	 * find exact matches.
	 */
	private String sha1HashCode;

	
	// ==============================================================
	// Getters & Setters
	// ==============================================================
	
	@Id
	@GeneratedValue
	@Column
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	@Column
	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}

	@Column
	public String getSha1HashCode() {
		return sha1HashCode;
	}

	public void setSha1HashCode(String sha1HashCode) {
		this.sha1HashCode = sha1HashCode;
	}

}
