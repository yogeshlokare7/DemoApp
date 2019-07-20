package com.tbsm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tbsm.model.DailyStaff;

@Repository
public interface DailyStaffRepository extends JpaRepository<DailyStaff, Long>{


	@Modifying
	@Transactional
	@Query(value="update daily_staff SET picture =?2 WHERE id =?1", nativeQuery=true)
	void updatePitureURL(Long id, String pictureUrl);

	@Query(value="SELECT count(*) FROM daily_staff WHERE societyid =?1", nativeQuery=true)
	Long countBySocietyId(Long societyId);
	
}
