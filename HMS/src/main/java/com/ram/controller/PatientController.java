package com.ram.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.entity.Patient;
import com.ram.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")  // allow fronted request port
public class PatientController {
	
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@PostMapping
	public Patient createpatient(@RequestBody Patient patient) {
		
		return patientRepository.save(patient);
				
	}
	
	@GetMapping
	public List<Patient>getAllPationt(){
		
		return patientRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient>getPatient(@PathVariable long id){
		
		Patient patient2 = patientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Patient not found with id" +id));
		
		return ResponseEntity.ok(patient2);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>>deletepatient(@PathVariable long id){
		
		Patient patient = patientRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found with id" +id));
		
		patientRepository.delete(patient);
		
		Map<String, Boolean>response=new HashMap<String, Boolean>();
		
		response.put("Deleted",Boolean.TRUE);	
		
		
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient>updateData(@RequestBody Patient patient, @PathVariable long id){
		
		Patient patient2 = patientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException ("Patient not found with id"+id));
		
		patient2.setName(patient.getName());
		patient2.setAge(patient.getAge());
		patient2.setBlood(patient.getBlood());
		patient2.setDose(patient.getDose());
		patient2.setFees(patient.getFees());
		patient2.setPrescription(patient.getPrescription());
		patient2.setUrgency(patient.getUrgency());
		
		Patient saved = patientRepository.save(patient2);
		
		
		return  ResponseEntity.ok(saved);
		
		
	}

}
