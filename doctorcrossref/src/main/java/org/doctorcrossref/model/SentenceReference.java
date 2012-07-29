package org.doctorcrossref.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sentence_Reference") 
public class SentenceReference {
			
	/** The particular reference unique identifier */
	private String uuid;
	
	/** 
	 * EXAMPLES: The writings of the ACM,  The writings of C.S. Lewis
	 * The ramblings of user XYZ123, etc.
	 */
	private String authorUUID;
	private String authorType;
	private String authorLabel;
	
	
	/** 
	 * Examples: 
	 *  	Cannonized Scriptures 
	 *  	??
	 * 	
	 */
	private String rootVolumeUUID;
	private String rootVolumeType;
	private String rootVolumeLabel;
	private short    rootVolumeOrder;
	
	
	/**
	 * Examples:  
	 *  The Bible
	 * 	Volume I of Communications of the ACM
	 * 	
	 */
	private String volumeUUID;
	private String volumeType;
	private String volumeLabel;
	private short volumeOrder;
	
	
	/**
	 * Examples:
	 *   The New Testament
	 * 	 Preface
	 *   
	 */
	private String volumeSectionUUID;
	private String volumeSectionType;
	private String volumeSectionLabel;
	private short volumeSectionOrder;
	
	/**
	 * Examples:
	 * 	The Gospel of John
	 * 	"A Confluence of Seizmic Matters"    [An article by...]
	 */
	private String bookUUID;
	private String bookType;
	private String bookLabel;
	private short    bookOrder;
	
	
	/**
	 * Examples:
	 * 	[The adventures of the people of Zeniff]
	 */
	private String bookSectionUUID;
	private String bookSectionType;
	private String bookSectionLabel;
	private short bookSectionOrder;
	
	/**
	 * Examples:
	 * 	John 3
	 */
	private String chapterUUID;
	private String chapterType;
	private String chapterLabel;
	private short chapterOrder;
	
	/**
	 * Examples:
	 *   ??
	 */
	private String chapterSectionUUID;
	private String chapterSectionType;
	private String chapterSectionLabel;
	private short	   chapterSectionOrder;
	
	/**
	 * Examples:
	 * 	Verse 16
	 */
	private String paragraphUUID;
	private String paragraphType;
	private String paragraphLabel;
	private short paragraphOrder;
	
	/**
	 * Examples:
	 * 	For God so loved the world...
	 */
	private String sentenceUUID;
	private short sentenceOrder;
	
	
	// ==============================================================
	// Getters & Setters
	// ==============================================================
	
	@Id
	@GeneratedValue
	@Column
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	@Column
	public String getAuthorUUID() {
		return authorUUID;
	}
	public void setAuthorUUID(String authorUUID) {
		this.authorUUID = authorUUID;
	}
	
	@Column
	public String getAuthorType() {
		return authorType;
	}
	public void setAuthorType(String authorType) {
		this.authorType = authorType;
	}
	
	@Column
	public String getAuthorLabel() {
		return authorLabel;
	}
	public void setAuthorLabel(String authorLabel) {
		this.authorLabel = authorLabel;
	}
	
	@Column
	public String getRootVolumeUUID() {
		return rootVolumeUUID;
	}
	public void setRootVolumeUUID(String rootVolumeUUID) {
		this.rootVolumeUUID = rootVolumeUUID;
	}
	
	@Column
	public String getRootVolumeType() {
		return rootVolumeType;
	}
	public void setRootVolumeType(String rootVolumeType) {
		this.rootVolumeType = rootVolumeType;
	}
	
	@Column
	public String getRootVolumeLabel() {
		return rootVolumeLabel;
	}
	public void setRootVolumeLabel(String rootVolumeLabel) {
		this.rootVolumeLabel = rootVolumeLabel;
	}
	
	@Column
	public short getRootVolumeOrder() {
		return rootVolumeOrder;
	}
	public void setRootVolumeOrder(short rootVolumeOrder) {
		this.rootVolumeOrder = rootVolumeOrder;
	}
	
	@Column
	public String getVolumeUUID() {
		return volumeUUID;
	}
	public void setVolumeUUID(String volumeUUID) {
		this.volumeUUID = volumeUUID;
	}
	
	@Column
	public String getVolumeType() {
		return volumeType;
	}
	public void setVolumeType(String volumeType) {
		this.volumeType = volumeType;
	}
	
	@Column
	public String getVolumeLabel() {
		return volumeLabel;
	}
	public void setVolumeLabel(String volumeLabel) {
		this.volumeLabel = volumeLabel;
	}
	
	@Column
	public short getVolumeOrder() {
		return volumeOrder;
	}
	public void setVolumeOrder(short volumeOrder) {
		this.volumeOrder = volumeOrder;
	}
	
	@Column
	public String getVolumeSectionUUID() {
		return volumeSectionUUID;
	}
	public void setVolumeSectionUUID(String volumeSectionUUID) {
		this.volumeSectionUUID = volumeSectionUUID;
	}
	
