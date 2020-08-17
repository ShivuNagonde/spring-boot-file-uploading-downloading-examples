package com.uploadingimage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uploadingimage.entity.DataBaseFile;
import com.uploadingimage.service.DataBaseFileServiceImpl;

@RestController
public class FileDownloadController {

    @Autowired
    private DataBaseFileServiceImpl fileStorageService;

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        DataBaseFile databaseFile = fileStorageService.getFile(fileName);
    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
            .body(new ByteArrayResource(databaseFile.getData()));
    }
}
