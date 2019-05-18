package com.tbsm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyMaster;


public interface SocietyService {
	public Page<SocietyMaster> listByPage(Pageable pageable);
	
	public SocietyMaster saveSociety(SocietyMaster societyMaster);
	
	public SocietyMaster updateSociety(SocietyMaster societyMaster);

	public SocietyMaster getSocietyById(Long societyId) throws ResourceNotFoundException;

	public Map<String, Boolean> removeSocietyById(Long societyId) throws ResourceNotFoundException;

	public List<SocietyMaster> getAllSocietys();

	public void updatePitureURL(Long id, String pictureUrl);

	public Long getCount();

	public Long getSalesCount();
}
