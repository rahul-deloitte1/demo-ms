package com.everhealth.integration.zbfspublisher.modal;

public class TriggerDates {
	
	private String name;
	private String triggerDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTriggerDate() {
		return triggerDate;
	}
	public void setTriggerDate(String triggerDate) {
		this.triggerDate = triggerDate;
	}
	
	@Override
	public String toString() {
		return "TriggerDates [name=" + name + ", triggerDate=" + triggerDate + "]";
	}

}
