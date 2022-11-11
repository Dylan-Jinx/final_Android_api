package com.finalproj.devapp.api.common.api;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.finalproj.devapp.api.common.ApiResponse;
import com.finalproj.devapp.api.log.annotation.WebLog;
import com.finalproj.devapp.api.utils.MinioUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/minio")
public class MinioController {

    Logger logger = LoggerFactory.getLogger(MinioController.class);

    /**
     *
     * @param objectName 对象的名称 例如：user_icon/admin_login.png  这里user_icon为上一级的文件夹
     * @return ApiResponse
     * @throws Exception
     */
    @WebLog(description = "Minio对象存储获取资源文件url")
    @GetMapping
    public ApiResponse getFileUrlByName(@RequestParam("objectName")String objectName) throws Exception {
        String resUrl = MinioUtils.getResUrl("android", objectName);
        Map<String,String> minioReturnParamMap = new HashMap<>();
        minioReturnParamMap.put("resUrl",resUrl);
        return ApiResponse.ok(minioReturnParamMap);
    }

    /**
     *
     * @param objectName 对象的名称 例如：user_icon/admin_login.png  这里user_icon为上一级的文件夹
     * @return ApiResponse
     * @throws Exception
     */
    @WebLog(description = "Minio对象存储获取资源文件url")
    @GetMapping("/getVideoByName")
    public ApiResponse getVideoByName(@RequestParam("objectName")String objectName) throws Exception {
        String resUrl = MinioUtils.getResUrl("androidvideo", objectName);
        Map<String,String> minioReturnParamMap = new HashMap<>();
        minioReturnParamMap.put("resUrl",resUrl);
        return ApiResponse.ok(minioReturnParamMap);
    }

    @WebLog(description = "Minio对象存储上传资源文件")
    @PostMapping
    public ApiResponse uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("bucketName") String bucketName,
                                  @RequestParam("objectname") String objectname) throws Exception {
        String fileName = file.getOriginalFilename();
        String contentType = FileUtil.getMimeType(fileName);
        logger.info(contentType);
        boolean result = MinioUtils.uploadFile(file,bucketName,objectname,contentType);
        if (result) {
            Map<String,String> minioReturnParamMap = new HashMap<>();
            minioReturnParamMap.put("resUrl",objectname);
            return ApiResponse.ok("文件上传成功",minioReturnParamMap);
        }else{
            return ApiResponse.error("文件上传失败，请稍后进行上传操作");
        }
    }

    private String generatorObjectName(String fileName) {
        String objectName = IdUtil.objectId();
        String extName = FileUtil.extName(fileName);
        return objectName+"."+extName;
    }
}
