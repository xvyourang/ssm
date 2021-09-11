import java.util.*;

/**
 * 输入一行整数
 * 统计出最多的哪些整数
 * 然后将这些数升序、求中位数
 * 中位数： 偶数个是中间2个数的平均值，奇数个是中间的数
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] strings = line.split("\\s");
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (String str : strings) {
            int key = Integer.parseInt(str);
            int value = map.computeIfAbsent(key, k -> 0);
            int count = value + 1;
            max = Math.max(count, max);
            map.put(key, count);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (value == max) {
                list.add(key);
            }
        }
        list.sort(Comparator.comparingInt(i0 -> i0));
        if (list.isEmpty()) {
            throw new NullPointerException();
        }
        int size = list.size();
        if (size % 2 == 0) {
            System.out.println((list.get(size / 2) + list.get(size / 2 - 1)) / 2);
        } else {
            System.out.println(list.get(size / 2));
        }
    }
}
