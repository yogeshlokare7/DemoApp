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
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SecurityUser;
import com.tbsm.service.SecurityUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/securityUser")
public class SecurityUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityUserController.class);
	
	@Autowired
	SecurityUserService securityUserService;
	
	@GetMapping("")
	public Page<SecurityUser> showPage(Pageable pageable){
		logger.debug("inside SecurityUserController.showPage() method");
		Page<SecurityUser> pageInfo = securityUserService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list")
	public List<SecurityUser> showList(){
		logger.debug("inside SecurityUserController.showList() method");
		List<SecurityUser> list = securityUserService.getAllSecurityUsers();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<SecurityUser> saveResident(@Valid @RequestBody SecurityUser securityUser){
		logger.debug("inside SecurityUserController.saveResident() method");
		SecurityUser securityUser2 = securityUserService.saveSecurityUser(securityUser);
		return ResponseEntity.ok().body(securityUser2);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SecurityUser> getResidentById(@PathVariable(value = "id") Long securityUserId) throws ResourceNotFoundException{
		logger.debug("inside SecurityUserController.getResidentById() method");
		SecurityUser securityUser = securityUserService.getSecurityUserById(securityUserId);
		return ResponseEntity.ok().body(securityUser);
	}
	
	/* DELETE SecurityUser With ResidentEventMapp,
	 * counselor and presenter By SecurityUser Id 
	 * */
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteResident(@PathVariable(value = "id") Long securityUserId)
			throws ResourceNotFoundException {
		logger.debug("inside SecurityUserController.deleteResident() method:"+securityUserId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = securityUserService.removeSecurityUserById(securityUserId);
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}

}

