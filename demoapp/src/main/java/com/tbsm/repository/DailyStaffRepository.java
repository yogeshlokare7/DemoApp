package com.tbsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tbsm.model.DailyStaff;

@Repository
public interface DailyStaffRepository extends JpaRepository<DailyStaff, Long>{
	
}
