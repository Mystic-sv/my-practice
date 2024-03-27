package com.techymeet.mytodo.bo;




public class Task {
	
	private int taskId;
	private String taskName;
	private String taskType;
	private String taskOwner;
	private String assignedProject;
	private String startDate;
	private String endDate;
	private boolean taskStatus;
	private long projectId;
	private long employeeId;

	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
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
	public String getTaskOwner() {
		return taskOwner;
	}
	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public boolean isTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAssignedProject() {
		return assignedProject;
	}
	public void setAssignedProject(String assignedProject) {
		this.assignedProject = assignedProject;
	}

}
