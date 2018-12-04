package test.design;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wangfy
 * @Description 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * @date 2018/12/4
 */

public class MedianFinder {
    //左边大顶堆
    private PriorityQueue<Integer> left;
    //右边小顶堆
    private PriorityQueue<Integer> right;
    //判断奇偶，辅助将元素均匀插入两堆
    private boolean flag;


    /**
     * @return
     * @Description 长度为偶数的时候，两个堆顶，取出来/2
     * 奇数约定好取左边小顶堆堆顶
     * 思路：通过堆来维护中间值，需要两个堆，左边大顶堆，右边小顶堆（右边堆所有元素>左边堆），这样中位数在堆顶。
     * @Param []
     * @Line 22
     **/
    public MedianFinder() {
        left = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        right = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        flag = false;
    }

    /**
     * @return void
     * @Param
     * @Description flag元素均匀分布在左右两个堆中的辅助变量
     * 如果是奇数的话，判断插入的数是否大于右边小顶堆的堆顶最小值，大于说明，
     * 该值大于中位数(由于之前是偶数总数,中位数是两堆顶相加/2，该值加入后中位数变成了左边小顶堆堆顶)，多以应该先插入右边小顶堆，
     * 插入后为了维护左右堆大小差值 <=1 ,应将小顶堆堆顶弹出（此时堆顶就是中位数），插入左边大顶堆。
     * @Line 62
     **/
    public void addNum(int num) {
        flag = !flag;
        if (flag) {

            if (right.size() > 0 && num > right.peek()) {
                right.add(num);
                num = right.poll();
            }
            left.add(num);
        } else {

            if (left.size() > 0 && num < left.peek()) {
                left.add(num);
                num = left.poll();
            }
            right.add(num);
        }
    }

    public double findMedian() {
        if (flag) {
            if (left.size() != 0) {
                return left.peek();
            }
            return right.peek();


        } else {

            return (left.peek() + right.peek()) / 2.0;
        }
    }

    @Test
    public void test() {
        var medfinder = new MedianFinder();
        medfinder.addNum(1);
        medfinder.addNum(2);
        medfinder.addNum(3);
//        medfinder.addNum(4);
//        medfinder.addNum(5);
//        medfinder.addNum(6);

        medfinder.left.forEach(o -> System.out.println(o + " "));
        System.out.println();
        medfinder.right.forEach(o -> System.out.println(o + " "));
        System.out.println();

        System.out.println(medfinder.findMedian());
    }
}
