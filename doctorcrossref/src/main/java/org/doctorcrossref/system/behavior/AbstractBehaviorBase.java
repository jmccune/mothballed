package org.doctorcrossref.system.behavior;

import org.tierlon.system.evaluation.AcceptanceResult;
import org.tierlon.system.evaluation.IAcceptanceResult;

public abstract class AbstractBehaviorBase<ResultTYPE> 
	implements IBehavior<ResultTYPE> {

	private String behaviorName;
	private boolean isRunning=false;
	
	
	// ==============================================================
	// CONSTRUCTION 
	// ==============================================================
	public AbstractBehaviorBase(String behaviorName) {
		this.behaviorName = behaviorName;
	}
	
	// ==============================================================
	// (Partial) IMPLEMENTATION
	// ==============================================================
	@Override
	public String getBehaviorName() {
		return behaviorName;
	}

	@Override
	public boolean isRunning() {
		//Default assumption is non-concurrent or asynchronous...
		return isRunning;
	}

	@Override
	public IAcceptanceResult canRunBehavior() {
		return new AcceptanceResult(true,"Able to run "+behaviorName);
	}
}
