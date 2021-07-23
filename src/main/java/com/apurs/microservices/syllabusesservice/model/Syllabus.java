package com.apurs.microservices.syllabusesservice.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="\"syllabus\"")
@SequenceGenerator(name = "syllabus_id_seq", initialValue = 1, allocationSize = 1)
public class Syllabus {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "syllabus_id_seq")
	@Column(name="\"id\"")
	private int id;
	
	@Column(name="\"name\"")
	private String name;
	
	@Column(name="\"createdAt\"")
	private ZonedDateTime createdAt;
	
	@Column(name="\"updatedAt\"")
	private ZonedDateTime updatedAt;

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