package com.tierlon.aspectengine.model;

import java.util.Collection;


/**
 * This represents the context of information.  
 * 
 * In the real world, information is usually shared in contexts-- implicit and explicit.
 * If you and I are in the same culture, speaking the same language, from the same region,
 * we share the same context and therefore understanding about the meaning of certain words,
 * expressions, ideas, etc.  If we do not, a certain amount of misunderstanding can occur
 * as we try to interact. 
 * 
 * In any system where you are trying to generalize relations and information, you will
 * run into the problem of contexts.   This can get quite fuzzy, as in a user asking
 * for "I want the red cars."   Red (255,0,0) as an rgb value is quite precise, but
 * what exactly does "Red" as a word mean to the given user?
 * 
 * Note: This gets interesting.   Length for instance is a single dimension.
 * 								  Length in meters is a dual-context-- a dimension and a unit type.
 *    
 * @author justinanddiana
 */
public interface IContext extends IRootElement{
	
	
	/** Returns the single context representation if one exists, or null
	 * otherwise.
	 * 
	 * NOTE: hasContextKey may still return true to several different keys
	 * even if this returns non-null.  For example, this may return the
	 * key "a.b.c.d.e"  and may be said therefore to have context keys:
	 * "a.b", "a.b.c.d.e", "a.b.c", etc.
	 * 
	 * @return (null allowed) null if this object has no single representation,
	 * otherwise the single/simple aspect represented by this context.
	 */
	public ContextRepresentationKey getSingleRepresentation();
	
	
	/** What aspects/dimensions/etc are represented by this context. */
	public boolean hasContextKey(ContextRepresentationKey key);	
	
	/** What families are best represented by this context.
	 * This is subjective of course and the actual object decides
	 * to represent what it thinks the best familylike groupings are.
	 * @return (non-null, possibly empty) of the set of family groupings (if any)
	 * that make sense based on this context.
	 */
	public Collection<ContextRepresentationKey> getFamilylikeGroupings();
	
 
	/** Is the given object/element within the context defined by this object? 
	 * If the response is 1.0 or greater the answer is true.  If
	 * the response is -1.0 or lower the answer is false.   
	 * Between -1.0 and 1.0 is INDETERMINATE and RESERVED FOR FUTURE USE.
	 */
	public float withinContext(IRootElement element);
}