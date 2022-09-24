package com.doctor.cattle.customerservice.entity.farm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.company.Company;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;

@Entity
@Table(name="farm")
public class Farm {
	
	private static final String PREFIX = "farm_";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=PREFIX+"id")
	private Long id;
	
	@Column(name=PREFIX+"city")
	private String city;
	
	@Column(name=PREFIX+"name")
	private String name;
	
	@Column(name=PREFIX+"liveStock")
	private int liveStock;
	

	@Column(name=PREFIX + "access_granted")
	private Access accessGranted = Access.GRANTED ;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name=PREFIX+"owner")
	private CompanyOwner owner;
	
	@OneToMany(mappedBy="farm",cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	private List<FarmUser> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLiveStock() {
		return liveStock;
	}

	public void setLiveStock(int liveStock) {
		this.liveStock = liveStock;
	}

	public CompanyOwner getOwner() {
		return owner;
	}

	public void setOwner(CompanyOwner owner) {
		this.owner = owner;
	}

	public List<FarmUser> getUsers() {
		return users;
	}

	public void setUsers(List<FarmUser> users) {
		this.users = users;
	}

	public Access getaccessGranted() {
		return accessGranted;
	}

	public void setaccessGranted(Access accessGranted) {
		this.accessGranted = accessGranted;
	}
	
	public Company getCompany() {
		if (owner == null) {
			return null;
		}
		return owner.getCompany();
	}
	

}
