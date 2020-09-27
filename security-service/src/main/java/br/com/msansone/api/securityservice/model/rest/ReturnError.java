package br.com.msansone.api.securityservice.model.rest;

public class ReturnError {
	
	private String id;
	private String message;
	private String source;
	
	
	
	public ReturnError(String message, String source) {
		super();
		this.message = message;
		this.source = source;
	}

	public ReturnError(String id, String message, String source) {
		super();
		this.id = id;
		this.message = message;
		this.source = source;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
