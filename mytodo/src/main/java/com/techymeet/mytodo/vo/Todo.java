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


@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "username",nullable = false)
	private String username;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@Column(name = "target_date",nullable = false)
	private LocalDate targetDate;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task task;
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Todo() {
		
	}
	
	/*
	 * public Todo(long id, String title, String username, String description,
	 * LocalDate targetDate, boolean isDone) { super(); this.id = id; this.title =
	 * title; this.username = username; this.description = description;
	 * this.targetDate = targetDate; this.status = isDone; }
	 * 
	 * public Todo(String title, String username, String description, LocalDate
	 * targetDate, boolean isDone) { super(); this.title = title; this.username =
	 * username; this.description = description; this.targetDate = targetDate;
	 * this.status = isDone; }
	 */	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
