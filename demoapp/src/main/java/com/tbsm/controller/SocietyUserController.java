package com.tbsm.controller;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyUser;
import com.tbsm.response.ResponseMessage;
import com.tbsm.service.EmailService;
import com.tbsm.service.SocietyUserService;
import com.tbsm.utils.CodeGeneratorUtils;
import com.tbsm.utils.RegistrationTemplate;
import com.tbsm.utils.SecureProcess;

/**
 *
 * @author Yogesh Lokare
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/societyuser")
public class SocietyUserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	SocietyUserService societyUserService;
	
	@Autowired
	EmailService emailService;
	
    @Value("${tbsm.app.awsWebIP}")
    private String awsWebIP;


	@GetMapping("/list")
	public Page<SocietyUser> showPage(Pageable pageable){
		logger.debug("inside UserController.showPage() method");
		Page<SocietyUser> pageInfo = societyUserService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list/{societyId}")
	public Page<SocietyUser> showPage(@PathVariable("societyId") Long societyId, Pageable pageable) throws ResourceNotFoundException{
		logger.debug("inside UserController.showPage() method");
		Page<SocietyUser> pageInfo = societyUserService.listPageBySocietyId(societyId, pageable);
		return pageInfo;
	}

	@GetMapping("")
	public Iterable<SocietyUser> getAllUsers(){
		logger.debug("inside UserController.getAllUsers() method");
		return societyUserService.getAll();
	}

	@PostMapping("")
	public ResponseEntity<?> saveUser(@Valid @RequestBody SocietyUser user) throws ResourceNotFoundException{
		logger.debug("inside UserController.saveUser() method");
		Long id = user.getId();
		if(id != null && id > 0) {
			SocietyUser user2 = societyUserService.getSocietyUserById(id);
			Boolean sameUserName = user2.getUsername().equalsIgnoreCase(user.getUsername());
			Boolean sameUserEmail = user2.getEmail().equalsIgnoreCase(user.getEmail());
			if(!sameUserName) {
				if(societyUserService.existsBySocietyUsername(user.getUsername())) { 
					return new ResponseEntity<>(new ResponseMessage("Username is already taken. Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			if(!sameUserEmail) {
				if (societyUserService.existsByEmail(user.getEmail())) { 
					return new ResponseEntity<>(new ResponseMessage("Email is already in use.  Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			user2.setFirstname(user.getFirstname());
			user2.setLastname(user.getLastname());
			user2.setUsername(user.getUsername());
			user2.setEmail(user.getEmail());
			user2.setContactno(user.getContactno());
			user2.setStreetno(user.getStreetno());
			user2.setStreetname(user.getStreetname());
			user2.setCity(user.getCity());
			user2.setPostalcode(user.getPostalcode());
			user2.setProvince(user.getProvince());
			user2.setCountry(user.getCountry());
			user2.setStatus(user.getStatus());
			user2.setDob(user.getDob());
			user2.setGender(user.getGender());
			SocietyUser updatedUser = societyUserService.save(user2);
			return ResponseEntity.ok().body(updatedUser);
		}else {
			if(societyUserService.existsBySocietyUsername(user.getUsername())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST); 
			}
			if (societyUserService.existsByEmail(user.getEmail())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST); 
			}
			String password = new CodeGeneratorUtils().generateRandomPassword();
			user.setPassword(SecureProcess.encrypt(password));
			SocietyUser userInfo = societyUserService.save(user);
			String template = new RegistrationTemplate().registerEmail(userInfo.getFirstname(), userInfo.getEmail(), password, awsWebIP.concat("/#/admin/login"));
			try {
				emailService.sendEmailHtml(template, user.getEmail(), "TBSM| Welcome Society Admin");
			}catch(Exception e){
				logger.debug("Something is wrong. Please try again.");
			}
			return ResponseEntity.ok().body(userInfo);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody SocietyUser user) throws ResourceNotFoundException{
		logger.debug("inside UserController.updateUser() method");
		SocietyUser user2 = societyUserService.getSocietyUserById(userId);
		Boolean sameUserName = user2.getUsername().equalsIgnoreCase(user.getUsername());
		Boolean sameUserEmail = user2.getEmail().equalsIgnoreCase(user.getEmail());
		if(!sameUserName) {
			if(societyUserService.existsBySocietyUsername(user.getUsername())) { 
				return new ResponseEntity<>(new ResponseMessage("Username is already taken. Please try another!"), HttpStatus.BAD_REQUEST); 
			}
		}
		if(!sameUserEmail) {
			if (societyUserService.existsByEmail(user.getEmail())) { 
				return new ResponseEntity<>(new ResponseMessage("Email is already in use.  Please try another!"), HttpStatus.BAD_REQUEST); 
			}
		}
		user2.setFirstname(user.getFirstname());
		user2.setLastname(user.getLastname());
		user2.setUsername(user.getUsername());
		user2.setEmail(user.getEmail());
		user2.setContactno(user.getContactno());
		user2.setStreetno(user.getStreetno());
		user2.setStreetname(user.getStreetname());
		user2.setCity(user.getCity());
		user2.setPostalcode(user.getPostalcode());
		user2.setProvince(user.getProvince());
		user2.setCountry(user.getCountry());
		user2.setStatus(user.getStatus());
		user2.setDob(user.getDob());
		user2.setGender(user.getGender());
		SocietyUser updatedUser = societyUserService.save(user2);
		return ResponseEntity.ok().body(updatedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocietyUser> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
		logger.debug("inside UserController.getUserById() method");
		SocietyUser user = societyUserService.getSocietyUserById(userId);
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		logger.debug("inside UserController.deleteUser() method");
		Boolean user = societyUserService.removeSocietyUserById(userId);
		Map<String, Boolean> response = new HashMap<>();
		if(user) {
			response.put("deleted", Boolean.TRUE);
		}else {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}
}
