package com.tbsm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyUser;

public interface SocietyUserService {
	
	SocietyUser login(String username, String password)  throws ResourceNotFoundException;

	Page<SocietyUser> listByPage(Pageable pageable);

	Iterable<SocietyUser> getAll();

	SocietyUser save(SocietyUser user);

	SocietyUser getSocietyUserById(Long userId) throws ResourceNotFoundException;

	Boolean removeSocietyUserById(Long userId) throws ResourceNotFoundException;

	void updatePitureURL(Long id, String pictureUrl);

	Long getSocietyUSerCount();

	public SocietyUser getSocietyUserBySocietyUsername(String username) throws ResourceNotFoundException;

	public SocietyUser getSocietyUserByEmail(String userEmail) throws ResourceNotFoundException;

	public boolean existsByEmail(String email);

	public boolean existsBySocietyUsername(String username);

	Page<SocietyUser> listPageBySocietyId(Long societyId, Pageable pageable);

}
