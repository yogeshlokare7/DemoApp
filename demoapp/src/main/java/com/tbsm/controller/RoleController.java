package com.tbsm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Role;
import com.tbsm.service.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/role")
public class RoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/list")
	public Iterable<Role> showList(){
		logger.debug("inside SecurityUserController.showList() method");
		Iterable<Role> list = roleService.getAllRoles();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<Role> saveResident(@Valid @RequestBody Role role){
		logger.debug("inside SecurityUserController.saveResident() method");
		Role role2 = roleService.saveRole(role);
		return ResponseEntity.ok().body(role2);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getResidentById(@PathVariable(value = "id") Long roleId) throws ResourceNotFoundException{
		logger.debug("inside SecurityUserController.getResidentById() method");
		Role role = roleService.getRoleById(roleId);
		return ResponseEntity.ok().body(role);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteResident(@PathVariable(value = "id") Long roleId)
			throws ResourceNotFoundException {
		logger.debug("inside SecurityUserController.deleteResident() method: "+roleId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = roleService.removeRoleById(roleId);
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}

}


