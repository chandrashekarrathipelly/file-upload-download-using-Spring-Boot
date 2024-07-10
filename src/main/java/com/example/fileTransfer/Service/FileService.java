package com.example.fileTransfer.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.fileTransfer.Repositories.FileRepository;
import com.example.fileTransfer.entity.Files;
import com.example.fileTransfer.entity.User;

@Service
public class FileService {
    
    @Autowired
    FileRepository fileRepository;

    public String uploadFile(MultipartFile file,User user) throws IOException{
       
        String path="C:\\Users\\Dell\\Downloads\\"+file.getOriginalFilename();
        File f=new File(path);
        // f.createNewFile();
        file.transferTo(f);
        String[] fileType=file.getOriginalFilename().split("[.]"); 
        System.out.println(fileType[fileType.length-1]);
        Files newFile=Files.builder().filePath(path).fileType(fileType[fileType.length-1]).user(user)
        .name(file.getOriginalFilename()).build();
        fileRepository.save(newFile);
        return "Sucessfully created";
    }

    public FileSystemResource downloadFile(String fileName,Long userId){
        Files file=fileRepository.findByFilenameAndUserid(fileName, userId).orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404),"File not Found"));
        File f =new File(file.getFilePath());
        if(f.exists()){
            FileSystemResource resource = new FileSystemResource(f);
            return resource;
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404),"file not found");
    }

    public String getContentType(String fileName,Long userId){
        Files file=fileRepository.findByFilenameAndUserid(fileName, userId).orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404),"File not Found"));
        return file.getFileType();
    }

    public String uploadFileService(MultipartFile file, User user) throws IOException{
        String path = "C:\\Users\\Dell\\Downloads\\"+file.getOriginalFilename();
        File fileObj = new File(path);
        file.transferTo(fileObj);
        String[] fileType = file.getOriginalFilename().split("[.]");
        System.out.println(fileType[fileType.length-1]);
        Files uploadFile = Files.builder().filePath(path).fileType(fileType[fileType.length-1]).user(user)
                .name(file.getOriginalFilename()).build();
        fileRepository.save(uploadFile);
        return "File Uploaded Successfully";

    }

}
