package com.ram.doclogin.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jakarta.persistence.EntityNotFoundException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.doclogin.entity.Appointment;
import com.ram.doclogin.repository.AppointmentRepository;


@RestController
@RequestMapping("api/v2")
@CrossOrigin(origins = "http://localhost:4200") 
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	

	public AppointmentController(AppointmentRepository appointmenytRepository) {
         super();
         
         this.appointmentRepository = appointmentRepository;
}

	
	@PostMapping
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		
		return appointmentRepository.save(appointment);
		
	}
	
	
	@GetMapping
	public List<Appointment>getAllAppointments(){
		return appointmentRepository.findAll();
	}
	
	@DeleteMapping("/{id}")

	public ResponseEntity<Map<String,Boolean>>deleteAppointment(@PathVariable long id){
		
		 Appointment appointment = appointmentRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id " + id));

		
		  appointmentRepository.delete(appointment);
		  Map<String ,Boolean>response=new HashMap<String,Boolean>();
		  response.put("Deleted",Boolean.TRUE);
			        
		   return ResponseEntity.ok(response);
		
		
	}
	
	
}
