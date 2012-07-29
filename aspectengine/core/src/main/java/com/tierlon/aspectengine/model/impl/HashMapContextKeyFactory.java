package com.tierlon.aspectengine.model.impl;

import java.util.Collections;
import java.util.Set;

import com.tierlon.aspectengine.model.ContextRepresentationKey;
import com.tierlon.aspectengine.model.ContextRepresentationKeyBuilder;
import com.tierlon.aspectengine.model.IContextKeyFactory;

public class HashMapContextKeyFactory 
	extends ContextRepresentationKeyBuilder 
	implements IContextKeyFactory {
	
	
	public ContextRepresentationKey getKey(String key) {
		if (!ContextRepresentationKey.validateKeyString(key)) {
			throw new IllegalArgumentException("Violates key validation rules!");
		}
		//TODO: real implementation
		return super.createKey(key);
	}

	public Set<ContextRepresentationKey> getChildKeys(
			ContextRepresentationKey key) {
		//TODO: real implementation
		return Collections.emptySet();
	}

	public Set<ContextRepresentationKey> getSimilarKeys(String keyPart) {
		//TODO: real implementation
		return Collections.emptySet();
	}

}
