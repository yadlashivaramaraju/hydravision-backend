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

    public String saveImageLocally(MultipartFile file) throws IOException {
        // 1. THE LINUX FIX: Force Java to calculate the exact Absolute Path
        File directory = new File("uploads");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String absolutePath = directory.getAbsolutePath() + "/";

        // 2. Generate a random name so files don't overwrite each other
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 3. Save the file to those exact absolute coordinates
        Path targetLocation = Paths.get(absolutePath + uniqueFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // 4. Return the file name to save in MySQL
        return uniqueFileName;
    }
}