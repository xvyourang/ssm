package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main000 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File("G:\\360data\\重要数据\\桌面\\关于我转生成史莱姆这件事web.txt")));
        FileWriter writer = new FileWriter(new File("G:\\360data\\重要数据\\桌面\\关于我转生成史莱姆这件事web2.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            int index = line.indexOf("话 ");
            int index0 = line.indexOf("第");
            if (index != -1 && index0 != -1 && index > index0) {
                String num = line.substring(index0 + 1, index + 1);
                System.out.println(num);
                writer.write(line.replace("话", "章"));
                writer.write(System.lineSeparator());
            } else {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }
    }
}
