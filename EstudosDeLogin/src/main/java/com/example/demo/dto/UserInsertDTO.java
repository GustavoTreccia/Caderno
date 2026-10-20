package com.example.demo.dto;

import com.example.demo.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

private static final long serialVersionUID = 600893824804291074L;
	
	private String password;
	
	public UserInsertDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
