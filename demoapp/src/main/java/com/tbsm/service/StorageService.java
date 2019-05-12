package com.tbsm.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.tbsm.exception.ResourceNotFoundException;

public interface StorageService {
	
	public void store(Long id, MultipartFile file, String type, String filename, HttpServletRequest request);
	public Resource loadFile(String type, String filename);
	public Boolean CheckAvailability(Integer id, String type, String filename);
	public String saveHomestayImage(Integer id, Integer propertyid, MultipartFile[] files);
	public Resource loadFileAsResource(String fileName, String type);
	public void storeImage(Integer id, MultipartFile file, String type, String filename);
	public Resource loadFile2(Integer id, Integer propertyid, String filename);
	public Resource getTourEventsByTourid(Long tourId, HttpServletRequest request) throws ResourceNotFoundException;
	
	/** test
	 * 
	 * @param type
	 * @param filename
	 * @return
	 */
	public Resource loadFileTest(String type, String filename);
	
}