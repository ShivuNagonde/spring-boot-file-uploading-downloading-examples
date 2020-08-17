package com.uploadingimage.service;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.uploadingimage.entity.DataBaseFile;
import com.uploadingimage.exception.FileNotFoundException;
import com.uploadingimage.exception.FileStorageException;
import com.uploadingimage.repository.DataBaseFileRepository;


@Service
public class DataBaseFileServiceImpl {

    @Autowired
    private DataBaseFileRepository dbFileRepository;

    public DataBaseFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
        DataBaseFile dbFile = new DataBaseFile(fileName, file.getContentType(), file.getBytes());
                return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DataBaseFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
        		.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}