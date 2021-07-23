package com.apurs.microservices.syllabusesservice.dto;

import java.time.ZonedDateTime;

public class SyllabusCreateDTO {
	private String name;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;

	public SyllabusCreateDTO(String name) {
		super();
		this.name = name;
		this.setCreatedAt(ZonedDateTime.now());
		this.setUpdatedAt(ZonedDateTime.now());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
