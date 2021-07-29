package com.apurs.microservices.syllabusesservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apurs.microservices.departmentsservice.dto.DepartmentDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusCreateDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusDTO;
import com.apurs.microservices.syllabusesservice.dto.SyllabusUpdateDTO;
import com.apurs.microservices.syllabusesservice.model.Syllabus;
import com.apurs.microservices.syllabusesservice.repository.SyllabusRepository;

@Service
public class SyllabusServiceImpl implements SyllabusService {

	private SyllabusRepository syllabusRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Value("${app.departmentsEndpoint}")
	private String departmentsEndpoint;
	
	public SyllabusServiceImpl(SyllabusRepository syllabusRepository) {
		this.syllabusRepository = syllabusRepository;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Override
	public List<SyllabusDTO> findAll() {
		List<Syllabus> syllabuses = syllabusRepository.findAll();
		List<SyllabusDTO> dtos = new ArrayList<SyllabusDTO>();
		for (Syllabus syllabus : syllabuses)
			dtos.add(modelMapper.map(syllabus, SyllabusDTO.class));
		
		return dtos;
	}

	@Override
	public SyllabusDTO findOne(Integer id) {
		Syllabus syllabus = syllabusRepository.getById(id);
		return modelMapper.map(syllabus, SyllabusDTO.class);
	}

	@Override
	public SyllabusDTO insert(SyllabusCreateDTO syllabus) {
		Syllabus createSyllabus = modelMapper.map(syllabus, Syllabus.class);
		createSyllabus = syllabusRepository.save(createSyllabus);
		return modelMapper.map(createSyllabus, SyllabusDTO.class);
	}

	@Override
	public SyllabusDTO update(SyllabusUpdateDTO syllabus) {
		if (!syllabusRepository.existsById(syllabus.getId()))
			return null;
		
		Syllabus tempSyllabus = syllabusRepository.getById(syllabus.getId());
		Syllabus updatedSyllabus = modelMapper.map(syllabus, Syllabus.class);
		updatedSyllabus.setCreatedAt(tempSyllabus.getCreatedAt());
		updatedSyllabus = syllabusRepository.save(updatedSyllabus);
		return modelMapper.map(updatedSyllabus, SyllabusDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if (!syllabusRepository.existsById(id))
			return false;
		
		syllabusRepository.deleteById(id);
		return true;
	}

	public List<SyllabusDTO> findAllSyllabusesWhereDepartmentName(String departmentName) {
		ResponseEntity<List<DepartmentDTO>> res = restTemplate.exchange(departmentsEndpoint + "?name=" + departmentName, HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDTO>>() {});
		List<DepartmentDTO> matches = res.getBody();
		
		String inSql = "SELECT * FROM syllabus s JOIN depsyl ds ON s.id = ds.\"syllabusId\" WHERE ds.\"departmentId\" IN (";
		for (DepartmentDTO match : matches) {
			inSql += match.getId() + ", ";
		}
		inSql = inSql.substring(0, inSql.length() - 2) + ")";
		
		List<SyllabusDTO> syllabuses = new ArrayList<SyllabusDTO>();
		
		syllabuses = jdbcTemplate.query(inSql, (rs, rowNum) ->
				new SyllabusDTO(
					rs.getInt("id"),
					rs.getString("name")
				));
		
		return syllabuses;
	}

	public List<SyllabusDTO> findAllByName(String name) {
		List<Syllabus> syllabuses = syllabusRepository.findByNameContainingIgnoreCase(name);
		List<SyllabusDTO> dtos = new ArrayList<SyllabusDTO>();
		
		for (Syllabus syllabus : syllabuses)
			dtos.add(modelMapper.map(syllabus, SyllabusDTO.class));
		
		return dtos;
	}

}
