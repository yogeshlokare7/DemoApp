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
import com.tbsm.model.SocietyMaster;
import com.tbsm.service.SocietyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/society")
public class SocietyController {
	
	private static final Logger logger = LoggerFactory.getLogger(SocietyController.class);
	
	@Autowired
	SocietyService societyService;
	
	@GetMapping("")
	public Page<SocietyMaster> showPage(Pageable pageable){
		logger.debug("inside SocietyController.showPage() method");
		Page<SocietyMaster> pageInfo = societyService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list")
	public List<SocietyMaster> showList(){
		logger.debug("inside SocietyController.showList() method");
		List<SocietyMaster> list = societyService.getAllSocietys();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<SocietyMaster> saveSociety(@Valid @RequestBody SocietyMaster society){
		logger.debug("inside SocietyController.saveSociety() method");
		SocietyMaster tourInfo = societyService.saveSociety(society);
		return ResponseEntity.ok().body(tourInfo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocietyMaster> getSocietyById(@PathVariable(value = "id") Long societyId) throws ResourceNotFoundException{
		logger.debug("inside SocietyController.getSocietyById() method");
		SocietyMaster society = societyService.getSocietyById(societyId);
		return ResponseEntity.ok().body(society);
	}
	
	@GetMapping("/updatestatus/{id}")
	public ResponseEntity<SocietyMaster> updateSocietyStatusById(@PathVariable(value = "id") Long societyId, @RequestParam("status") String status) throws ResourceNotFoundException{
		logger.debug("inside SocietyController.updateSocietyStatusById() method");
		SocietyMaster society = societyService.getSocietyById(societyId);
		//society.setStatus(status);
		SocietyMaster society2 = societyService.saveSociety(society);
		return ResponseEntity.ok().body(society2);
	}
	
	/* DELETE SocietyMaster With SocietyEventMapp,
	 * counselor and presenter By SocietyMaster Id 
	 * */
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteSociety(@PathVariable(value = "id") Long societyId)
			throws ResourceNotFoundException {
		logger.debug("inside SocietyController.deleteSociety() method:"+societyId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = societyService.removeSocietyById(societyId);	
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}

}
