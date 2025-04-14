package com.everhealth.integration.zbfspublisher.modal;

import java.util.List;

public class UpdateOrderZbRequest {
	
	private String orderDate;
	private String existingAccountNumber;
	private List<SubscriptionDetails> subscriptions;
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getExistingAccountNumber() {
		return existingAccountNumber;
	}
	public void setExistingAccountNumber(String existingAccountNumber) {
		this.existingAccountNumber = existingAccountNumber;
	}
	public List<SubscriptionDetails> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<SubscriptionDetails> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	@Override
	public String toString() {
		return "UpdateOrderZbRequest [orderDate=" + orderDate + ", existingAccountNumber=" + existingAccountNumber
				+ ", subscriptions=" + subscriptions + "]";
	}
		

}
