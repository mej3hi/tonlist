package com.tonlist.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public static String storeFile(MultipartFile file){
    	String fileName = file.getOriginalFilename();
    	String dir = System.getProperty("user.dir");
    	dir+= File.separator+"src"+File.separator+"main"+File.separator;
    	dir+= "resources"+File.separator+"static"+File.separator+"images"+File.separator;
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(dir + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                
                return (dir+fileName).replaceAll(".*?(static.*)", "$1").replace("\\", "/");
            } catch (Exception e) {
                return("You failed to upload " + fileName + ": " + e.getMessage());
            }
        } else {
            return("Unable to upload. File is empty.");
        }
    	
    }
	
}