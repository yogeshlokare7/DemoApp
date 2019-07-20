package com.tbsm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "security_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class SecurityUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    
    private String lastname;

    private String username;

    private String email;

    private String contactno;
    
    private String alternatecontact;

    private String password;
    
    @Column(name = "streetno")
    private String streetno;
    
    @Column(name = "streetname")
    private String streetname;
    
    @Column(name = "postalcode")
    private String postalcode;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "country")
    private String country;
    
    @Size(max = 255)
    @Column(name = "token")
    private String token;
    
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;
    
    @Column(name="gender")
	private Character gender;
    
    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;
    
    @Column(name = "rating")
    private String rating;
    
    @Column(name="status")
	private Character status;
	
	@Column(name="loginallowed")
	private Boolean loginallowed;
    
	@Column(name="colone")
	private String colone;

	@Column(name="coltwo")
	private String coltwo;
	
    @Column(name = "apartment")
    private String apartment;
    
    @Column(name = "societyid")
    private Long societyid;

    public SecurityUser() {
    	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public Long getSocietyid() {
		return societyid;
	}

	public void setSocietyid(Long societyid) {
		this.societyid = societyid;
	}
	public String getAlternatecontact() {
		return alternatecontact;
	}

	public void setAlternatecontact(String alternatecontact) {
		this.alternatecontact = alternatecontact;
	}

	@Override
	public String toString() {
		return "SecurityUser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", email=" + email + ", contactno=" + contactno + ", password=" + password + ", streetno="
				+ streetno + ", streetname=" + streetname + ", postalcode=" + postalcode + ", city=" + city
				+ ", province=" + province + ", country=" + country + ", token=" + token + ", picture=" + picture
				+ ", gender=" + gender + ", dob=" + dob + ", rating=" + rating + ", status=" + status
				+ ", loginallowed=" + loginallowed + ", colone=" + colone + ", coltwo=" + coltwo + ", apartment="
				+ apartment + ", societyid=" + societyid + "]";
	}
    
}
