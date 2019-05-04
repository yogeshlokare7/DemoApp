package com.tbsm.controller;


import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.User;
import com.tbsm.service.UserService;
import com.tbsm.utils.CodeGeneratorUtils;
import com.tbsm.utils.SecureProcess;
/**
 *
 * @author Yogesh Lokare
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;


	@GetMapping("/list")
	public Page<User> showPage(Pageable pageable){
		logger.debug("inside UserController.showPage() method");
		Page<User> pageInfo = userService.listByPage(pageable);
		return pageInfo;
	}

	@GetMapping("/list/{societyId}")
	public Page<User> showPage(@PathVariable("societyId") Long societyId, Pageable pageable){
		logger.debug("inside UserController.showPage() method");
		Page<User> pageInfo = userService.listPageBySocietyId(societyId, pageable);
		return pageInfo;
	}

	@GetMapping("")
	public Iterable<User> getAllUsers(){
		logger.debug("inside UserController.getAllUsers() method");
		return userService.getAll();
	}

	@PostMapping("")
	public ResponseEntity<User> saveCandidate(@Valid @RequestBody User user){
		logger.debug("inside CandidateController.saveCandidate() method");
		String password = new CodeGeneratorUtils().generateRandomPassword();
		user.setPassword(SecureProcess.encrypt(password));
		User userInfo = userService.save(user);
		return ResponseEntity.ok().body(userInfo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
		logger.debug("inside UserController.getUserById() method");
		User user = userService.getUserById(userId);
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		logger.debug("inside UserController.deleteUser() method");
		Boolean user = userService.removeUserById(userId);
		Map<String, Boolean> response = new HashMap<>();
		if(user) {
			response.put("deleted", Boolean.TRUE);
		}else {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}


}
