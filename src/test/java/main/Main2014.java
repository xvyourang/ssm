package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 带权值的区间最大值
 */
public class Main2014 {
    /**
     * 升序存放区间
     */
    private static List<Section> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add(new Section(2, 3, 0));
        list.add(new Section(4, 5, 1));
        list.add(new Section(5, 6, 2));
        list.add(new Section(7, 8, 4));
        list.add(new Section(9, 11, 5));
        list.add(new Section(11, 20, 6));

        find(6, 7);

    }

    private static void marge(int start, int end) {
        Section section = find(start, end);
        int index = section.start;
        int max = section.end;
        if (index == -1) {
            list.add(new Section(start, end, 1));
        } else {
            if (index == max) {
                list.add(index, new Section(start, end, 1));
            } else {
                if (start > index) {

                } else {

                }
            }
        }
    }

    /**
     * 找到所有start和end的区间
     * 2，3，5
     * 3，6，10
     * 1，5
     * 1 2 3 4 5 6 7 8 9
     * 3
     */
    private static Section find(int start, int end) {
        // 2分查找
        if (list.size() > 0) {
            int left = 0;
            int right = list.size() - 1;
            int mid = Integer.MAX_VALUE;
            if (list.get(right).start <= start) {
                mid = right;
            } else {
                // 找左边界
                while (left < right) {
                    mid = (right - left) / 2 + left;
                    Section section = list.get(mid);
                    if (section.start < start) {
                        left = mid;
                    } else if (section.start > start) {
                        right = mid;
                    } else {
                        break;
                    }
                    if (right - left == 1) {
                        if (list.get(left).start < start && start < list.get(right).start) {
                            mid = left;
                            break;
                        }
                    }
//                    System.out.println(left + "," + mid + "," + right + ",");
                }
            }
            int leftIndex = mid;
            // 判断有没有比start大的
            if (leftIndex + 2 < list.size()) {
                Section section = list.get(leftIndex);
                if (section.start < start) {
                    leftIndex = leftIndex + 1;
                }
            }
//            System.out.println("**************");
            left = 0;
            right = list.size() - 1;
            mid = Integer.MAX_VALUE;
            // 找右边界
            if (list.get(right).end <= end) {
                mid = right;
            } else if (list.get(0).end >= end) {
                mid = -1;
            } else {
                while (left < right) {
                    mid = (right - left) / 2 + left;
                    Section section = list.get(mid);
                    if (section.end < end) {
                        left = mid;
                    } else if (section.end > end) {
                        right = mid;
                    } else {
                        break;
                    }
                    if (right - left == 1) {
                        if (list.get(left).end < end && end < list.get(right).end) {
                            mid = left;
                            break;
                        }
                    }
//                    System.out.println(left + "," + mid + "," + right + ",");
                }
            }

            int rightIndex = mid;
            System.out.println(start + "," + end + ";" + leftIndex + "->" + (rightIndex + 1));
            return new Section(leftIndex, rightIndex, 0);
        }
        return new Section(-1, -1, 0);
    }

    private static class Section {
        int start;
        int end;
        int count;

        public Section(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Section section = (Section) o;
            return start == section.start && end == section.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "Section{" +
                    "start=" + start +
                    ", end=" + end +
                    ", count=" + count +
                    '}';
        }
    }
}
