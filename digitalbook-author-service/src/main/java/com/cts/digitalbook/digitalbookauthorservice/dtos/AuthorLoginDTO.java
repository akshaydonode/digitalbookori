package com.cts.digitalbook.digitalbookauthorservice.dtos;

public class AuthorLoginDTO {

	private String authorEmail;
	private String password;

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}