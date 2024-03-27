package com.techymeet.mytodo.vo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	@Column(name="taskName",nullable = false)
	private String taskName;
	@Column(name="taskType",nullable = false)
	private String taskType;
	@Column(name="startDate",nullable = false)
	private LocalDate startDate;
	@Column(name="endDate",nullable = false)
	private LocalDate endDate;
	private boolean taskStatus;
	
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
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
	public boolean isTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
