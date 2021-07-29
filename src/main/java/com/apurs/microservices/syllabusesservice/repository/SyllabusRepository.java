package com.apurs.microservices.syllabusesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apurs.microservices.syllabusesservice.model.Syllabus;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Integer> {
	List<Syllabus> findByNameContainingIgnoreCase(String name);
}
