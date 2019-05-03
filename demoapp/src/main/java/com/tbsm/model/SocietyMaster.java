/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbsm.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "society_master")
public class SocietyMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "society_name")
    private String societyName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "fax")
    private String fax;
    @Size(max = 45)
    @Column(name = "contact_no")
    private String contactNo;
    @Size(max = 45)
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "apartments")
    private Integer apartments;
    @Size(max = 70)
    @Column(name = "address_one")
    private String addressOne;
    @Size(max = 70)
    @Column(name = "address_two")
    private String addressTwo;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 20)
    @Column(name = "postal_code")
    private String postalCode;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    @Column(name = "status")
    private Character status;
    @Size(max = 70)
    @Column(name = "society_code")
    private String societyCode;

    public SocietyMaster() {
    	
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getApartments() {
		return apartments;
	}

	public void setApartments(Integer apartments) {
		this.apartments = apartments;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getSocietyCode() {
		return societyCode;
	}

	public void setSocietyCode(String societyCode) {
		this.societyCode = societyCode;
	}

	@Override
	public String toString() {
		return "SocietyMaster [societyId=" + id + ", societyName=" + societyName + ", email=" + email + ", fax="
				+ fax + ", contactNo=" + contactNo + ", phoneNo=" + phoneNo + ", apartments=" + apartments
				+ ", addressOne=" + addressOne + ", addressTwo=" + addressTwo + ", city=" + city + ", postalCode="
				+ postalCode + ", state=" + state + ", country=" + country + ", status=" + status + ", societyCode="
				+ societyCode + "]";
	}
    
}
