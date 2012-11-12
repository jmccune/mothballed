package org.tierlon.evaluation;

public interface IEvaluate<ObjectTYPE> {

	IAcceptanceResult evaluate(ObjectTYPE object, Object... contextualInfo);
}
