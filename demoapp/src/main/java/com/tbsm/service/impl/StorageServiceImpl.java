package com.tbsm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tbsm.exception.MyFileNotFoundException;
import com.tbsm.exception.ResourceNotFoundException;
import com.tbsm.service.SocietyService;
import com.tbsm.service.StorageService;
import com.tbsm.service.UserService;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	UserService userService;
	
	@Autowired
	SocietyService societyService;


//	@Autowired
//	StaffService staffService;
//
//	@Autowired
//	CandidateService candidateService;

//	@Autowired
//	EventMasterService eventService;
//
//	@Autowired
//	PresenterService presenterService;
//
//	@Autowired
//	TourService tourService;
//
//	@Autowired
//	TourEventMappingService tourEventServiceMapping;

	final private String USER = "user";
//	final private String PRESENTER = "presenter";
//	final private String EVENT = "event";
	final private String SOCIETY = "society";
//	final private String CANDIDATE = "candidate";

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	public static final String EXTERNAL_FILE_PATH = "C:\\tbsm\\";
	public static final String EXTERNAL_FILE_PATH2 = "C:\\tbsm\\homestayproperty";

	/* new test file */
	public static final String EXTERNAL_FILE_PATH_TEST = "C:\\tbsm\\upload";

	/**
	 * Current Working Service
	 */
