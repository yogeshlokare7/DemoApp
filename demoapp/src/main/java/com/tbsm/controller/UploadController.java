package com.tbsm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.service.StorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/auth")
public class UploadController {
	
	@Autowired
	StorageService storageService;
 
	List<String> files = new ArrayList<String>();
	
	public static final String USER = "user";
	public static final String SOCIETY = "society";
 
	@PostMapping("/uploadtest/{id}")
	public ResponseEntity<Map<String,Boolean>> handleFileUpload(@PathVariable Long id, 
			@RequestParam("file") MultipartFile file,
			@RequestParam(defaultValue="user") String type, HttpServletRequest request) {
		String filename = type+"_"+id+".png";
		try {
			storageService.store(id, file, type, filename, request);
			return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("status", true));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.singletonMap("status", false));
		}
	}
	
	/**TESTING-
	 * 
	 * @param id
	 * @param propertyid
	 * @param files
	 * @return
	 * @throws IOException
	 */
	
	@PostMapping("/upload/{id}")
	public ResponseEntity<Map<String,Boolean>> handleFileUpload2(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam(defaultValue="user") String type, HttpServletRequest request) {
		try {
			storageService.storeNew(id, file, type, request);
			return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("status", true));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.singletonMap("status", false));
		}
	}
	
	@PostMapping(value = "/uploadFiles/{id}/{propertyid}")
	public String uploadingPost(@PathVariable Integer id, @PathVariable Integer propertyid, @RequestParam("files") MultipartFile[] files) throws IOException {
	      String status = storageService.saveHomestayImage(id, propertyid, files);
	      return status;
	}
	
	@GetMapping("/checkfile/{id}")
	public Map<String, Boolean> handleFileUpload(@PathVariable Integer id, @RequestParam(defaultValue="user") String type) {
		String filename = type+"_"+id+".png";
		try {
			Boolean result = storageService.CheckAvailability(id, type, filename);
			return Collections.singletonMap("status", result);
		} catch (Exception e) {
			return Collections.singletonMap("status", false);
		}
	}
	
	@PostMapping("/uploadImage/{id}")
	public ResponseEntity<String> handleFileUploadImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file, @RequestParam(defaultValue="user") String type) {
		String message = "";
		String filename = type+"_"+id+".png";
		try {
			storageService.storeImage(id, file, type, filename);
			files.add(filename);
			message = "You successfully uploaded " + filename + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
 
	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(fileNames);
	}
 
	@GetMapping("/upload/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable Integer id, @RequestParam(defaultValue="user") String type) {
		String filename = type+"_"+id+".png";
		Resource file = storageService.loadFile(type, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/userpicture")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@RequestParam(defaultValue="user_default.png") String filename) {
		Resource file = storageService.getFile(USER, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/societypicture")
	@ResponseBody
	public ResponseEntity<Resource> getCompanyFile(@RequestParam(defaultValue="society_default.png") String filename) {
		Resource file = storageService.getFile(SOCIETY, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	/**
	 * Test Method : DOWNLOAD FILE
	 * */
	
	@GetMapping("/downloadtest/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile2(@PathVariable Integer id, @RequestParam(defaultValue="user") String type) {
		String filename = type+"_"+id;
		Resource file = storageService.loadFileTest(type, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping(value = "/files/{id}/{propertyid}/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable Integer id, @PathVariable Integer propertyid, @PathVariable String filename) {
		Resource file = storageService.loadFile2(id, propertyid, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/downloadtourpdf/{tourId}")
	public  ResponseEntity<Resource> downloadFile(@PathVariable Long tourId, HttpServletRequest request) throws ResourceNotFoundException {
		Resource file = storageService.getTourEventsByTourid(tourId, request);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
