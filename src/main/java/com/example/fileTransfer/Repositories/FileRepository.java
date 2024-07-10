package com.example.fileTransfer.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fileTransfer.entity.Files;

public interface FileRepository extends JpaRepository<Files,Long>{
    @Query("select f from Files f where f.name=:filename and f.user.userId=:userId")
    Optional<Files> findByFilenameAndUserid(String filename,Long userId);
}
