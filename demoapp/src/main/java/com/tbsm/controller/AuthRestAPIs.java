package com.tbsm.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.tbsm.model.SocietyUser;
import com.tbsm.model.User;
import com.tbsm.request.LoginForm;
import com.tbsm.service.EmailService;
import com.tbsm.service.SocietyUserService;
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
	SocietyUserService societyService;

	@Autowired
	EmailService emailService;

	@Value("${tbsm.tejovatapp.awsTBSMIP}")
	private String awsAdminIP;
	
	@Value("${tbsm.societyapp.awsTBSMIP}")
	private String awsSocietyIP;
	
	public static final String FORGOT_URL = "/admin/setpassword;resetToken=";
	public static final String FORGOT_URL2 = "/sessions/setpassword;resetToken=";

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws ResourceNotFoundException {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		User user = userService.login(username, password);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/society/login")
	public ResponseEntity<?> authenticateSocietyUser(@Valid @RequestBody LoginForm loginRequest) throws ResourceNotFoundException {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		SocietyUser societyUser = societyService.login(username, password);
		return ResponseEntity.ok(societyUser);
	}

	@PostMapping("/update/{id}")
	public Boolean updatePassword(@PathVariable("id") Long id, @RequestBody User loginRequest) throws ResourceNotFoundException {
		User user = userService.getUserById(id);
		String password = loginRequest.getPassword();
		if(user!=null) {
			user.setToken(null);
			user.setPassword(SecureProcess.encrypt(password));
			userService.save(user);
			return true;
		}
		return false;
	}

	@GetMapping("/society/update/{id}")
	public Boolean updateSocietyUserPassword(@PathVariable("id") Long id, @RequestBody User loginRequest) throws ResourceNotFoundException {
		SocietyUser user = societyService.getSocietyUserById(id);
		String password = loginRequest.getPassword();
		if(user!=null) {
			user.setToken(null);
			user.setPassword(SecureProcess.encrypt(password));
			societyService.save(user);
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
			String appUrl = awsAdminIP+FORGOT_URL+user.getToken()+";date="+forgotDate;
			String emailContaint =   "Hi "+user.getFirstname()+"," + "<br/><br/>\n" + "\n" +
					"    To reset your password, click the link below:"+
					"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\"><a>"+ 
					appUrl+"</a>" + "\n" + "\n" 
					+ "\n" + "\n" +
					"\n<br/>" + 
					" <p>NOTE : This is an automated message. Please do not reply.</p>"+ "\n" +  "\n"+
					" \n<p>Best Regards,<br>\n" + 
					" The Tejovat Team</p>" + 
					" \n" ;
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

	@GetMapping("/society/forgot")
	public ResponseEntity<SocietyUser> societyForgotPasswordForm(@RequestParam("userEmail") String userEmail, @RequestParam("forgotDate") String forgotDate) throws ResourceNotFoundException {
		SocietyUser user = societyService.getSocietyUserByEmail(userEmail);
		if (user!=null) {
			user.setToken(UUID.randomUUID().toString());
			societyService.save(user);
			String appUrl = awsSocietyIP+FORGOT_URL+user.getToken()+";date="+forgotDate;
			String emailContaint =   "Hi "+user.getFirstname()+"," + "<br/><br/>\n" + "\n" +
					"    To reset your password, click the link below:"+
					"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\"><a>"+ 
					appUrl+"</a>" + "\n" + "\n" 
					+ "\n" + "\n" +
					"\n<br/>" + 
					" <p>NOTE : This is an automated message. Please do not reply.</p>"+ "\n" +  "\n"+
					" \n<p>Best Regards,<br>\n" + 
					" The Tejovat Team</p>" + 
					" \n" ;
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

	@GetMapping(value = "/checkUserByToken")
	public ResponseEntity<User> getUserByUserResetToken(@RequestParam("resetToken") String resetToken) throws ResourceNotFoundException {
		User user = userService.getUserByToken(resetToken);
		return ResponseEntity.ok().body(user);
	}
}