package com.xx.tools.controller;

import com.xx.tools.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class LocalStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    // upload file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        return "File uploaded successfully: " + fileName;
    }

    // query file
    @GetMapping("/query")
    public List<String> queryFiles(@RequestParam String keyword) throws IOException {
        return fileStorageService.queryFiles(keyword);
    }

    // split pages
    @GetMapping("/pagedFiles")
    public Page<String> listPagedFiles(Pageable pageable) throws IOException {
        return fileStorageService.listFiles(pageable);
    }

    // download file
    @GetMapping("/download/{fileName}.{format}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, @PathVariable String format) throws IOException {
        byte[] fileBytes = fileStorageService.getFileBytes(fileName);
        ByteArrayResource resource = new ByteArrayResource(fileBytes);

        String contentType;
        String fileExtension = format.toLowerCase();

        if ("json".equals(fileExtension)) {
            contentType = "application/json";
        } else if ("csv".equals(fileExtension)) {
            contentType = "text/csv";
        } else {
            return ResponseEntity.badRequest().body("Unsupported format: " + format);
        }

        return ResponseEntity.ok()
                .contentLength(fileBytes.length)
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
