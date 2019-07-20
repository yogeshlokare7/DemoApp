package com.tbsm.dto;

public class SocietyCounterDto {
	private Long userCount;
	private Long staffCount;
	private Long securityCount;
	private Long residentCount;
	
	
	public SocietyCounterDto() {
	}
	
	
	public SocietyCounterDto(Long userCount, Long staffCount, Long securityCount, Long residentCount) {
		this.userCount = userCount;
		this.staffCount = staffCount;
		this.securityCount = securityCount;
		this.residentCount = residentCount;
	}


	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	public Long getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(Long staffCount) {
		this.staffCount = staffCount;
	}
	public Long getSecurityCount() {
		return securityCount;
	}
	public void setSecurityCount(Long securityCount) {
		this.securityCount = securityCount;
	}
	public Long getResidentCount() {
		return residentCount;
	}
	public void setResidentCount(Long residentCount) {
		this.residentCount = residentCount;
	}
	@Override
	public String toString() {
		return "SocietyCounterDto [userCount=" + userCount + ", staffCount=" + staffCount + ", securityCount="
				+ securityCount + ", residentCount=" + residentCount + "]";
	}
}
