package com.tierlon.aspectengine.model;

import java.util.Collection;

import com.tierlon.aspectengine.model.impl.AbstractRootElement;

public interface IRootElement {
	
	static public final IRootElement SYSTEM_ROOT=AbstractRootElement.makeSystemRootElement();
	
	/**
	 * Gets the origin(s) of this object.
	 * Origin means information about or the actual representation of the creator of this object. 
	 * The result may never be null, but may use system constants to indicate a particular origin 
	 * (ANONYMOUS_USER, SYSTEM_ROOT, etc.)
	 * 
	 * @return (non-null, non-empty) the origin(s) of this object.
	 */
	public Collection<IRootElement> getOrigins();
	
	
	/** What aspects/dimensions/etc are represented by this object (and its
	 *  associated information). */
	public IContext getContextualRepresentation();
	
	/**
	 * Gets the information associated with this data element (if any).
	 * For example, there may be reviews, tags, citations, background, etc. 
	 * associated with this data element.
	 * 
	 * Note: It only gets information directly associated with this element.
	 * For instance, if you were to search the new testament, you wouldn't get
	 * all the verses or sentences, but you would get all the books.  E.g. you only
	 * get your friends, not your friends of friends.
	 * 
	 * @param context (null allowed) If non-null indicates the context for the results.
	 * 	[e.g. get only the information within the context given. ] 
	 * 
	 * @return (non-null, empty allowed) associated information.
	 */
	public Collection<IRootElement> getAssociatedInformation(IRootElement context);
		
	
}
