package dev.controllers.dto;

public class EmailDto {

	/** email : String */
	private String email;

	/**
	 * Constructor
	 * 
	 */
	public EmailDto() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param email
	 */
	public EmailDto(String email) {
		super();
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
