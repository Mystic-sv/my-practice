package com.techymeet.mytodo.vo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "role",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "roleName")})
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column(name="roleName",nullable = false)
	private String roleName;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
