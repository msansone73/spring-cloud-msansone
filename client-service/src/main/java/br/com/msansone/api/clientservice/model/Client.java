package br.com.msansone.api.clientservice.model;

import javax.persistence.Entity;

@Entity
public class Client extends AbstractModel {

	private String name;
	private String login;
	private String email;
	private String telemovel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelemovel() {
		return telemovel;
	}
	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}

	
	
	
	
}
