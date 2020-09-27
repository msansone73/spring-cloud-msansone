package br.com.msansone.api.securityservice.model;

import br.com.msansone.api.securityservice.model.rest.ReturnError;

public abstract class AbstractModel {

	private ReturnError returnError;


	public ReturnError getReturnError() {
		return returnError;
	}

	public void setReturnError(ReturnError returnError) {
		this.returnError = returnError;
	}
	
	
}
