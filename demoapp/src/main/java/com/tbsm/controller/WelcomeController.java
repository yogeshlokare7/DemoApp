package com.tbsm.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.response.ResponseMessage;
/**
*
* @author Yogesh Lokare
*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WelcomeController {
	
	@GetMapping("/")
	public ResponseEntity<?> test() {
		String msg = "Demo App Backend Services: Success \n Date: " + new Date().toString();
		return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);
	}
}


