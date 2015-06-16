package com.tierlon.aspectengine.model.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.tierlon.aspectengine.model.IContext;
import com.tierlon.aspectengine.model.IRootElement;


/**
 * Represents a simple implementation of a root element with one origin,
 * no associated information/contexts.
 * 
 * @author justinanddiana
 */
public class AbstractRootElement implements IRootElement {
	
	/** Where did this data originate? 
	 * (Non-null, empty allowed) */
	private Collection<IRootElement>   origins;
	private static final IRootElement SYSTEM_ROOT=new AbstractRootElement();
	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================
	public AbstractRootElement(IRootElement origin) {
		if (origin==null) throw new NullPointerException();
		origins = Collections.singleton(origin);
	}
	
	
	public static IRootElement makeSystemRootElement() {
		return SYSTEM_ROOT;
	}
	
	private AbstractRootElement() {
		if (SYSTEM_ROOT!=null) {
			throw new IllegalStateException();
		}
		origins= Collections.singleton((IRootElement)this);
	}


	
	// ==============================================================
	// PUBLIC Interface
	// ==============================================================


	public Collection<IRootElement> getOrigins() {
		return origins;
	}


	public IContext getContextualRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}


	public Collection<IRootElement> getAssociatedInformation(
			IRootElement context) {
		return Collections.emptyList();
	}

}
