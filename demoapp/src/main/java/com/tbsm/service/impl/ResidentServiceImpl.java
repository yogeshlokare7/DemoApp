package com.tbsm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.Resident;
import com.tbsm.repository.ResidentRepository;
import com.tbsm.service.ResidentService;

@Service
public class ResidentServiceImpl implements ResidentService{
	
	@Autowired
	ResidentRepository repo;

	@Override
	public Page<Resident> listByPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Resident saveResident(Resident resident) {
		return repo.save(resident);
	}

	@Override
	public Resident updateResident(Resident resident) {
		return repo.save(resident);
	}

	@Override
	public Resident getResidentById(Long residentId) throws ResourceNotFoundException {
		return repo.findById(residentId).orElseThrow(() -> new ResourceNotFoundException("Society not found for this id :: " + residentId));
	}

	@Override
	public Map<String, Boolean> removeResidentById(Long residentId) throws ResourceNotFoundException {
		Resident optional = getResidentById(residentId);
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
	public List<Resident> getAllResidents() {
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public void updatePitureURL(Long id, String pictureUrl) {
		repo.updatePitureURL(id, pictureUrl);
	}

}
