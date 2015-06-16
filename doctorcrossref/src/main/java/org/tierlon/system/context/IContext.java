package org.tierlon.system.context;

import java.util.Map;
import java.util.Set;


/**
 * Defines a context for a set or family of related functionality, operations
 * or transformations.
 * 
 * This operates as a bag of data that may be managed and transformed.
 * 
 * ContextSkins may plug-in to this context and determine whether they
 * are valid -- thus enabling some level of type-saftey, while allowing
 * everything else to plug and play with the one common argument
 * that defines an arbitrary set of arguments.
 * 
 * @author justinanddiana
 *
 */
public interface IContext  {
	
	// ----------------------------------------
	// 
	// ----------------------------------------
	/** 
	 * Indicates a human-readable name for the context 
	 * represented by this object.
	 * @return
	 */
	public String getContextName();
	
	/** 
	 * Indicates whether the context string can be 
	 * used as a key or not-- e.g. is the context string
	 * for this particular implementation of context
	 * guaranteed to be unique for every context (true) or 
	 * (false) does the context string merely define a 
	 * usage class.
	 * 
	 * @return true if the context string may be used as
	 * a unique key, false otherwise.
	 */
	public boolean isUniqueByContextName();
	
	// ----------------------------------------
	// READ FUNCTIONS (Bag)
	// ----------------------------------------
	public boolean containsKey(String key);
	public boolean containsValue(Object value);
	public boolean isEmpty();
	public int size();	
	public Object get(String key);
	
	public Set<String> keySet();

	// ----------------------------------------
	// MODIFY FUNCTIONS (Bag)
	// ----------------------------------------
	public Object put(String key, Object value);
	public void putAll(Map<? extends String, ? extends Object> m);
	public Object remove(String key);

}

