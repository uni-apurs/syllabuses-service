package com.apurs.microservices.syllabusesservice.service;

import java.util.List;

import com.apurs.microservices.syllabusesservice.dto.SyllabusCreateDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusUpdateDTO;

public interface SyllabusService {
	public abstract List<SyllabusDTO> findAll();
	public abstract SyllabusDTO findOne(Integer id);
	public abstract SyllabusDTO insert(SyllabusCreateDTO syllabus);
	public abstract SyllabusDTO update(SyllabusUpdateDTO syllabus);
	public abstract boolean delete(Integer id);
	
	public abstract List<SyllabusDTO> findAllByName(String name);
	
	public abstract List<SyllabusDTO> findAllSyllabusesWhereDepartmentName(String departmentName);
}
