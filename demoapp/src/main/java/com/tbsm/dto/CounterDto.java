package com.tbsm.dto;

public class CounterDto {
	
	private Long societyCount;
	private Long societyAdminCount;
	private Long societyUserCount;
	private Long salesCount;
	
	public CounterDto() {
	}
	public CounterDto(Long societyCount, Long societyAdminCount, Long societyUserCount, Long salesCount) {
		this.societyCount = societyCount;
		this.societyAdminCount = societyAdminCount;
		this.societyUserCount = societyUserCount;
		this.salesCount = salesCount;
	}
	public Long getSocietyCount() {
		return societyCount;
	}
	public void setSocietyCount(Long societyCount) {
		this.societyCount = societyCount;
	}
	public Long getSocietyAdminCount() {
		return societyAdminCount;
	}
	public void setSocietyAdminCount(Long societyAdminCount) {
		this.societyAdminCount = societyAdminCount;
	}
	public Long getSocietyUserCount() {
		return societyUserCount;
	}
	public void setSocietyUserCount(Long societyUserCount) {
		this.societyUserCount = societyUserCount;
	}
	public Long getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(Long salesCount) {
		this.salesCount = salesCount;
	}
	

}
