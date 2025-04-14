package com.everhealth.integration.zbfspublisher.modal;

import java.util.List;

public class OrderActions {
	
	private String type;
	private List<TriggerDates> triggerDates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<TriggerDates> getTriggerDates() {
		return triggerDates;
	}
	public void setTriggerDates(List<TriggerDates> triggerDates) {
		this.triggerDates = triggerDates;
	}
	
	@Override
	public String toString() {
		return "OrderAction [type=" + type + ", triggerDates=" + triggerDates + "]";
	}
	
}
