package com.tonlist.extraUtilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


@Component
public class FileManager {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Autowired
	private AmazonS3Client s3Client;

	private static final String SUFFIX = "/";
	private static final String UNDERSCORE = "_";

	/**
	 * It store the image to amazon S3 database. 
	 * @param file The image to store.
	 * @param username Add the username to image name.
	 * @param eventName Add the Event name to image name.
	 * @return It return link for image or erro string.
	 */
	public String storeFile(MultipartFile file, String username, String eventName) {

		String[] splitType = file.getOriginalFilename().split("\\.");
		String type = "." + splitType[splitType.length - 1];
		String uuid = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(new Date());
		String folderName = "eventImages";
		String fileName = folderName + SUFFIX + username + SUFFIX + username + UNDERSCORE + eventName + UNDERSCORE + uuid
				+ type;

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		try {
			objectMetadata.setContentLength(file.getBytes().length);
		} catch (IOException e1) {
			return "erro";
		}

		try {

			s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));

		} catch (Exception e) {
			return "erro";
		}

		return "https://s3-eu-west-1.amazonaws.com/" + bucket + "/" + fileName;
	}



}
