package com.tbsm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.User;
import com.tbsm.repository.UserRepository;
import com.tbsm.service.UserService;
import com.tbsm.utils.SecureProcess;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User login(String username, String password) throws ResourceNotFoundException {
		User user = userRepository.findByUsername(username);
		String pwd = SecureProcess.encrypt(password);
		if(user.getPassword().equalsIgnoreCase(pwd)) {
			return user;
		}else {
			throw new ResourceNotFoundException("Wrong Password. Please try again");
		}
	}

	@Override
	public Page<User> listByPage(Pageable pageable) {
		logger.debug("inside  UserServiceImpl.listByPage() method");
		return userRepository.findAll(pageable);
	}

	@Override
	public Iterable<User> getAll() {
		logger.debug("inside  UserServiceImpl.getAll() method");
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		logger.debug("inside  UserService.listPageBySocietyId() method");
		return userRepository.save(user);
	}

	@Override
	public Page<User> listPageBySocietyId(Long societyId, Pageable pageable) {
		logger.debug("inside  UserService.listPageBySocietyId() method");
		return userRepository.findBySocietyid(societyId, pageable);
	}

	@Override
	public User getUserById(Long userId) throws ResourceNotFoundException {
		logger.debug("inside UserServiceImpl.getUserById() Method: fetch the tour by id:"+userId);
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Society not found for this id :: " + userId));
	}

	@Override
	public Boolean removeUserById(Long userId) throws ResourceNotFoundException {
		logger.debug("inside  UserService.removeUserById() method");
		User user = getUserById(userId);
		if(user!=null) {
			userRepository.delete(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User getUserByEmail(String userEmail) throws ResourceNotFoundException {
		logger.debug("inside UserServiceImpl.getUserByEmail() Method: fetch the user by email:"+userEmail);
		return userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("USer not found for this id :: " + userEmail));
	}

}