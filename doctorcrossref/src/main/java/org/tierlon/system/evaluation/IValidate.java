package org.tierlon.system.evaluation;

public interface IValidate<ObjectTYPE> {

	IAcceptanceResult evaluate(ObjectTYPE object, Object... contextualInfo);
}
