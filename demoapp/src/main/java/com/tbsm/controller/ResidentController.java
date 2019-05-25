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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Resident;
import com.tbsm.model.Resident;
import com.tbsm.response.ResponseMessage;
import com.tbsm.service.EmailService;
import com.tbsm.service.ResidentService;
import com.tbsm.utils.CodeGeneratorUtils;
import com.tbsm.utils.RegistrationTemplate;
import com.tbsm.utils.SecureProcess;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/resident")
public class ResidentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResidentController.class);
	
	@Autowired
	ResidentService residentService;
	
	@Autowired
	EmailService emailService;
	
    @Value("${tbsm.app.awsWebIP}")
    private String awsWebIP;
	
	@GetMapping("")
	public Page<Resident> showPage(Pageable pageable){
		logger.debug("inside ResidentController.showPage() method");
		Page<Resident> pageInfo = residentService.listByPage(pageable);
		return pageInfo;
	}
	
	@GetMapping("/list")
	public List<Resident> showList(){
		logger.debug("inside ResidentController.showList() method");
		List<Resident> list = residentService.getAllResidents();
		return list;
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveResident(@Valid @RequestBody Resident resident) throws ResourceNotFoundException{
		logger.debug("inside ResidentController.saveResident() method");
		Long id = resident.getId();
		if(id!=null && id >0) {
			Resident resident2 = residentService.getResidentById(id);
			Boolean sameUserName = resident2.getUsername().equalsIgnoreCase(resident.getUsername());
			Boolean sameUserEmail = resident2.getEmail().equalsIgnoreCase(resident.getEmail());
			if(!sameUserName) {
				if(residentService.existsByUsername(resident.getUsername())) { 
					return new ResponseEntity<>(new ResponseMessage("Username is already taken. Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			if(!sameUserEmail) {
				if (residentService.existsByEmail(resident.getEmail())) { 
					return new ResponseEntity<>(new ResponseMessage("Email is already in use.  Please try another!"), HttpStatus.BAD_REQUEST); 
				}
			}
			resident2.setFirstname(resident.getFirstname());
			resident2.setLastname(resident.getLastname());
			resident2.setUsername(resident.getUsername());
			resident2.setEmail(resident.getEmail());
			resident2.setContactno(resident.getContactno());
			resident2.setApartment(resident.getAlternatecontact());
			resident2.setAge(resident.getAge());
			resident2.setAlternatecontact(resident.getAlternatecontact());
			resident2.setColone(resident.getColone());
			resident2.setColtwo(resident.getColtwo());
			resident2.setFlatnumber(resident.getFlatnumber());
			resident2.setFloornumber(resident.getFloornumber());
			resident2.setLoginallowed(resident.getLoginallowed());
			resident2.setToken(resident.getToken());
			resident2.setStatus(resident.getStatus());
			resident2.setGender(resident.getGender());
			Resident updatedUser = residentService.saveResident(resident2);
			return ResponseEntity.ok().body(updatedUser);
		}else {
			if(residentService.existsByUsername(resident.getUsername())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST); 
			}
			if (residentService.existsByEmail(resident.getEmail())) { 
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST); 
			}
			String password = new CodeGeneratorUtils().generateRandomPassword();
			resident.setPassword(SecureProcess.encrypt(password));
			Resident resident2 = residentService.saveResident(resident);
			String template = new RegistrationTemplate().registerEmail(resident2.getFirstname(), resident2.getEmail(), password, awsWebIP.concat("/#/admin/login"));
			try {
				emailService.sendEmailHtml(template, resident.getEmail(), "TBSM| Welcome Resident");
			}catch(Exception e){
				logger.debug("Something is wrong. Please try again.");
			}
			return ResponseEntity.ok().body(resident2);	
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long residentId, @Valid @RequestBody Resident resident) throws ResourceNotFoundException{
		logger.debug("inside UserController.updateUser() method");
		Resident resident2 = residentService.getResidentById(residentId);
		Boolean sameUserName = resident2.getUsername().equalsIgnoreCase(resident.getUsername());
		Boolean sameUserEmail = resident2.getEmail().equalsIgnoreCase(resident.getEmail());
		if(!sameUserName) {
			if(residentService.existsByUsername(resident.getUsername())) { 
				return new ResponseEntity<>(new ResponseMessage("Username is already taken. Please try another!"), HttpStatus.BAD_REQUEST); 
			}
		}
		if(!sameUserEmail) {
			if (residentService.existsByEmail(resident.getEmail())) { 
				return new ResponseEntity<>(new ResponseMessage("Email is already in use.  Please try another!"), HttpStatus.BAD_REQUEST); 
			}
		}
		resident2.setFirstname(resident.getFirstname());
		resident2.setLastname(resident.getLastname());
		resident2.setUsername(resident.getUsername());
		resident2.setEmail(resident.getEmail());
		resident2.setContactno(resident.getContactno());
		resident2.setApartment(resident.getAlternatecontact());
		
		resident2.setStatus(resident.getStatus());
		resident2.setGender(resident.getGender());
		Resident updatedUser = residentService.saveResident(resident2);
		return ResponseEntity.ok().body(updatedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resident> getResidentById(@PathVariable(value = "id") Long residentId) throws ResourceNotFoundException{
		logger.debug("inside ResidentController.getResidentById() method");
		Resident resident = residentService.getResidentById(residentId);
		return ResponseEntity.ok().body(resident);
	}
	
	@GetMapping("/updatestatus/{id}")
	public ResponseEntity<Resident> updateResidentStatusById(@PathVariable(value = "id") Long residentId, @RequestParam("status") String status) throws ResourceNotFoundException{
		logger.debug("inside ResidentController.updateResidentStatusById() method");
		Resident resident = residentService.getResidentById(residentId);
		Resident resident2 = residentService.saveResident(resident);
		return ResponseEntity.ok().body(resident2);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteResident(@PathVariable(value = "id") Long residentId)
			throws ResourceNotFoundException {
		logger.debug("inside ResidentController.deleteResident() method:"+residentId );
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		try {
			response = residentService.removeResidentById(residentId);
		} catch (Exception e) {
			new ResourceNotFoundException("Not deleted");
		}
		return response;
	}

}
