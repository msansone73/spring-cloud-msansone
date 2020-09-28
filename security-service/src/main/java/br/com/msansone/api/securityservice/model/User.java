package br.com.msansone.api.securityservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tuser")
public class User extends AbstractModel {


	private String login;
	private String password;
	private Long status;

	
	public User() {
	}

	public User( String login, String password, Long status) {
		this.login = login;
		this.password = password;
		this.status = status;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	
}
