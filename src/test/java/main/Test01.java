package main;

import java.util.*;

/**
 * 单抽接龙、同样的取最长、然后按acii值排序
 * 类名要是Main
 * 保存调试
 * 先去下一题吧
 * 60分也可以了
 *
 * 160分了 你还要弄吗
 * 那做第3体吧
 *
 *
 */
public class Test01 {
    /**
     * 优先队列
     * 长度优先、acii值优先 这个先
     * ComparatorComparatorComparatorComparator是这个 你打错了
     * Comparator比较类
     * 你jdk版本不是1.8的...
     * 改这种 capy进去就可以了
     * 刷新配置
     * 直接代码粘过去跑算了
     * 看题目、看看漏了什么条件
     */
    private static Comparator<String> comparator = (s0, s1) -> {
        // 长度优先
        // acii值优先
        // 刚刚网络波动了
        // 好了 注释不用写 抄啊 感觉你这样好假、一看就是在抄..
        //注释没必要写的.. 上面
        int len0 = s0.length();
        int len1 = s1.length();
        // 判断长度
        if (len0 != len1) {
            return len1 - len0;
        }
        // 判断acii值 依次对比每个字符的acii 不一样就返回
        // 判断每个字符还是只判断最后一个？
        for (int i = 0; i < len0; i++) {
            char c0 = s0.charAt(i);
            char c1 = s1.charAt(i);

            if (c0 != c1) {
                return c0 - c1;
            }
        }
        // 不可能 出现2个一样的字符串 这句可以换成 return 0;
        return 0;
        //throw new NullPointerException();
    };


    public static void main(String[] args) {
        // 题目copy去idea做啊 你直接在这写也太假了
        // 把实例的输入copy去idea做
        // 你也先做做样子啊..不要等抄代码
        Scanner scanner = new Scanner(System.in);
        int start = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());
        //                0                                  1
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = scanner.nextLine();
        }
        System.out.println(dcjl(start, strs));
        // word dword da

    }
    // 函数名随便起的 你这...
    //不是被抓作弊了
    // 配一下jdk1.8吧

    private static String dcjl(int start, String[] strs) {
        String startStr = strs[start];
        // 前置处理 map key放单词首字母、value放对应的字符串
        // 优先队列 取的时候会按照 长度优先、acii值优先 规则出栈字符串
        Map<Character, PriorityQueue<String>> map = new HashMap<>();
        for (String s : strs) {
            if (s.equals(startStr)) {
                continue;
            }
            char c = s.charAt(0);
            PriorityQueue<String> priorityQueue = map.get(c);
            if (priorityQueue == null) {
                // 这里要赋值的
                priorityQueue = new PriorityQueue<>(comparator);
                map.put(c, priorityQueue);
            }
            priorityQueue.offer(s);
        }
        StringBuilder sb = new StringBuilder();
        // 开始接龙
        sb.append(startStr);
        // 当前接龙字符
        Character c = startStr.charAt(startStr.length() - 1);
        while (true) {
            PriorityQueue<String> priorityQueue = map.get(c);
            if (priorityQueue == null) {
                break;
            } else {
                String peek = priorityQueue.poll();
                sb.append(peek);
                // 所有字符串都用过了 移除
                if (priorityQueue.isEmpty()) {
                    map.remove(c);
                }
                c = peek.charAt(peek.length() - 1);
            }
        }
        return sb.toString();
    }

}
