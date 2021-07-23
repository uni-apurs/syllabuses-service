package com.apurs.microservices.syllabusesservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.syllabusesservice.service.SyllabusServiceImpl;
import com.apurs.microservices.syllabusesservice.dto.SyllabusCreateDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusUpdateDTO;

@RestController
@RequestMapping("/syllabuses")
public class SyllabusRestController {
	
	@Autowired
	private SyllabusServiceImpl syllabusService;
	
	@GetMapping("")
	public List<SyllabusDTO> getSyllabuses() {
		return syllabusService.findAll();
	}
	
	@GetMapping("/{id}")
	public SyllabusDTO getSyllabus(@PathVariable("id") Integer id) {
		return syllabusService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<SyllabusDTO> insertSyllabus(@RequestBody SyllabusCreateDTO syllabus) {
		if (syllabusService.insert(syllabus) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<SyllabusDTO> updateSyllabus(@RequestBody SyllabusUpdateDTO syllabus) {
		if (syllabusService.update(syllabus) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SyllabusDTO> deleteDepartment(@PathVariable ("id") Integer id) {
		if (syllabusService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
