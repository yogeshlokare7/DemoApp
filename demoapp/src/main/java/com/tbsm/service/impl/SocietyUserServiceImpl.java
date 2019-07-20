package com.tbsm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyMaster;
import com.tbsm.model.SocietyUser;
import com.tbsm.repository.SocietyUserRepository;
import com.tbsm.service.SocietyService;
import com.tbsm.service.SocietyUserService;
import com.tbsm.utils.SecureProcess;

@Service
public class SocietyUserServiceImpl implements SocietyUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(SocietyUserServiceImpl.class);
	
	@Autowired
	private SocietyUserRepository repo;
	
	@Autowired
	private SocietyService societyService;

	@Override
	public SocietyUser login(String username, String password) throws ResourceNotFoundException {
		SocietyUser user = repo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found for this username :: " + username));
		String pwd = SecureProcess.encrypt(password);
		if(user.getPassword().equalsIgnoreCase(pwd)) {
			return user;
		}else {
			throw new ResourceNotFoundException("The password you entered is incoorect. Please try again.");
		}
	}

	@Override
	public Page<SocietyUser> listByPage(Pageable pageable) {
		logger.debug("inside  SocietyUserServiceImpl.listByPage() method");
		return repo.findAll(pageable);
	}

	@Override
	public Iterable<SocietyUser> getAll() {
		logger.debug("inside  SocietyUserServiceImpl.getAll() method");
		return repo.findAll();
	}

	@Override
	public SocietyUser save(SocietyUser user) {
		logger.debug("inside  SocietyUserService.listPageBySocietyId() method");
		return repo.save(user);
	}


	@Override
	public SocietyUser getSocietyUserById(Long userId) throws ResourceNotFoundException {
		logger.debug("inside SocietyUserServiceImpl.getSocietyUserById() Method: fetch the tour by id:"+userId);
		return repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Society not found for this id :: " + userId));
	}

	@Override
	public Boolean removeSocietyUserById(Long userId) throws ResourceNotFoundException {
		logger.debug("inside  SocietyUserService.removeSocietyUserById() method");
		SocietyUser user = getSocietyUserById(userId);
		if(user!=null) {
			repo.delete(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public SocietyUser getSocietyUserByEmail(String userEmail) throws ResourceNotFoundException {
		logger.debug("inside SocietyUserServiceImpl.getSocietyUserByEmail() Method: fetch the user by email:"+userEmail);
		return repo.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("USer not found for this id :: " + userEmail));
	}

	@Override
	public void updatePitureURL(Long id, String pictureUrl) {
		repo.updatePitureURL(id, pictureUrl);
	}

	@Override
	public Long getSocietyUSerCount() {
		return repo.count();
	}

	@Override
	public SocietyUser getSocietyUserBySocietyUsername(String username) throws ResourceNotFoundException {
		logger.debug("inside  SocietyUserServiceImpl.getSocietyUserById() method");
		return repo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("SocietyUser not found for this username :: " + username));
	}

	@Override
	public boolean existsByEmail(String email) {
		logger.debug("inside  SocietyUserServiceImpl.existsByEmail() method");
		return repo.existsByEmail(email);
	}

	@Override
	public boolean existsBySocietyUsername(String username) {
		logger.debug("inside  SocietyUserServiceImpl.existsBySocietyUsername() method");
		return repo.existsByUsername(username);
	}

	@Override
	public Page<SocietyUser> listPageBySocietyId(Long societyId, Pageable pageable) throws ResourceNotFoundException {
		SocietyMaster society = societyService.getSocietyById(societyId);
		return repo.findBySocietyid(society, pageable);
	}

	@Override
	public Long getSocietyUserCount(Long societyId) {
		return repo.countBySocietyId(societyId);
	}

}
