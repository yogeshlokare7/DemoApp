package com.tbsm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "daily_staff")
public class DailyStaff  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max = 50)
    private String firstname;
    
    @Size(min=3, max = 50)
    private String lastname;

    @Size(max = 50)
    private String email;
    
    @Size(max = 50)
    private String contactno;
    
    @Size(max = 50)
    private String alternatecontact;
    
	@Column(name="profile")
	private String profile;
    
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;
    
    @Column(name="gender")
	private Character gender;
    
    @Column(name = "age")
    private Double age;
    
    @Column(name="status")
	private Character status;
    
    @Column(name = "societyid")
    private Long societyid;
    
    @Column(name = "residentid")
    private Long residentid;
    
    @Size(max = 70)
    @Column(name = "streetno")
    private String streetno;
    
    @Size(max = 70)
    @Column(name = "streetname")
    private String streetname;
    
    @Size(max = 20)
    @Column(name = "postalcode")
    private String postalcode;
    
    @Size(max = 70)
    @Column(name = "city")
    private String city;
    
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    
	@Column(name="colone")
	private String colone;

	@Column(name="coltwo")
	private String coltwo;

	public DailyStaff() {
		
	}

	public DailyStaff(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getAlternatecontact() {
		return alternatecontact;
	}

	public void setAlternatecontact(String alternatecontact) {
		this.alternatecontact = alternatecontact;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Long getSocietyid() {
		return societyid;
	}

	public void setSocietyid(Long societyid) {
		this.societyid = societyid;
	}

	public Long getResidentid() {
		return residentid;
	}

	public void setResidentid(Long residentid) {
		this.residentid = residentid;
	}

	public String getStreetno() {
		return streetno;
	}

	public void setStreetno(String streetno) {
		this.streetno = streetno;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getColone() {
		return colone;
	}

	public void setColone(String colone) {
		this.colone = colone;
	}

	public String getColtwo() {
		return coltwo;
	}

	public void setColtwo(String coltwo) {
		this.coltwo = coltwo;
	}
}
