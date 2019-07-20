package com.tbsm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SecurityUser;

public interface SecurityUserService {
	
	public Page<SecurityUser> listByPage(Pageable pageable);

	public SecurityUser saveSecurityUser(SecurityUser securityUser);

	public SecurityUser updateSecurityUser(SecurityUser securityUser);

	public SecurityUser getSecurityUserById(Long securityUserId) throws ResourceNotFoundException;

	public Map<String, Boolean> removeSecurityUserById(Long securityUserId) throws ResourceNotFoundException;

	public List<SecurityUser> getAllSecurityUsers();

	public void updatePitureURL(Long id, String pictureUrl);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);

	public Long getSecurityCount(Long societyId);
}
