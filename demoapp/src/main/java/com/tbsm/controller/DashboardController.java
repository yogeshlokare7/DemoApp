package com.tbsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.dto.CounterDto;
import com.tbsm.service.SocietyService;
import com.tbsm.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private UserService userService;

	@Autowired
	private SocietyService societyService;


	@GetMapping("/admincounters")
	public ResponseEntity<CounterDto> getUserById(){
		Long societyCount = societyService.getCount();
		Long societyAdminCount = userService.getSocietyAdminCount();
		Long societyUserCount = userService.getSocietyUSerCount();
		Long salesCount= societyService.getSalesCount();
		CounterDto counterDto = new CounterDto(societyCount, societyAdminCount, societyUserCount, salesCount);
		return ResponseEntity.ok().body(counterDto);
	}
}
