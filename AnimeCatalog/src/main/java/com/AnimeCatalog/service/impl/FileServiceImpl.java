package com.AnimeCatalog.service.impl;

import com.AnimeCatalog.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        log.info("FileService is uploading the file...");

        String fileName = file.getOriginalFilename();

        String filePath = path + File.separator + fileName;

        File mainFile = new File(path);

        if (!mainFile.exists()) {
            mainFile.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }
}
