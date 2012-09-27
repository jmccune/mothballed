package org.doctorcrossref.model.domain.context;

import org.tierlon.system.evaluation.IAcceptanceResult;

public interface IContextValidator {

	public IAcceptanceResult getAcceptanceEvaluation(EntityContext context);	
	
}
