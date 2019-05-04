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
@Table(name = "residents", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class Resident implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max = 50)
    private String firstname;
    
    @Size(min=3, max = 50)
    private String lastname;

    @Size(min=3, max = 50)
    private String username;

    @Size(max = 50)
    private String email;
    
    @Size(max = 50)
    private String contactno;
    
    @Size(max = 50)
    private String alternatecontact;

    @Size(min=6, max = 100)
    private String password;
    
    @Size(max = 70)
    @Column(name = "apartment")
    private String apartment;
    
    @Size(max = 70)
    @Column(name = "floornumber")
    private String floornumber;
    
    @Size(max = 70)
    @Column(name = "flatnumber")
    private String flatnumber;
    
    @Size(max = 255)
    @Column(name = "token")
    private String token;
    
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;
    
    @Column(name="gender")
	private Character gender;
    
    @Column(name = "age")
    private Double age;
    
    @Column(name="status")
	private Character status;
	
	@Column(name="loginallowed")
	private Boolean loginallowed;
    
	@Column(name="colone")
	private String colone;

	@Column(name="coltwo")
	private String coltwo;
    
    @Column(name = "societyid")
    private Long societyid;
    
	public Resident() {
		
	}

	public Resident(Long id) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getFloornumber() {
		return floornumber;
	}

	public void setFloornumber(String floornumber) {
		this.floornumber = floornumber;
	}

	public String getFlatnumber() {
		return flatnumber;
	}

	public void setFlatnumber(String flatnumber) {
		this.flatnumber = flatnumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Boolean getLoginallowed() {
		return loginallowed;
	}

	public void setLoginallowed(Boolean loginallowed) {
		this.loginallowed = loginallowed;
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

	public Long getSocietyid() {
		return societyid;
	}

	public void setSocietyid(Long societyid) {
		this.societyid = societyid;
	}

	@Override
	public String toString() {
		return "Resident [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", email=" + email + ", contactno=" + contactno + ", alternatecontact=" + alternatecontact
				+ ", password=" + password + ", apartment=" + apartment + ", floornumber=" + floornumber
				+ ", flatnumber=" + flatnumber + ", token=" + token + ", picture=" + picture + ", gender=" + gender
				+ ", age=" + age + ", status=" + status + ", loginallowed=" + loginallowed + ", colone=" + colone
				+ ", coltwo=" + coltwo + ", societyid=" + societyid + "]";
	}
    
}
