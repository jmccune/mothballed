package com.tierlon.aspectengine.model;

import java.util.Set;

public interface IContextKeyFactory {
	
	/**
	 * Gets the key that matches the given string.
	 * 
	 * @param key the key must be a key that conforms to the rules
	 * 	of the ContextRepresentationKey. 
	 * @throws IllegalArgumentException if any of the many rules that violate the string rules are violated.
	 * 
	 * @return (non-null) the unique key that represents the given key.
	 */
	public ContextRepresentationKey getKey(String key);
	
	
	
	/**
	 * Gets the next level of keys-- for example, given key "a.b.c" gets "a.b.c.d1","a.b.c.d2", etc.
	 * but not keys "a.b.c.d1.e","a.b.c.d2.e.f.g",etc.
	 * @param key
	 * @return
	 */
	public Set<ContextRepresentationKey> getChildKeys(ContextRepresentationKey key);	
	
	/**
	 * Gets keys that are similar to any part of the given key.
	 * @param keyPart
	 * @return
	 */
	public Set<ContextRepresentationKey> getSimilarKeys(String keyPart);
}
