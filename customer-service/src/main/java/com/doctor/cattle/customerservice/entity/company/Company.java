package com.doctor.cattle.customerservice.entity.company;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;




@Entity
@Table(name="company")
public class Company {

	private static final String PREFIX = "company_";
	
	@Column(name=PREFIX+"id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	
	@Column(name=PREFIX+"name")
	private String companyName;
	
	@Column(name=PREFIX + "access_granted")
	private Access accessGranted = Access.GRANTED ;
	
	@OneToOne(mappedBy="company",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private CompanyOwner owner;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	public CompanyOwner getOwner() {
		return owner;
	}

	public void setOwner(CompanyOwner owner) {
		this.owner = owner;
	}

	public Access getaccessGranted() {
		return accessGranted;
	}

	public void setaccessGranted(Access accessGranted) {
		this.accessGranted = accessGranted;
	}


	
	
}
