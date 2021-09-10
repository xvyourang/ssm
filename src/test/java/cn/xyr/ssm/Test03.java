package cn.xyr.ssm;

import java.io.File;
import java.io.IOException;

public class Test03 {
    public static void main(String[] args) throws Exception {

        File dir = new File("F:\\MUSIC\\old");
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File oldFile : files) {
                    String path = oldFile.getCanonicalPath();
                    int index = path.lastIndexOf(".");
                    String newPath = path.substring(0, index) + "kg" + path.substring(index);
                    File newFile = new File(newPath);
                    System.out.println(newPath);
//                    boolean res = renameTo(oldFile, newFile);
//                    if (!res) {
//                        System.out.println();
//                    }
                }
            }
        }

    }

    private static boolean renameTo(File oldFile, File newFile) {
        if (oldFile.exists()) {
            return oldFile.renameTo(newFile);
        }
        return false;
    }
}
