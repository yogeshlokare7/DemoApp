package com.tbsm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SecurityUser;
import com.tbsm.repository.SecurityUserRepository;
import com.tbsm.service.SecurityUserService;

@Service
public class SecurityUserServiceImpl implements SecurityUserService{
	
	@Autowired
	SecurityUserRepository repo;

	@Override
	public Page<SecurityUser> listByPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public SecurityUser saveSecurityUser(SecurityUser securityUser) {
		return repo.save(securityUser);
	}

	@Override
	public SecurityUser updateSecurityUser(SecurityUser securityUser) {
		return repo.save(securityUser);
	}

	@Override
	public SecurityUser getSecurityUserById(Long securityUserId) throws ResourceNotFoundException {
		return repo.findById(securityUserId).orElseThrow(() -> new ResourceNotFoundException("SecurityUser not found for this id :: " + securityUserId));
	}

	@Override
	public Map<String, Boolean> removeSecurityUserById(Long securityUserId) throws ResourceNotFoundException {
		SecurityUser optional = getSecurityUserById(securityUserId);
		Map<String, Boolean> response = new HashMap<>();
		if(optional!=null) {
			repo.delete(optional);
			response.put("deleted", Boolean.TRUE);
		}else {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}

	@Override
	public List<SecurityUser> getAllSecurityUsers() {
		return (List<SecurityUser>) repo.findAll();
	}

	@Override
	public void updatePitureURL(Long id, String pictureUrl) {
		repo.updatePitureURL(id, pictureUrl);
	}

	@Override
	public boolean existsByUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public Long getSecurityCount(Long societyId) {
		return repo.countBySocietyId(societyId);
	}

}
