package com.tbsm.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tbsm.model.SocietyUser;
import com.tbsm.model.SocietyUser;

@Repository
public interface SocietyUserRepository extends JpaRepository<SocietyUser, Long>{

	Page<SocietyUser> findBySocietyid(Long societyid, Pageable pageable);

	Optional<SocietyUser> findByUsername(String username);

	Optional<SocietyUser> findByEmail(String email);

	@Modifying
	@Transactional
	@Query(value="update society_users SET picture =?2 WHERE id =?1", nativeQuery=true)
	void updatePitureURL(Long id, String pictureUrl);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
}
