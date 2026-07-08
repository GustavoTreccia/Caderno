package com.example.treino.dto;

import java.util.Set;

public class UserDto {

	private long id;
	private String username;
	private Set<String> roles;
	
	public UserDto() {}

	public UserDto(long id, String username, Set<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	
}
