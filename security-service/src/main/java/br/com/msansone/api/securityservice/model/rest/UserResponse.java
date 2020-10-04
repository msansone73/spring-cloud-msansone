package br.com.msansone.api.securityservice.model.rest;

import br.com.msansone.api.securityservice.model.User;

public class UserResponse  {

	private ReturnError returnError;
	private User user;
	
	public ReturnError getReturnError() {
		return returnError;
	}
	public void setReturnError(ReturnError returnError) {
		this.returnError = returnError;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	
}
