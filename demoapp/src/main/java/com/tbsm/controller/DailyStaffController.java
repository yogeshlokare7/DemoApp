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
import com.tbsm.model.DailyStaff;
import com.tbsm.service.DailyStaffService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dailystaff")
public class DailyStaffController {
	private static final Logger logger = LoggerFactory.getLogger(DailyStaffController.class);
	
	@Autowired
	DailyStaffService dailyStaffService;
	
	@GetMapping("")
	public Page<DailyStaff> showPage(Pageable pageable){
		logger.debug("inside DailyStaffController.showPage() method");
		Page<DailyStaff> pageInfo = dailyStaffService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list")
	public List<DailyStaff> showList(){
		logger.debug("inside DailyStaffController.showList() method");
		List<DailyStaff> list = dailyStaffService.getList();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<DailyStaff> saveDailyStaff(@Valid @RequestBody DailyStaff dailyStaff){
		logger.debug("inside DailyStaffController.saveDailyStaff() method");
		DailyStaff dailyStaff2 = dailyStaffService.saveDailyStaff(dailyStaff);
		return ResponseEntity.ok().body(dailyStaff2);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DailyStaff> getDailyStaffById(@PathVariable(value = "id") Long dailyStaffId) throws ResourceNotFoundException{
		logger.debug("inside DailyStaffController.getDailyStaffById() method");
		DailyStaff dailyStaff = dailyStaffService.getDailyStaffById(dailyStaffId);
		return ResponseEntity.ok().body(dailyStaff);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteDailyStaff(@PathVariable(value = "id") Long dailyStaffId)
			throws ResourceNotFoundException {
		logger.debug("inside DailyStaffController.deleteDailyStaff() method:"+dailyStaffId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = dailyStaffService.removeDailyStaffById(dailyStaffId);
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}
}
