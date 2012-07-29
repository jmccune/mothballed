package com.tierlon.aspectengine.model.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.tierlon.aspectengine.model.ContextRepresentationKey;
import com.tierlon.aspectengine.model.IContext;
import com.tierlon.aspectengine.model.IRootElement;

public  class AbstractContext 
		extends AbstractRootElement
		implements IContext {

	private ContextRepresentationKey myKey;
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================
	public AbstractContext(IRootElement origin, ContextRepresentationKey key) {
		super(origin);
		
	}
	// ==============================================================
	// INTERFACE Operations
	// ==============================================================
	public ContextRepresentationKey getSingleRepresentation() {
		return myKey;
	}
	public boolean hasContextKey(ContextRepresentationKey key) {
		// TODO Auto-generated method stub
		return false;
	}
	public Collection<ContextRepresentationKey> getFamilylikeGroupings() {
		// TODO Auto-generated method stub
		return null;
	}
	public float withinContext(IRootElement element) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
//	public boolean isAtomicContext() {
//		return true;
//	}
//
//	public Collection<ContextRepresentationKey> getContextualRepresentationKeys() {
//		return Collections.singleton(myKey);
//	}
//
//	public Map<String, Object> getContextInformation() {
//		return Collections.emptyMap();
//	}
//	
//	/**
//	 * Defaults to a definition that uses the given element's associated contexts
//	 */
//	public float withinContext(IRootElement element) {
//		Set<IContext> contexts=
//				element.getAssociatedContexts(this);
//		
//		for (IContext context : contexts) {
//			if (context.getContextualRepresentationKeys().contains(this.myKey)) {
//				return 1.0f;
//			}
//		}
//		return -1.0f;
//	}
}
