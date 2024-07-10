package com.example.fileTransfer.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileTransfer.Service.FileService;
import com.example.fileTransfer.entity.User;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Hi how are you?\n I am fine");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return fileService.uploadFile(file, user);
    }

    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam("fileName") String fileName)
            throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String path="C:\\Users\\Dell\\Downloads\\"+fileName;
        FileSystemResource resource = fileService.downloadFile(fileName, user.getUserId());
        String contentType = this.fileService.getContentType(fileName, user.getUserId()); // Files.probeContentType(Paths.get(path));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : "application/octet-stream")
                .body(resource);
    }
}
