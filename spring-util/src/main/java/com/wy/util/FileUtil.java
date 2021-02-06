package com.wy.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Dwen
 * @date 2020-11-10 15:34
 */
public class FileUtil {

    /**
     * 创建文件
     * @param filePath 文件目录 例：/usr/image
     * @param fileName 文件名 例：20201110.img
     * @return
     */
    public static File createFile(String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        File fileParent = file.getParentFile();//返回的是File类型,可以调用exsit()等方法
        if (!fileParent.exists()) {
            fileParent.mkdirs();// 能创建多级目录
        }
        if (!file.exists()) {
            try {
                file.createNewFile();//有路径才能创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * MultipartFile 转 File
     * @param file 上传的文件
     * @param toFile 要存储的文件
     * @return
     * @throws Exception
     */
    public static void multipartFileToFile(MultipartFile file, File toFile) {
        if (file.equals("") || file.getSize() <= 0 || toFile == null) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStreamToFile(ins, toFile);
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * MultipartFile 转 File
     * @param file 上传的文件
     * @param filePath 要存储的文件路径
     * @param fileName 要存储的文件名
     * @throws Exception
     */
    public static void multipartFileToFile(MultipartFile file, String filePath, String fileName) {
        multipartFileToFile(file, createFile(filePath, fileName));
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createFile("/Users/xxx/Desktop", "111.txt");
    }
}
