package com.makara.phoneshop.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.makara.phoneshop.service.GenerateFileService;

@Service
public class GenerateFileServiceImple implements GenerateFileService{

    @Override
    public String generateFile(String originalFile) {
       LocalDateTime now = LocalDateTime.now();
       String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyMMddHHmmss"));

       int lastIndex = originalFile.lastIndexOf(".");
       if (lastIndex == -1) {
        return timeStamp;
       }
       String  fileException = originalFile.substring(lastIndex);
        return timeStamp + fileException;
        
    }
    
}
