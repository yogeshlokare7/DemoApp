package com.tbsm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.DailyStaff;

public interface DailyStaffService {
	
	public Page<DailyStaff> listByPage(Pageable pageable);
	
	public DailyStaff saveDailyStaff(DailyStaff dailyStaff);
	
	public DailyStaff updateDailyStaff(DailyStaff dailyStaff);

	public DailyStaff getDailyStaffById(Long dailyStaffId) throws ResourceNotFoundException;

	public Map<String, Boolean> removeDailyStaffById(Long dailyStaffId) throws ResourceNotFoundException;

	public List<DailyStaff> getList();
}
