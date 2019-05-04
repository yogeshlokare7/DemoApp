package com.tbsm.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.User;
import com.tbsm.request.LoginForm;
import com.tbsm.service.EmailService;
import com.tbsm.service.UserService;
import com.tbsm.utils.ApplicationUtility;
import com.tbsm.utils.SecureProcess;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {


	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws ResourceNotFoundException {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		User user = userService.login(username, password);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/update/{id}")
	public Boolean updatePassword(@PathVariable("id") Long id, @RequestParam String password) throws ResourceNotFoundException {
		User user = userService.getUserById(id);
		if(user!=null) {
			user.setToken(null);
			user.setPassword(SecureProcess.encrypt(password));
			userService.save(user);
			return true;
		}
		return false;
	}

	@GetMapping("/forgot")
	public ResponseEntity<User> processForgotPasswordForm(@RequestParam("userEmail") String userEmail, @RequestParam("forgotDate") String forgotDate) throws ResourceNotFoundException {
		User user = userService.getUserByEmail(userEmail);
		if (user!=null) {
			user.setToken(UUID.randomUUID().toString());
			userService.save(user);
			String appUrl = ApplicationUtility.FORGOT_URL+user.getToken()+";date="+forgotDate;
			String emailContaint =   "Hi "+user.getFirstname()+" ," + "\n" + "\n" +
					"    To reset your password, click the link below:"+
					"    \n" + 
					"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\"><a>"+ 
					appUrl+"</a>" + "\n" + "\n" 
					+ "\n" + "\n" +
					" NOTE : This is an automated message. Please do not reply."+ "\n" +  "\n"; 
			try {
				emailService.sendEmailHtml(emailContaint, user.getEmail(), "Password Reset Request");
			}catch(Exception e) {
				//logger.debug("Something is wrong. Please try again.");
			}
			return ResponseEntity.ok().body(user);	
		}else {
			return ResponseEntity.ok().body(user);
		}
	}
}