package com.tbsm.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SecurityUser;
import com.tbsm.model.User;
import com.tbsm.response.ResponseMessage;
import com.tbsm.service.EmailService;
import com.tbsm.service.SecurityUserService;
import com.tbsm.utils.CodeGeneratorUtils;
import com.tbsm.utils.RegistrationTemplate;
import com.tbsm.utils.SecureProcess;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/securityuser")
public class SecurityUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityUserController.class);
	
	@Autowired
	SecurityUserService securityUserService;
	
	@Autowired
	EmailService emailService;
	
    @Value("${tbsm.app.awsWebIP}")
    private String awsWebIP;
	
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
	public ResponseEntity<?> saveSecurityUser(@Valid @RequestBody SecurityUser securityUser) throws ResourceNotFoundException{
		logger.debug("inside UserController.saveUser() method");
		Long id = securityUser.getId();
		if(id != null && id > 0) {
			SecurityUser securityUser2 = securityUserService.getSecurityUserById(id);
			Boolean sameUserName = securityUser2.getUsername().equalsIgnoreCase(securityUser.getUsername());
			Boolean sameUserEmail = securityUser2.getEmail().equalsIgnoreCase(securityUser.getEmail());
			if(!sameUserName) {
				if(securityUserService.existsByUsername(securityUser.getUsername())) { 
					return new ResponseEntity<>(new ResponseMessage("Username is already taken. Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			if(!sameUserEmail) {
				if (securityUserService.existsByEmail(securityUser.getEmail())) { 
					return new ResponseEntity<>(new ResponseMessage("Email is already in use.  Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			securityUser2.setFirstname(securityUser.getFirstname());
			securityUser2.setLastname(securityUser.getLastname());
			securityUser2.setUsername(securityUser.getUsername());
			securityUser2.setEmail(securityUser.getEmail());
			securityUser2.setContactno(securityUser.getContactno());
			securityUser2.setStreetno(securityUser.getStreetno());
			securityUser2.setStreetname(securityUser.getStreetname());
			securityUser2.setCity(securityUser.getCity());
			securityUser2.setPostalcode(securityUser.getPostalcode());
			securityUser2.setProvince(securityUser.getProvince());
			securityUser2.setCountry(securityUser.getCountry());
			securityUser2.setStatus(securityUser.getStatus());
			securityUser2.setRole(securityUser.getRole());
			securityUser2.setDob(securityUser.getDob());
			securityUser2.setGender(securityUser.getGender());
			SecurityUser updatedUser = securityUserService.saveSecurityUser(securityUser2);
			return ResponseEntity.ok().body(updatedUser);
		}else {
			if(securityUserService.existsByUsername(securityUser.getUsername())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST); 
			}
			if (securityUserService.existsByEmail(securityUser.getEmail())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST); 
			}
			String password = new CodeGeneratorUtils().generateRandomPassword();
			securityUser.setPassword(SecureProcess.encrypt(password));
			SecurityUser userInfo = securityUserService.saveSecurityUser(securityUser);
			String template = new RegistrationTemplate().registerEmail(userInfo.getFirstname(), userInfo.getEmail(), password, awsWebIP.concat("/#/admin/login"));
			try {
				emailService.sendEmailHtml(template, securityUser.getEmail(), "TBSM| Welcome Society Admin");
			}catch(Exception e){
				logger.debug("Something is wrong. Please try again.");
			}
			return ResponseEntity.ok().body(userInfo);
		}
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

