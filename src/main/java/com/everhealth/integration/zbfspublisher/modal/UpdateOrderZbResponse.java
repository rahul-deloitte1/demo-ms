package com.everhealth.integration.zbfspublisher.modal;

public class UpdateOrderZbResponse {
	
	private String accountNumber;
	private boolean success;
	private String message;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "UpdateOrderZbResponse [accountNumber=" + accountNumber + ", success=" + success + ", message=" + message
				+ "]";
	}

}
