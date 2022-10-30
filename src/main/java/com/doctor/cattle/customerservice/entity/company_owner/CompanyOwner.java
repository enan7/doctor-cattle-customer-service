package com.doctor.cattle.customerservice.entity.company_owner;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.company.Company;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.role.Role;

@Entity
@Table(name ="company_owner")
public class CompanyOwner {

	private static final String PREFIX= "owner_";
	
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
	
	
	@JoinColumn(name=PREFIX+"company")
	@OneToOne(fetch=FetchType.EAGER)
	private Company company;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	private List<Farm> farms;
	
	
	@Column(name=PREFIX+"role")
	private Role role;


	@Column(name=PREFIX + "access_granted")
	private Access accessGranted = Access.GRANTED ;

	@Column(name=PREFIX + "phoneNumber")
	private String phoneNumber  ;
	
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


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	public Boolean getIsOwner() {
		return true;
	}


	public List<Farm> getFarms() {
		return farms;
	}


	public void setFarms(List<Farm> farms) {
		this.farms = farms;
	}


	public Access getaccessGranted() {
		return accessGranted;
	}


	public void setaccessGranted(Access accessGranted) {
		this.accessGranted = accessGranted;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
