package com.xx.tools.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    /**
     * bfile        需要转换成文件的byte数组
     * filePath     生成的文件保存路径
     * fileName     生成文件后保存的名称如test.pdf，test.jpg等
     */
    public static void makeFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            boolean isDir = dir.isDirectory();
            if (!isDir) {// 目录不存在则先建目录
                try {
                    dir.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * object       需要转换成文件的Object對象
     * filePath     生成的文件保存路径
     * fileName     生成文件后保存的名称如test.pdf，test.jpg等
     */
    public static void makeJsonFile(Object object, String filePath, String fileName) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        ;
        String str = mapper.writeValueAsString(object);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            boolean isDir = dir.isDirectory();
            if (!isDir) {// 目录不存在则先建目录
                try {
                    dir.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte bfile[] = new byte[512];
            bfile = str.getBytes();
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * 删除文件和目录
     */
    public static void clearFiles(String workspaceRootPath){
        File file = new File(workspaceRootPath);
        if(file.exists()){
            deleteFile(file);
        }
    }
    private static void deleteFile(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(int i=0; i<files.length; i++){
                deleteFile(files[i]);
            }
        }
        file.delete();
    }

    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    private static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }


    public static byte[] readFile(String filePath,String fileName) throws IOException {
        File  file = new File(filePath + File.separator + fileName);
        InputStream in = new FileInputStream(file);//实例化FileInputStream
        byte[] b = new byte[(int)file.length()];
        int length = in.read(b);
        in.close();
        return b;
    }
}
