package com.doctor.cattle.customerservice.entity.farm_user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.role.Role;

@Entity
@Table(name="farm_user")
public class FarmUser {
	
	private static final String PREFIX = "farm_user_";


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=PREFIX+"id")
	private Long id;
	
	@Column(name=PREFIX+"userName")
	private String userName;
	
	@Column(name=PREFIX+"password")
	private String password;
	
	@Column(name=PREFIX+"firstName")
	private String firstName;
	
	@Column(name=PREFIX+"lastName")
	private String lastName;
	
	@Column(name=PREFIX+"registrationNumber")
	private String registrationNumber;
	
	@Column(name=PREFIX+"is_owner")
	private Boolean isOwner;
	
	@Column(name=PREFIX+"role")
	private Role role;
	
    @Column(name=PREFIX + "access_granted")
	private Access accessGrantedd = Access.GRANTED ;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="farm_id")
	private Farm farm;


    @Column(name=PREFIX + "phone_number")
	private String phoneNumber ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Access getaccessGrantedd() {
		return accessGrantedd;
	}

	public void setaccessGrantedd(Access accessGranted) {
		this.accessGrantedd = accessGranted;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}
