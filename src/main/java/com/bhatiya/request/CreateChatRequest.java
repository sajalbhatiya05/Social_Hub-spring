package com.bhatiya.request;

public class CreateChatRequest {
	
	private Integer userId;
	
	public CreateChatRequest() {
		// TODO Auto-generated constructor stub
	}

	public CreateChatRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	 
}
