package com.tbsm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.model.SocietyMaster;
import com.tbsm.repository.SocietyRepository;
import com.tbsm.service.SocietyService;

@Service
public class SocietyServiceImpl implements SocietyService{
	
	private static final Logger logger = LoggerFactory.getLogger(SocietyServiceImpl.class);
	
	@Autowired
	SocietyRepository repo;

	@Override
	public Page<SocietyMaster> listByPage(Pageable pageable) {
		logger.debug("inside SocietyServiceImpl.listByPage() Method : fetching the Society list by page");
		return repo.findAll(pageable);
	}

	@Override
	public SocietyMaster saveSociety(SocietyMaster societyMaster) {
		logger.debug("inside SocietyServiceImpl.saveSociety() Method ");
		return repo.save(societyMaster);
	}

	@Override
	public SocietyMaster updateSociety(SocietyMaster societyMaster) {
		logger.debug("inside SocietyServiceImpl.updateSociety() Method ");
		return repo.save(societyMaster);
	}

	@Override
	public SocietyMaster getSocietyById(Long societyId) throws ResourceNotFoundException {
		logger.debug("inside SocietyServiceImpl.getSocietyById() Method: fetch the tour by id:"+societyId);
		return repo.findById(societyId).orElseThrow(() -> new ResourceNotFoundException("Society not found for this id :: " + societyId));
	}

	@Override
	public Map<String, Boolean> removeSocietyById(Long societyId) throws ResourceNotFoundException {
		logger.debug("inside SocietyServiceImpl.removeSocietyById() Method: removing the tour by id:"+societyId);
		SocietyMaster optional = getSocietyById(societyId);
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
	public List<SocietyMaster> getAllSocietys() {
		logger.debug("inside SocietyServiceImpl.getAllSocietys() Method ");
		return repo.findAllSociety();
	}

	@Override
	public void updatePitureURL(Long id, String pictureUrl) {
		// TODO Auto-generated method stub
		repo.updatePitureURL(id, pictureUrl);
	}

	@Override
	public Long getCount() {
		return repo.count();
	}

	@Override
	public Long getSalesCount() {
		return (long) 0;
	}

}
