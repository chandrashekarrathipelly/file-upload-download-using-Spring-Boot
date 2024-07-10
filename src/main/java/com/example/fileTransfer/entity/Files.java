package com.example.fileTransfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@Entity
public class Files {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileID;

    private String name;
    
    private String filePath;

    private String fileType;

    
    @JoinColumn(name="userId" , referencedColumnName = "userID" )
    @ManyToOne
    @JsonBackReference
    private User user;

}
