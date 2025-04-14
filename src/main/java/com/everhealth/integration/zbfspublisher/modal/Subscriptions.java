package com.everhealth.integration.zbfspublisher.modal;

import java.util.List;

public class Subscriptions {
	
	private String subscriptionNumber;
	private List<OrderActions> orderActions;
	
	public String getSubscriptionNumber() {
		return subscriptionNumber;
	}
	public void setSubscriptionNumber(String subscriptionNumber) {
		this.subscriptionNumber = subscriptionNumber;
	}
	public List<OrderActions> getOrderActions() {
		return orderActions;
	}
	public void setOrderActions(List<OrderActions> orderActions) {
		this.orderActions = orderActions;
	}
	
	@Override
	public String toString() {
		return "Subscriptions [subscriptionNumber=" + subscriptionNumber + ", orderActions=" + orderActions + "]";
	}
	
}
