package com.apurs.microservices.syllabusesservice.dto;

public class SyllabusDTO {
	private int id;
	private String name;
	
	public SyllabusDTO() {
		super();
	}

	public SyllabusDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}