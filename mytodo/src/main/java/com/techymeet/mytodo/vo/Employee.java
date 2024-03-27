package com.techymeet.mytodo.vo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "employee",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "emailId")
		 
})
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	@Column(name="firstName",nullable = false)
	private String firstName;
	@Column(name="lastName",nullable = false)
	private String lastName;
	@Column(name="emailId",nullable = false)
	private String emailId;
	@Column(name="password",nullable = false)
	private String password;
	@Column(name="mobileNumber",nullable = false,unique = true)
	private Long mobileNumber;
	@Column(name="address",nullable = false)
	private String address;
	@Column(name="city",nullable = false)
	private String city;
	@Column(name="state",nullable = false)
	private String state;
	@Column(name="country",nullable = false)
	private String country;
	@Column(name="isDelete")
	private boolean isDelete=false;
	@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
	private Login login;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
