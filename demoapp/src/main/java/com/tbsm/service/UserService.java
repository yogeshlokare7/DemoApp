package com.tbsm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyUser;
import com.tbsm.model.User;

public interface UserService {

	User login(String username, String password)  throws ResourceNotFoundException;

	Page<User> listByPage(Pageable pageable);

	Iterable<User> getAll();

	User save(User user);

	User getUserById(Long userId) throws ResourceNotFoundException;

	Boolean removeUserById(Long userId) throws ResourceNotFoundException;

	void updatePitureURL(Long id, String pictureUrl);

	Long getSocietyAdminCount();

	public User getUserByUsername(String username) throws ResourceNotFoundException;

	public User getUserByEmail(String userEmail) throws ResourceNotFoundException;

	public boolean existsByEmail(String email);

	public boolean existsByUsername(String username);

	User getUserByToken(String resetToken) throws ResourceNotFoundException ;

}
