package org.doctorcrossref.system.behavior;

import java.util.HashMap;
import java.util.Map;

public class BehaviorableObject {

	private Map<String,IBehavior<? extends Object>> behaviorMap = 
			new HashMap<String,IBehavior<? extends Object>>();
	
	// ==============================================================
	// Methods
	// ==============================================================
	public IBehavior<? extends Object> addBehavior(
			IBehavior<? extends Object> behavior) {
		String name =behavior.getBehaviorName();
		return behaviorMap.put(name, behavior);
	}
	
	@SuppressWarnings("unchecked")
	public <TYPE> IBehavior<TYPE> removeBehavior(String name) {
		return (IBehavior<TYPE>) behaviorMap.remove(name);
	}
	
	@SuppressWarnings("unchecked")
	public <TYPE> IBehavior<TYPE> getBehavior(String name) {
		IBehavior<? extends Object> behavior = behaviorMap.get(name);
		if (behavior==null) 
			return null;
		return (IBehavior<TYPE>) behavior;
	}
	
	
}
