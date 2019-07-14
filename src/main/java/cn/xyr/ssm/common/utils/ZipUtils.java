package cn.xyr.ssm.common.utils;


import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;


public class ZipUtils {

    /**
     * java 压缩zip文件方法
     * 此方法生成的zip文件会设置压缩文件的压缩前大小
     * 避免了其他压缩方法未设置压缩前大小，导致校验失败的问题
     * @author xyr
     * @param srcPathName 输入文件目录
     * @param outPathName 输出zip文件位置
     */
    public static void compress(String srcPathName,String outPathName) {
        File srcdir = new File(srcPathName);
        File zipFile = new File(outPathName);
        if (!srcdir.exists())
            throw new RuntimeException(srcPathName + "不存在！");

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        //fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
        //fileSet.setExcludes(...); 排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
    }
}