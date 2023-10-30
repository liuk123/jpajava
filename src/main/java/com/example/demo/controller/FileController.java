package com.example.demo.controller;


import com.example.demo.base.ResultSet;
import com.example.demo.utils.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    FileUtil fileUtil;

    @PostMapping(path="/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultSet addImage(@RequestParam(value = "uploadFile") MultipartFile file, @RequestParam() String pathName ) throws IOException {
        if(file.isEmpty()){
            return new ResultSet(ResultSet.RESULT_CODE_ERROR, "文件不可为空");
        }
        String fileName = file.getOriginalFilename();
        if(fileName!=null){
            String fileSuffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = UUID.randomUUID() + fileSuffix;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+File.separator+"MM"+File.separator+"dd" +File.separator);
            String format = sdf.format(new Date());
            String realPath = "assets" + File.separator + pathName + File.separator;
            File realFile = new File( realPath + format);
            File newFile = new File(realFile.getAbsolutePath() +  File.separator + fileName);

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(!realFile.exists()){
                realFile.mkdirs();
            }
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String url =  "assets" + File.separator + pathName + File.separator + format + fileName;
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, url.replace(File.separator, "/") + "?tr=" + width+","+height);
        }
        return new ResultSet();
    }

    @GetMapping(path = "/download")
    public void downLoad(HttpServletResponse response, @RequestParam String filePath) throws RuntimeException {
//        String realPath = "";
        File file = new File(filePath);
        if(file.exists()){
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/")+1), StandardCharsets.UTF_8));

            try {
                OutputStream os = response.getOutputStream();
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer))!=-1){
                    os.write(buffer,0, len);
                }
                os.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 删除文件(需要优化)
     * @param urls 文件路径
     */
    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam String[] urls){
        List<String> r = new ArrayList<>();
        for(String url:urls){
            if(fileUtil.delFile(url)){
                r.add(url);
            }
        }
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功", r);
    }
}
