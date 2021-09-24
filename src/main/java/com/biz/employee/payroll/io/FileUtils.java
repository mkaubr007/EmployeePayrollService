package com.biz.employee.payroll.io;

import java.io.File;

public class FileUtils {
    public static boolean deleteFiles(File files){
        File[] listFiles=files.listFiles();
        if(listFiles !=null) {
            for (File file : listFiles) {
                deleteFiles(file);
            }
        }
        return files.delete();
    }
}
