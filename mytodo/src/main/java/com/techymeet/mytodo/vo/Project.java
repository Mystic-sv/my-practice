package com.techymeet.mytodo.vo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "project",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "projectName")})
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	@Column(name="projectName",nullable = false)
	private String projectName;
	@Column(name="projectManager",nullable = false)
	private String projectManager;
	@Column(name="startDate",nullable = false)
	private LocalDate startDate;
	@Column(name="endDate",nullable = false)
	private LocalDate endDate;
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
