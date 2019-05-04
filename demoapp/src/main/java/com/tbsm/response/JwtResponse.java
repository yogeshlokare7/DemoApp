package com.tbsm.response;

public class JwtResponse {
	private Long userid;
	private String name;
	private String username;
	
	public JwtResponse() {
	}
	
	public JwtResponse(Long userid, String name, String username) {
		this.userid = userid;
		this.name = name;
		this.username = username;
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}