package com.ram.doclogin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.doclogin.entity.Medicine;
import com.ram.doclogin.repository.MedicineRepository;

@RequestMapping("/api/v3")
@RestController
@CrossOrigin(origins = "http://localhost:4200") 
public class MedicineController {
	
	
	@Autowired
	MedicineRepository medicineRepository;

	public MedicineController(MedicineRepository medicineRepository) {
		super();
		this.medicineRepository = medicineRepository;
	}
	

	@PostMapping()
	public Medicine createMedicine(@RequestBody Medicine medicine) {
		
		return medicineRepository.save(medicine);
		
	}

	@GetMapping
	public List<Medicine> getAllMedicine(){
		
		return medicineRepository.findAll();
	}
	}
