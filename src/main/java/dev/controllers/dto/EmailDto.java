package dev.controllers.dto;

public class EmailDto {

	private String email;

	public EmailDto() {
		super();
	}

	public EmailDto(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
