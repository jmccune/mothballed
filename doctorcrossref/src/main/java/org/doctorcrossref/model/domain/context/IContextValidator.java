package org.doctorcrossref.model.domain.context;

import org.tierlon.evaluation.IAcceptanceResult;

public interface IContextValidator {

	public IAcceptanceResult getAcceptanceEvaluation(EntityContext context);	
	
}
