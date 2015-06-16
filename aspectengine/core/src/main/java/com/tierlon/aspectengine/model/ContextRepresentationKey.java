package com.tierlon.aspectengine.model;


/**
 * A specialized string that follows certain rules.
 * <ul>
 * <li>The string is lowercase.</li>
 * <li> The string is itself unique -- you cannot have two different instances of "a.b.c" </li> 
 * <li> There is no whitespace in the string.</li>
 * <li> Periods are used to separate families. </li>
 * <li> Only characters and numbers and dashes are allowed as the family names.  Periods are used
 * exclusively to separate families.  Example:  
 * "entity.film-industry.actors.charlie-chaplin.imdb-1942341" </li>
 * <li> The periods represent families with the smaller/descendant groupings increasing to the right. </li>
 * </ul>
 * ContextRepresentationKeys are inherently atomic and cannot represent multiple contexts-- although a context
 * may use multiple keys to represent a complex context.
 * 
 * @author justinanddiana
 *
 */
public class ContextRepresentationKey {

	private String delegate;
	private String parts[];
	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================
	/*package constructor */ 
	ContextRepresentationKey(String rawkey) {
		if (rawkey==null || rawkey.isEmpty()) {
			throw new IllegalArgumentException("Must have non-null,non-empty arguments: "+rawkey);
		}

		this.delegate=rawkey;
		this.parts = rawkey.split("\\.");
	}
	
	// ==============================================================
	// PUBLIC Methods 
	// ==============================================================
	public static boolean  validateKeyString(String key) {
		if (key==null) return false;
		if (key.isEmpty()) return false;
		
		//TODO: rest of key validation...
		// Validate lowercase only
		// Validate only alphanumeric and dash and period
		
		return true;
	}
	
	
	public String  getRawKey() { return delegate; }
	
	public boolean hasParts(ContextRepresentationKey key) {
		String[] myParts=delegate.split("\\.");
		String[] otherParts = key.delegate.split("\\.");
		
		if (otherParts.length<myParts.length)
	}
	
	// ==============================================================
	// Object Methods 
	// ==============================================================
	public boolean equals(Object arg0) { 
		if (arg0 instanceof ContextRepresentationKey) {
			return delegate.equals(((ContextRepresentationKey) arg0).delegate);
		}
		return false;
	}

	public int hashCode() {
		return delegate.hashCode();
	}
}

