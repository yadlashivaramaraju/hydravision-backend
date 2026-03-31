package com.hydravision.service;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils; // THIS is the critical one!

@Service
public class FileStorageService {

    private final Cloudinary cloudinary;

    // This automatically pulls your keys from application.properties!
    public FileStorageService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {
        
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    // The new Cloudinary Upload Engine
    public String uploadImageToCloudinary(MultipartFile file) throws IOException {
        // Uploads the file directly from memory to the cloud
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        
        // Returns the permanent, secure https:// URL
        return uploadResult.get("secure_url").toString(); 
    }
}