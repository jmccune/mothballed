package org.doctorcrossref.system.behavior;

import org.tierlon.system.evaluation.IAcceptanceResult;

public interface IBehavior<ResultTYPE> {

	public String getBehaviorName();
	
	public boolean isRunning();
	public IAcceptanceResult canRunBehavior();
	public ResultTYPE runBehavior(Object... args);
	public Class<ResultTYPE> getResultTypeClass();
	
}
