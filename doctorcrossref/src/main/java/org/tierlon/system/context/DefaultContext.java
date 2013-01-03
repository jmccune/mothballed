package org.tierlon.system.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultContext implements IContext {

	private HashMap<String,Object> implementation=new HashMap<String,Object>();

	private String contextName;
	private boolean isUniqueByName;
	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================	
	public DefaultContext(String contextName, boolean isUniqueByContextName) {
		if (contextName==null) {
			throw new NullPointerException();
		}
		this.contextName=contextName;
		this.isUniqueByName =isUniqueByContextName;
	}
	
	
	// ==============================================================
	// INTERFACE IMPLEMENTATION
	// ==============================================================
	
	@Override
	public String getContextName() {
		return contextName;
	}

	@Override
	public boolean isUniqueByContextName() {
		return isUniqueByName;
	}

	@Override
	public boolean containsKey(String arg0) {
		return implementation.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return implementation.containsValue(arg0);
	}

	@Override
	public Object get(String arg0) {
		return implementation.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return implementation.isEmpty();
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> arg0) {
		implementation.putAll(arg0);
	}

	@Override
	public Object remove(String arg0) {
		return implementation.remove(arg0);
	}

	@Override
	public int size() {
		return implementation.size();
	}


	@Override
	public Set<String> keySet() {
		return implementation.keySet();
	}


	@Override
	public Object put(String key, Object value) {
		return implementation.put(key,value);
	}

}
