package com.good.web.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtils {
	
	public static String saveFile(File file, String savePath, String filename , boolean isAutoName){
		FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            // 建立文件输出流
        	String savename = filename;
        	if(isAutoName){
        		savename = getSaveName(file,filename);
        	}
            System.out.println(savePath + File.separator + savename);
            fos = new FileOutputStream(savePath + File.separator + savename);
            // 建立文件上传流
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            return savename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close(fos, fis);
        }
	}
	
	private static String getSaveName(File file,String filename){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Random rd = new Random();
		if(ValidateUtil.str_isEmpty(filename)){
			return null;
		} else {
			//获得后缀
			String typename = filename.substring(filename.lastIndexOf(".")+1);
			//随机产生名字
			String savename = sdf.format(new Date())+"_"+rd.nextInt(999999)+"."+typename; 
			return savename;
		}
	}
	
	private static void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }
	
	public static final void main(String argv[]){
		File file = new File("E:\\private\\P1050271.JPG");
		System.out.println(getSaveName(file,"P1050271.JPG"));
	}

}
