package com.tbsm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Resident;
import com.tbsm.service.ResidentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/resident")
public class ResidentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResidentController.class);
	
	@Autowired
	ResidentService residentService;
	
	@GetMapping("")
	public Page<Resident> showPage(Pageable pageable){
		logger.debug("inside ResidentController.showPage() method");
		Page<Resident> pageInfo = residentService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list")
	public List<Resident> showList(){
		logger.debug("inside ResidentController.showList() method");
		List<Resident> list = residentService.getAllResidents();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<Resident> saveResident(@Valid @RequestBody Resident resident){
		logger.debug("inside ResidentController.saveResident() method");
		Resident tourInfo = residentService.saveResident(resident);
		return ResponseEntity.ok().body(tourInfo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resident> getResidentById(@PathVariable(value = "id") Long residentId) throws ResourceNotFoundException{
		logger.debug("inside ResidentController.getResidentById() method");
		Resident resident = residentService.getResidentById(residentId);
		return ResponseEntity.ok().body(resident);
	}
	
	@GetMapping("/updatestatus/{id}")
	public ResponseEntity<Resident> updateResidentStatusById(@PathVariable(value = "id") Long residentId, @RequestParam("status") String status) throws ResourceNotFoundException{
		logger.debug("inside ResidentController.updateResidentStatusById() method");
		Resident resident = residentService.getResidentById(residentId);
		Resident resident2 = residentService.saveResident(resident);
		return ResponseEntity.ok().body(resident2);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteResident(@PathVariable(value = "id") Long residentId)
			throws ResourceNotFoundException {
		logger.debug("inside ResidentController.deleteResident() method:"+residentId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = residentService.removeResidentById(residentId);
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}

}
