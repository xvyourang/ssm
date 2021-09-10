package cn.xyr.ssm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test08 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.sort((i0, i1) -> {
            return i0 - i1;
        });
        System.out.println(list);
    }
}
