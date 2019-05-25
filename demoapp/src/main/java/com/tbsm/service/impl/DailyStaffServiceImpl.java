package com.tbsm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.DailyStaff;
import com.tbsm.model.Resident;
import com.tbsm.repository.DailyStaffRepository;
import com.tbsm.service.DailyStaffService;

@Service
public class DailyStaffServiceImpl implements DailyStaffService{
	
	@Autowired
	DailyStaffRepository repo;

	@Override
	public Page<DailyStaff> listByPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public DailyStaff saveDailyStaff(DailyStaff dailyStaff) {
		return repo.save(dailyStaff);
	}

	@Override
	public DailyStaff updateDailyStaff(DailyStaff dailyStaff) {
		return repo.save(dailyStaff);
	}

	@Override
	public DailyStaff getDailyStaffById(Long dailyStaffId) throws ResourceNotFoundException {
		return repo.findById(dailyStaffId).orElseThrow(() -> new ResourceNotFoundException("Daily Staff not found for this id :: " + dailyStaffId));
	}

	@Override
	public Map<String, Boolean> removeDailyStaffById(Long dailyStaffId) throws ResourceNotFoundException {
		DailyStaff optional = getDailyStaffById(dailyStaffId);
		Map<String, Boolean> response = new HashMap<>();
		if(optional!=null) {
			repo.delete(optional);
			response.put("deleted", Boolean.TRUE);
		}else {
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}

	@Override
	public List<DailyStaff> getList() {
		return repo.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

}
