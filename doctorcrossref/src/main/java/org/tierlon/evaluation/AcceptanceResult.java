package org.tierlon.evaluation;

import java.util.Collections;
import java.util.List;

import org.tierlon.system.helper.StringHelper;

public class AcceptanceResult implements IAcceptanceResult {

	private double acceptanceValue;
	private String reason;
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	static public AcceptanceResult indeterminate(String reason) {
		return new AcceptanceResult(0,reason);
	}
	
	static public AcceptanceResult negate(IAcceptanceResult original) {
		if (original==null) {
			throw new NullPointerException();
		}
		
		double value = original.asNumber();
		value = -value;
		String reason = "Negated Logically("+ original.getReason()+")";
		
		return new AcceptanceResult(value,reason);		
	}
	
	
	public AcceptanceResult(boolean success ,String reason) {
		acceptanceValue=(success ? 1.0 : -1.0);
		this.reason = reason;
	}
	
	public AcceptanceResult(double  acceptanceValue ,String reason) {
		this.acceptanceValue=acceptanceValue;
		this.reason = reason;
	}
	// ==============================================================
	// OBJECT
	// ==============================================================
	public String toString() {
		
		String meaning = "indeterminate";
		if (isAcceptable()) {
			meaning = "acceptable";
		}
		if (isUnacceptable()) {
			meaning = "unacceptable";
		}
		return StringHelper.objectToString("AcceptanceResult", 
				"value",acceptanceValue,"means",meaning,"reason",reason);
	}
	// ==============================================================
	// IMPLEMENTATION 
	// ==============================================================
	@Override
	public boolean isAcceptable() {
		return acceptanceValue>=1.0;
	}

	
	@Override
	public boolean isUnacceptable() {
		return acceptanceValue<=-1.0;
	}
	
	@Override
	public boolean isIndeterminate() {
		return -1.0<acceptanceValue && acceptanceValue<1.0;
	}
	
	@Override
	public String getReason() {
		return reason;
	}

	@Override
	public double asNumber() {
		return acceptanceValue;
	}

	@Override
	public List<IAcceptanceResult> getSupportingInfo() {
		return Collections.emptyList();
	}
}
