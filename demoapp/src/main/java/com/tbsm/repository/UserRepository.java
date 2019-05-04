package com.tbsm.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Page<User> findBySocietyid(Long societyid, Pageable pageable);
	

}
