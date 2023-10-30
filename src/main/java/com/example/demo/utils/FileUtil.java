package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtil {
    /**
     * 复制文件
     * @param srcPathStr 原始
     * @param desPathStr 目标
     */
    public void copyFile(String srcPathStr, String desPathStr){
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("/")+1);
        System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr + File.separator + newFileName;
        System.out.println("目标文件地址:"+desPathStr);
        try {
            //创建输入流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            //创建输出流对象
            FileOutputStream fos = new FileOutputStream(desPathStr);
            //创建搬运工具
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            //释放资源
            fis.close();
            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移动文件
     * @param srcPathStr 原始
     * @param desPathStr 目标
     */
    public boolean moveFile(String srcPathStr, String desPathStr){
        try{
            File file = new File(srcPathStr);
            if(!file.exists()){
                System.out.println("Source file not exits!");
                return false;
            }
            File newFile = new File(desPathStr);
            if(!newFile.isDirectory() && newFile.mkdir()){
                System.out.println("make dir");
            }
            boolean result = file.renameTo(new File(desPathStr + File.separator + file.getName()));
            System.out.println("File is moved:" + result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除
     * @param srcPathStr 文件路径
     */
    public Boolean delFile(String srcPathStr){
        try {
            File file = new File(srcPathStr);
            if(!file.exists()){
                System.out.println("Source file not exits!");
                return false;
            }
            boolean result = file.delete();
            System.out.println("delFile:" + result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
