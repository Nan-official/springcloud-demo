package com.fastdfs.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fastdfs.service.FdfsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Nxy
 * @title: FdfsServiceImpl
 * @projectName lanka
 * @description: TODO
 */
public class FdfsServiceImpl implements FdfsService {
    @Override
    public String uploadOss(MultipartFile file, String userId, String fileExtName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = fileResourse.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = fileResourse.getAccessKeyId();
//        String accessKeySecret = fileResourse.getAccessKeySecret();
//        String myObjectName = fileResourse.getObjectName() + "/" + userId + "/" + userId + "." + fileExtName;
        OSS ossClient = null;

        try {
            // 创建OSSClient实例。
//            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传网络流。
            InputStream inputStream = file.getInputStream();
//            ossClient.putObject(fileResourse.getBucketName(), myObjectName, inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭OSSClient。
        ossClient.shutdown();

//        return myObjectName;
        return null;
    }
}
