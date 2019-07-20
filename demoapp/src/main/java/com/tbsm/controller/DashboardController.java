package com.tbsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.dto.CounterDto;
import com.tbsm.dto.SocietyCounterDto;
import com.tbsm.service.DailyStaffService;
import com.tbsm.service.ResidentService;
import com.tbsm.service.SecurityUserService;
import com.tbsm.service.SocietyService;
import com.tbsm.service.SocietyUserService;
import com.tbsm.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private UserService userService;

	@Autowired
	private SocietyService societyService;
	
	@Autowired
	private SocietyUserService societyUserService;
	
	@Autowired
	private SecurityUserService securityService;
	
	@Autowired
	private DailyStaffService staffService;
	
	@Autowired
	private ResidentService resiService;

	@GetMapping("/admincounters")
	public ResponseEntity<CounterDto> getUserById(){
		Long societyCount = societyService.getCount();
		Long societyAdminCount = societyUserService.getSocietyUSerCount();
		Long societyUserCount = userService.getCount();
		Long salesCount= societyService.getSalesCount();
		CounterDto counterDto = new CounterDto(societyCount, societyAdminCount, societyUserCount, salesCount);
		return ResponseEntity.ok().body(counterDto);
	}
	

	@GetMapping("/societycounters/{id}")
	public ResponseEntity<SocietyCounterDto> getUserById(@PathVariable(value = "id") Long societyId){
		Long userCount = societyUserService.getSocietyUserCount(societyId);
		Long staffCount = staffService.getStaffCount(societyId);
		Long securityCount = securityService.getSecurityCount(societyId);
		Long residentCount= resiService.getResidentCount(societyId);
		SocietyCounterDto counterDto = new SocietyCounterDto(userCount, staffCount, securityCount, residentCount);
		return ResponseEntity.ok().body(counterDto);
	}
}
