package main;

import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TTT {
    public static void main(String[] args) {
//        String str="梦幻西游 ONLINE - (福建2区[日光岩] - 心华☆粉[28796279])";
        String str="心华☆粉[28796279]";
        Pattern pattern=Pattern.compile("(梦幻西游 ONLINE - \\(.+\\[.+\\] - )?((.*)\\[\\d+\\])\\)?");
//        Pattern pattern=Pattern.compile("\\(\\)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            System.out.println(matcher.group());
            int count = matcher.groupCount();
            for (int i = 0; i <count+1 ; i++) {
                System.out.println(i+"=>"+matcher.group(i));
            }
        }else {
            System.err.println("not find");
        }

    }
}
