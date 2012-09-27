package org.tierlon.system.evaluation;

import java.util.Collections;
import java.util.List;

public class AcceptanceResult implements IAcceptanceResult {

	private double acceptanceValue;
	private String reason;
	
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public AcceptanceResult(boolean success ,String reason) {
		acceptanceValue=(success ? 1.0 : -1.0);
		this.reason = reason;
	}
	
	public AcceptanceResult(double  acceptanceValue ,String reason) {
		this.acceptanceValue=acceptanceValue;
		this.reason = reason;
	}
	// ==============================================================
	// IMPLEMENTATION 
	// ==============================================================
	@Override
	public boolean isAcceptable() {
		return acceptanceValue>=1.0;
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
