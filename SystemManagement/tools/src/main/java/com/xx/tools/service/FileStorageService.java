package com.xx.tools.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageService {
    private final Path storagePath = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = storagePath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }

    public byte[] getFileBytes(String fileName) throws IOException {
        Path filePath = storagePath.resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    // querying
    public List<String> queryFiles(String keyword) throws IOException {
        return Files.list(storagePath)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.contains(keyword))
                .toList();
    }

    // split page
    public Page<String> listFiles(Pageable pageable) throws IOException {
        List<String> allFiles = Files.list(storagePath)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

        int startIndex = pageable.getPageNumber() * pageable.getPageSize();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), allFiles.size());

        return new PageImpl<>(allFiles.subList(startIndex, endIndex), pageable, allFiles.size());
    }
}
