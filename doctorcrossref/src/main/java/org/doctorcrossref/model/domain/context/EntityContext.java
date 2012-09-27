package org.doctorcrossref.model.domain.context;

import java.util.Collection;
import java.util.Set;

import org.doctorcrossref.model.domain.EntityObject;


/**
 * An EntityContext is the set of entities that describes a particular context
 * for an entity.  
 * 
 * For example, given a particular sentence (or paragraph, or article, etc)
 * where was it found?
 * That answer might be described by the set: <br>
 *  (   <EntityObject:Volume>
 *      <EntityObject:Book>
 *      <EntityObject:Chapter>
 *      <EntityObject:ParagraphNumber>
 *      <EntityObject:SentenceNumber>
 *      <EntityObject:Author>
 *      <EntityObject:Author>
 *      <EntityObject:Publisher>
 *  )
 *  
 *  or possibly:
 *  (   <EntityObject:Book>
 *      <EntityObject:Chapter>
 *      <EntityObject:ParagraphNumber>
 *      <EntityObject:SentenceNumber>
 *      <EntityObject:Author>
 *  )
 *  
 *  As might be suggested by the example, certain entities may be required
 *  (like SentenceNumber assumes that there is a paragraph number, paragraph
 *  number assumes that there is a particular chapter).
 *  
 *  ??This Object is is a RUNTIME construct and doesn't exist as a 
 *  formal entity within the datastore??   (Or should it...)
 *  
 * @author justinanddiana
 *
 */
public class EntityContext {

	/** The set of entity objects that lends context to another entity object.*/
	private Set<EntityObject> entityObjects;
	
	
	// ==============================================================
	// DELEGATE METHODS
	// ==============================================================
	public boolean add(EntityObject e) {
		return entityObjects.add(e);
	}

	public boolean addAll(Collection<? extends EntityObject> c) {
		return entityObjects.addAll(c);
	}

	public void clear() {
		entityObjects.clear();
	}

	public boolean contains(Object o) {
		return entityObjects.contains(o);
	}

	public boolean remove(Object o) {
		return entityObjects.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return entityObjects.removeAll(c);
	}

	public int size() {
		return entityObjects.size();
	}
		
	
}
