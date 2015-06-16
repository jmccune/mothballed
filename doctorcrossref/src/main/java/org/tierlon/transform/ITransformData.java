package org.tierlon.transform;

public interface ITransformData<Object1TYPE,ResultTYPE> {

	ResultTYPE transformData(Object1TYPE data,Object... contextualInfo);
	
}