	@Column
	public String getVolumeSectionType() {
		return volumeSectionType;
	}
	public void setVolumeSectionType(String volumeSectionType) {
		this.volumeSectionType = volumeSectionType;
	}
	
	@Column
	public String getVolumeSectionLabel() {
		return volumeSectionLabel;
	}
	public void setVolumeSectionLabel(String volumeSectionLabel) {
		this.volumeSectionLabel = volumeSectionLabel;
	}
	
	@Column
	public short getVolumeSectionOrder() {
		return volumeSectionOrder;
	}
	public void setVolumeSectionOrder(short volumeSectionOrder) {
		this.volumeSectionOrder = volumeSectionOrder;
	}
	
	@Column
	public String getBookUUID() {
		return bookUUID;
	}
	public void setBookUUID(String bookUUID) {
		this.bookUUID = bookUUID;
	}
	
	@Column
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	@Column
	public String getBookLabel() {
		return bookLabel;
	}
	public void setBookLabel(String bookLabel) {
		this.bookLabel = bookLabel;
	}
	
	@Column
	public short getBookOrder() {
		return bookOrder;
	}
	public void setBookOrder(short bookOrder) {
		this.bookOrder = bookOrder;
	}
	
	@Column
	public String getBookSectionUUID() {
		return bookSectionUUID;
	}
	public void setBookSectionUUID(String bookSectionUUID) {
		this.bookSectionUUID = bookSectionUUID;
	}
	
	@Column
	public String getBookSectionType() {
		return bookSectionType;
	}
	public void setBookSectionType(String bookSectionType) {
		this.bookSectionType = bookSectionType;
	}
	
	@Column
	public String getBookSectionLabel() {
		return bookSectionLabel;
	}
	public void setBookSectionLabel(String bookSectionLabel) {
		this.bookSectionLabel = bookSectionLabel;
	}
	
	@Column
	public short getBookSectionOrder() {
		return bookSectionOrder;
	}
	public void setBookSectionOrder(short bookSectionOrder) {
		this.bookSectionOrder = bookSectionOrder;
	}
	
	@Column
	public String getChapterUUID() {
		return chapterUUID;
	}
	public void setChapterUUID(String chapterUUID) {
		this.chapterUUID = chapterUUID;
	}
	
	@Column
	public String getChapterType() {
		return chapterType;
	}
	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}
	
	@Column
	public String getChapterLabel() {
		return chapterLabel;
	}
	public void setChapterLabel(String chapterLabel) {
		this.chapterLabel = chapterLabel;
	}
	
	@Column
	public short getChapterOrder() {
		return chapterOrder;
	}
	public void setChapterOrder(short chapterOrder) {
		this.chapterOrder = chapterOrder;
	}
	
	@Column
	public String getChapterSectionUUID() {
		return chapterSectionUUID;
	}
	public void setChapterSectionUUID(String chapterSectionUUID) {
		this.chapterSectionUUID = chapterSectionUUID;
	}
	
	@Column
	public String getChapterSectionType() {
		return chapterSectionType;
	}
	public void setChapterSectionType(String chapterSectionType) {
		this.chapterSectionType = chapterSectionType;
	}
	
	@Column
	public String getChapterSectionLabel() {
		return chapterSectionLabel;
	}
	public void setChapterSectionLabel(String chapterSectionLabel) {
		this.chapterSectionLabel = chapterSectionLabel;
	}
	
	@Column
	public short getChapterSectionOrder() {
		return chapterSectionOrder;
	}
	public void setChapterSectionOrder(short chapterSectionOrder) {
		this.chapterSectionOrder = chapterSectionOrder;
	}
	
	@Column
	public String getParagraphUUID() {
		return paragraphUUID;
	}
	public void setParagraphUUID(String paragraphUUID) {
		this.paragraphUUID = paragraphUUID;
	}
	
	@Column
	public String getParagraphType() {
		return paragraphType;
	}
	public void setParagraphType(String paragraphType) {
		this.paragraphType = paragraphType;
	}
	
	@Column
	public String getParagraphLabel() {
		return paragraphLabel;
	}
	public void setParagraphLabel(String paragraphLabel) {
		this.paragraphLabel = paragraphLabel;
	}
	
	@Column
	public short getParagraphOrder() {
		return paragraphOrder;
	}
	public void setParagraphOrder(short paragraphOrder) {
		this.paragraphOrder = paragraphOrder;
	}
	
	@Column
	public String getSentenceUUID() {
		return sentenceUUID;
	}
	public void setSentenceUUID(String sentenceUUID) {
		this.sentenceUUID = sentenceUUID;
	}
	
	
	@Column
	public short getSentenceOrder() {
		return sentenceOrder;
	}
	public void setSentenceOrder(short sentenceOrder) {
		this.sentenceOrder = sentenceOrder;
	}
	

	
}

