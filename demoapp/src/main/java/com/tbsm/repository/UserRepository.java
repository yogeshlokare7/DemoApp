package com.tbsm.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Page<User> findBySocietyid(Long societyid, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value="update users SET picture =?2 WHERE id =?1", nativeQuery=true)
	void updatePitureURL(Long id, String pictureUrl);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
	

}
