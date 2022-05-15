package com.littlecow.blog.service;

import com.littlecow.blog.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
     List<File> getFileList();

     @Transactional
     Boolean deleteFileById( Integer id);

     File getFileById(Integer id);

     @Transactional
     Boolean uploadFile(MultipartFile file,Boolean isAvatar);

    File getFileByName(String filename, String suffix);

    @Transactional
    Boolean updateUploadTime(Integer id);
}
