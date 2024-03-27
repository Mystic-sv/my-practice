package com.techymeet.mytodo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "login",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "userName")
		 
})
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id",nullable = false)
	private long userId;
	@Column(name="userName",nullable = false)
	private String userName;
	@Column(name="password",nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(name="isDelete")
	private boolean isDelete;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	private Role role;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
		this.password = password;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	

}
