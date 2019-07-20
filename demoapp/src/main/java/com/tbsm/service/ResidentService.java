package com.tbsm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Resident;

public interface ResidentService {
	
	public Page<Resident> listByPage(Pageable pageable);
	
	public Resident saveResident(Resident resident);
	
	public Resident updateResident(Resident resident);

	public Resident getResidentById(Long residentId) throws ResourceNotFoundException;

	public Map<String, Boolean> removeResidentById(Long residentId) throws ResourceNotFoundException;

	public List<Resident> getAllResidents();

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);

	public void updatePitureURL(Long id, String pictureUrl);

	public Long getResidentCount(Long societyId);

	public Long getallResidentCount();
}
