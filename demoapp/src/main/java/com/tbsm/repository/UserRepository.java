package com.tbsm.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.Role;
import com.tbsm.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByToken(String token);
	
	List<User> findByRole(Role role);

	@Modifying
	@Transactional
	@Query(value="update users SET picture =?2 WHERE id =?1", nativeQuery=true)
	void updatePitureURL(Long id, String pictureUrl);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
	

}
