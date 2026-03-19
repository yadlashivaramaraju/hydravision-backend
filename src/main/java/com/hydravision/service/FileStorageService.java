package com.hydravision.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String UPLOAD_DIR = "uploads/";

    public String saveImageLocally(MultipartFile file) throws IOException {
        // 1. Create the "uploads" folder if it doesn't exist
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 2. Generate a random name so files don't overwrite each other
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 3. Save the file to your laptop
        Path targetLocation = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // 4. Return the file name to save in MySQL
        return uniqueFileName;
    }
}