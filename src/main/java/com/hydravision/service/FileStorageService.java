package com.hydravision.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class FileStorageService {

    // This grabs the secret URL from your application.properties securely
    @Value("${cloudinary.url}")
    private String cloudinaryUrl;

    public String saveImageLocally(MultipartFile file) throws IOException {
        // 1. Connect to your secure Cloudinary Vault
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);

        // 2. Fire the image up to the cloud
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // 3. Cloudinary gives us back a permanent, unbreakable URL. 
        // We return this URL so it gets saved securely into your Aiven database!
        return uploadResult.get("secure_url").toString();
    }
}