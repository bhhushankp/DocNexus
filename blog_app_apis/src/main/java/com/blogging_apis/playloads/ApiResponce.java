package com.blogging_apis.playloads;

public class ApiResponce {

	private String message;
	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponce(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "ApiResponce [message=" + message + ", success=" + success + "]";
	}
	private boolean success;
	
}
