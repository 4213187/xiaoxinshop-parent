package com.xiaoxinshop.manager.controller;

import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 小浩
 * @Date 2019/11/22 16:04
 * @Version 1.0
 **/
@RestController
@CrossOrigin(origins = "*", maxAge=3600)
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    //文件服务器地址
    private String FILE_SERVER_URL;


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResultVo upload(MultipartFile file){

        System.out.println("upload");
        //1、取文件的扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        try {
            //2、创建一个 FastDFS 的客户端
            FastDFSClient fastDFSClient   = new FastDFSClient("classpath:config/fdfs_client.conf");
            //3、执行上传处理
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            //4、拼接返回的 url 和 ip 地址，拼装成完整的 url
            String url = FILE_SERVER_URL + path;
            return new ResultVo(true,url);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "上传失败");
        }


    }
}
