package com.springbatch.controller;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Value("${upload.dir}")
	private String uploadDir;

	private String uploadedFileName;
	
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			uploadedFileName = file.getOriginalFilename();
			if(uploadedFileName == null || file.isEmpty()) {
				return "Please Upload the excel file";
			}
			String filePath = uploadDir + "/" + file.getOriginalFilename();
			File uploadDirectory = new File(filePath);
			if (!uploadDirectory.exists()) 
	        {
	           uploadDirectory.mkdirs();
	        }
			file.transferTo(uploadDirectory);
			return "File uploaded successfully! Path: " + filePath;
		} catch (Exception e) {
			return "Failed to upload file: " + e.getMessage();
		}
	}

    public String getUploadedFileName() {
        return uploadedFileName;
    }
}
