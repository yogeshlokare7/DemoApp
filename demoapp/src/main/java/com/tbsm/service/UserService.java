package com.tbsm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.User;

public interface UserService {

	User login(String username, String password)  throws ResourceNotFoundException;

	Page<User> listByPage(Pageable pageable);

	Iterable<User> getAll();

	User save(User user);

	Page<User> listPageBySocietyId(Long societyId, Pageable pageable);

	User getUserById(Long userId) throws ResourceNotFoundException;

	Boolean removeUserById(Long userId) throws ResourceNotFoundException;

	User getUserByEmail(String userEmail) throws ResourceNotFoundException;

}
