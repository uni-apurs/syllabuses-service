package com.apurs.microservices.syllabusesservice.dto;

import java.time.ZonedDateTime;

public class SyllabusUpdateDTO {
	private int id;
	private String name;
	private ZonedDateTime updatedAt;
	
	public SyllabusUpdateDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
