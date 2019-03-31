package com.springboot.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * springboot文件上传
 */
//@Controller
@RestController  // 该注解表示该类下的方法的返回值会自动做json格式的转换
public class FileUploadController {

    /**
     * 处理文件上传
     * filename:必须与html表格中<input name='xxx'>的name属性相同
     */
    @RequestMapping("/fileUploadController")
    public String fileUpload(MultipartFile filename) throws Exception{
        System.out.println(filename.getOriginalFilename());
        filename.transferTo(new File("D:\\SoftWare\\JavaWorkSpace\\SpringBootDemo\\src\\main\\resources\\static\\test.png"));
        return "上传成功";
    }
}
