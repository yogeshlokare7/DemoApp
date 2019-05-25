package com.tbsm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.SecurityUser;

@Repository
public interface SecurityUserRepository extends PagingAndSortingRepository<SecurityUser, Long>{

	@Modifying
	@Transactional
	@Query(value="update security_user SET picture =?2 WHERE id =?1", nativeQuery=true)
	void updatePitureURL(Long id, String pictureUrl);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