//	@Override
//	public void store(Long id, MultipartFile file, String type, String filename, HttpServletRequest request) {
//		logger.debug("inside StorageServiceImpl.store() Method : save the Image");
//		File dir = new File(EXTERNAL_FILE_PATH + File.separator + type);
//		try {
//			if (!dir.exists())
//				dir.mkdirs();
//			File src = convert(file);
//			File dest = new File(dir.getAbsolutePath() + File.separator + filename);
//			copyFileUsingStream(src, dest);
//			String path = request.getRequestURL().toString();
//			String pictureUrl = path + "?type=" + type;
//			StoreImagePath(id, type, pictureUrl);
//		} catch (Exception e) {
//			throw new RuntimeException("FAIL!");
//		}
//	}

	/**
	 * Test Store service for uploading
	 */
	@Override
	public void store(Long id, MultipartFile file, String type, String filename, HttpServletRequest request) {
		logger.debug("inside StorageServiceImpl.store() Method : save the Image");
		File dir = new File(EXTERNAL_FILE_PATH_TEST + File.separator + type);
		try {
			if (!dir.exists())
				dir.mkdirs();
			File src = convert(file);
			File dest = new File(dir.getAbsolutePath() + File.separator + filename);
			copyFileUsingStream(src, dest);
			String path = request.getRequestURL().toString();
			String pictureUrl = path + "?type=" + type;
			StoreImagePath(id, type, pictureUrl);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new RuntimeException("FAIL!");
		}
	}

	private void StoreImagePath(Long id, String type, String pictureUrl) throws ResourceNotFoundException {
		logger.debug("inside StorageServiceImpl.StoreImagePath() Method : saving imagepath to db");
		switch (type) {
		case USER:
			userService.updatePitureURL(id, pictureUrl);
			break;
		case SOCIETY:
			societyService.updatePitureURL(id, pictureUrl);
			break;
//		case CANDIDATE:
//			candidateService.updatePitureURL(id, pictureUrl);
//			break;
//		case PRESENTER:
//			presenterService.updatePitureURL(id, pictureUrl);
//			break;
//		case EVENT:
//			eventService.updatePictureURL(id, pictureUrl);
//			break;
		default:
			logger.debug("inside StorageServiceImpl.StoreImagePath() Method : Switch=default");
		}
	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		long start = System.nanoTime();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
			source.delete();
		}
		long t = System.nanoTime() - start;
		System.out.println("Time taken by Stream Copy = " + t);
	}

	@Override
	public Resource loadFile(String type, String filename) {
		File dir = new File(EXTERNAL_FILE_PATH_TEST + File.separator + type);
		try {
			String defaultFile = type + "_default.png";
			if (!dir.exists())
				dir.mkdirs();

			Path file = Paths.get(dir.getAbsolutePath()).resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				Path file2 = Paths.get(dir.getAbsolutePath()).resolve(defaultFile);
				Resource resource2 = new UrlResource(file2.toUri());
				return resource2;
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!, Something went wrong. Please try again.");
		}
	}

	@Override
	public Boolean CheckAvailability(Integer id, String type, String filename) {
		String uploadPath = EXTERNAL_FILE_PATH + "\\" + type;
		String uploadPath2 = uploadPath + "\\" + filename;
		File file = new File(uploadPath2);
		return file.exists();
	}

	@Override
	public String saveHomestayImage(Integer id, Integer propertyid, MultipartFile[] files) {
		String status = "";
		File dir = new File(EXTERNAL_FILE_PATH + File.separator + "/property");
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			try {
				if (!dir.exists())
					dir.mkdirs();
				File uploadFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				File src = convert(file);
				copyFileUsingStream(src, uploadFile);
				status = status + "You successfully uploaded file=" + file.getOriginalFilename();
			} catch (Exception e) {
				status = status + "Failed to upload " + file.getOriginalFilename() + " " + e.getMessage();
			}
		}
		return status;
	}

	@Override
	public Resource loadFileAsResource(String fileName, String type) {
		File file = new File(EXTERNAL_FILE_PATH);
		Path path = Paths.get(file + File.separator + type).resolve(fileName);
		try {
			Resource resource = new UrlResource(path.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	@Override
	public void storeImage(Integer id, MultipartFile file, String type, String filename) {
		long start = System.nanoTime();
		if (file.isEmpty()) {
			throw new RuntimeException("Empty File!");
		}
		try {
			// Get the file and save it somewhere
			String uploadPath = EXTERNAL_FILE_PATH + "\\" + type;
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadPath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			throw new RuntimeException("FAIL!");
		}
		System.out.println("Time taken by Store1234 Copy = " + (System.nanoTime() - start));
	}

	@Override
	public Resource loadFile2(Integer id, Integer propertyid, String filename) {
		File dir = new File(EXTERNAL_FILE_PATH2 + File.separator + id + "_" + propertyid);
		if (!dir.exists()) {
			// throw new HomestayException("FAIL! Not Found Path");
			throw new RuntimeException("FAIL! Not Found Path");
		} else {
			try {
				// Path file = rootLocation.resolve(filename);
				Path file = Paths.get(dir.getAbsolutePath()).resolve(filename);
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists() || resource.isReadable()) {
					return resource;
				} else {
					// throw new HomestayException("FAIL, Not Found!");
					throw new RuntimeException("FAIL, Not Found!");
				}
			} catch (MalformedURLException e) {
				// throw new HomestayException("FAIL!");
				throw new RuntimeException("FAIL!");
			}
		}
	}

	@Override
	public Resource getTourEventsByTourid(Long tourId, HttpServletRequest request) throws ResourceNotFoundException {
		String fileName = "Tour_Calendar_" + tourId + ".pdf";
		String dest = EXTERNAL_FILE_PATH + "\\" + fileName;
//		Tour tour = tourService.getTourById(tourId);
//		if (tour != null) {
//			List<TourEventMapping> tourEventMapList = tourEventServiceMapping
//					.getTourEventMappingByTourIdOrderByStartDate(tourId);
//			try {
//				new CalendarPdf().createPdf(dest, tour, tourEventMapList);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			Resource resource = loadFileAsResource(fileName);
			// Try to determine file's content type
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {
			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return resource;
		} else {
			return null;
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			String dest = EXTERNAL_FILE_PATH + "\\" + fileName;
			Path filePath = Paths.get(dest);
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	/**
	 * test For download file from server location
	 */
	@Override
	public Resource loadFileTest(String type, String filename) {
		File dir = new File(EXTERNAL_FILE_PATH_TEST + File.separator + type);
		try {
			String defaultFile = type + "_default.png";
			if (!dir.exists())
				dir.mkdirs();

			Path file = Paths.get(dir.getAbsolutePath()).resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				Path file2 = Paths.get(dir.getAbsolutePath()).resolve(defaultFile);
				Resource resource2 = new UrlResource(file2.toUri());
				return resource2;
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!, Something went wrong. Please try again.");
		}
	}

	@Override
	public void storeNew(Long id, MultipartFile file, String type, HttpServletRequest request) {
		logger.debug("inside StorageServiceImpl.store() Method : save the Image");
		File dir = new File(EXTERNAL_FILE_PATH_TEST + File.separator + type);
		try {
			if (!dir.exists())
				dir.mkdirs();
			File src = convert(file);
			File dest = new File(dir.getAbsolutePath() + File.separator + src.getName());
			copyFileUsingStream(src, dest);
			//String path = request.getRequestURL().toString();
			String pictureUrl = src.getName();
			StoreImagePath(id, type, pictureUrl);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new RuntimeException("FAIL!");
		}
	}

	@Override
	public Resource getFile(String type, String filename) {
		File dir = new File(EXTERNAL_FILE_PATH_TEST + File.separator + type);
		try {
			if (!dir.exists())
				dir.mkdirs();
			Path file = Paths.get(dir.getAbsolutePath()).resolve(filename);
			return new UrlResource(file.toUri());
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!, Something went wrong. Please try again.");
		}
	}

}
