package com.fastdfs.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nxy
 * @title: FdfsService
 * @projectName lanka
 * @description: TODO
 */
@Service
public interface FdfsService {
    public String uploadOss(MultipartFile file, String userId, String fileExtName);
}
