package test.sort;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

public class suanfafenxi {
    @Test
    public void PrintFibonacci() {
        Fibonacci(10);
    }

    /**
     * 斐波那契数列
     * 初始值：当n=0|1的时候 返回1
     * 放N>1时，返回f(n-1)+f(n-2)
     */
    public void Fibonacci(int i) {
        Integer a[] = new Integer[i];
        for (int j = 0; j < i; j++) {
            if (j <= 1) {
                a[j] = 1;

            } else {
                a[j] = a[j - 1] + a[j - 2];
            }
            System.out.println(a[j] + " ");
        }
    }


    @Test
    public void huafen() {
        System.out.println(q(6, 6));
    }

    /**
     * 整数划分问题，
     * 当最n=1时，n={1}.此时只有一种划分
     * /   m个    \
     * 当最大加数m=1时。n=m个1相加n={1+1+1+...+1+1+1}
     * 当n=m时有两种情况
     * 1、当包括m时，n={n},此时只有一种情况
     * 2、当不包括n时，此时最大加数比n小,最大为n-1。加上第一种的1中情况 即为 1+q(n,n-1)
     * 当n>m时候，有两种情况
     * 1、当不包含m时，划分中的最大加数为m-1 化分数为q(n,m-1)
     * 2、当包含m时，划分中一定会包含一个m,那么要求得划分数就是剩下的n-m的划分即为去q（n-m,n）
     * 加上第一种情况即为：q（n,m-1）+q(n-m,m)
     *
     * @param n
     * @param m
     * @return
     */
    int q(int n, int m) {
        if ((n < 1) || (m < 1))
            return 0;
        if ((n == 1) || (m == 1)) return 1;
        if (n < m) return q(n, n);
        if (n == m) return q(n, m - 1) + 1;
        return q(n, m - 1) + q(n - m, m);
    }

    private static int m;

    @Test
    public void testMergeSort() {
        Random random = new Random(100);
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = Math.abs(random.nextInt() % 100);
        }
        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));
        System.out.println();
        mergeSort(arr, 0, arr.length - 1);
        System.out.println();
        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));

    }

    public static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (right + left) / 2;
//            System.out.print("子数组left:");
//            for (int i = left; i <= mid; i++) {
//                System.out.print(a[i] + " ");
//            }
//            System.out.print("子数组right:");
//            for (int i = mid + 1; i <= right; i++) {
//                System.out.print(a[i] + " ");
//            }
//            System.out.println();
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public static void merge(int[] a, int left, int mid, int right) {
//        System.out.print("合并:" + left + "-" + mid + "与" + (mid + 1) + "-" + right);
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
//        System.out.println("创建临时数组");
        while (i <= mid && j <= right) {
            if (a[j] > a[i]) {
//                System.out.println("将a[" + i + "]放入临时数组中");
                temp[k++] = a[i++];
            } else {
//                System.out.println("将a[" + j + "]放入临时数组中");
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
//            System.out.println("将左边a[" + i + "]放到临时数组中");
            temp[k++] = a[i++];
        }

        while (j <= right) {
//            System.out.println("将右边a[" + j + "]放到临时数组中");
            temp[k++] = a[j++];
        }
//        System.out.println("将临时数组中的数放回原数组中");
        for (int l = 0; l < temp.length; l++) {
            a[l + left] = temp[l];
        }
//        System.out.println();


    }

    @Test
    public void StTest() {

        LCS_caculate("awangkaugt", "cwangznhyd");
    }

    public static void LCS_caculate(String s1, String s2) {
        var chess = new int[s1.length() + 1][s2.length() + 1];//
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    chess[i][j] = chess[i - 1][j - 1] + 1;
                else
                    chess[i][j] = Math.max(chess[i][(j - 1)], chess[(i - 1)][j]);
            }

        }

        for (int i = 0; i < chess.length; i++) {
            System.out.println();
            for (int j = 0; j < chess[i].length; j++) {
                System.out.print(chess[i][j] + " ");
            }

        }
        System.out.println();

        var k = s1.length();
        var h = s2.length();
        var stringBuilder = new StringBuilder();
        while (k != 0 && h != 0) {
            if (s1.charAt(k - 1) == s2.charAt(h - 1)) {
                stringBuilder.append(s1.charAt(k - 1));
                k--;
                h--;
            } else {
                if (chess[k][h - 1] > chess[k - 1][h])
                    h--;
                else
                    k--;
            }

        }
        System.out.println(stringBuilder.reverse().toString());
    }


    @Test
    public void jdk11Test() {
        URL url = null;
        try {
            url = new URL("https://www.ijinshan.com/sem/xintop/f29.shtml?sfrom=166&keyID=1475");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            var connection = (URLConnection) url.openConnection();
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            var s = "";
            while ((s = reader.readLine()) != null) {
                var src = 0;
                if ((src = s.indexOf("src=\"//")) != -1) {
                    var pic = s.substring(src + 7, s.indexOf("\"", src + 7));
                    if (pic.endsWith("png")) {
                        pic = "https://" + pic;
                        url = new URL(pic);
                        connection = url.openConnection();
                        var name = pic.substring(pic.lastIndexOf("/") + 1, pic.lastIndexOf("."));
                        var outputStream = new FileOutputStream("D:\\pic\\" + name + System.currentTimeMillis() % 100 + ".png");
                        var stream = connection.getInputStream();
                        var ch = stream.readAllBytes();
                        outputStream.write(ch);
                        stream.close();
                        outputStream.close();
                        out.println("下载成功" + name + ".png");
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testMasSubArray() {
        int[] arr = new int[10];
        Random random = new Random(100);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt() % 100;
        }
        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));
        int[] result = findMaxSubArray(arr, 0, 9);
        System.out.println("结果为：左边界=" + result[0] + " 右边界=" + result[1] + " 最大值=" + result[2]);
    }

    /**
     * 最大子数组问题
     * 采用分治策略
     * 将原数组分成两个组数组
     * 则结果又三种情况
     * 1、结果在左边的子数组中
     * 2、结果在右边的子数组中
     * 3、结果横跨做左右子数组，则需要计算从mid开始向两边计算最长字数组
     * 基本情况：当数组长度为1时，直接返回left就可以
     * 时间复杂度：先考虑一次计算所需要的时间，由于每次把原数组分成两个大小相等的子数组，
     * 设计算原数组的时间为T(n)，将数组一分为二之后计算的时间为T(n/2)。两个子数组共需要2T(n/2)
     * 计算横跨中间的数组其实是遍历了数组，花费时间为θ(n)，总时间为2T(n/2)+θ(n)
     * 基本情况计算时间为θ（1）
     * 再看由于每次吧原数组一分为二，则可以吧计算过程化成一棵二叉树。
     * 二叉树有lgn+1层。与单层计算时间相乘
     * 时间复复杂度为 n(lgn)
     *
     * @param a
     * @param left
     * @param right
     * @return
     */

    public int[] findMaxSubArray(int[] a, int left, int right) {
        if (left == right)
            return new int[]{left, left, a[left]};
        int mid = (left + right) / 2;
        int[] leftMax = findMaxSubArray(a, left, mid);
        int[] rightMax = findMaxSubArray(a, mid + 1, right);
        int[] crossMax = findMaxCrossSubArray(a, left, mid, right);
        if (leftMax[2] > rightMax[2] && leftMax[2] > crossMax[2])
            return leftMax;
        else if (rightMax[2] > leftMax[2] && rightMax[2] > crossMax[2])
            return rightMax;
        else return crossMax;
    }

    public int[] findMaxCrossSubArray(int[] a, int left, int mid, int right) {
        int leftSum = 0;
        int rightSum = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            if (a[i] + leftSum > leftSum) {
                leftSum += a[i];
                leftIndex = i;
            }

        }
        for (int j = mid + 1; j <= right; j++) {
            if (a[j] + rightSum > rightSum) {
                leftSum += a[j];
                rightIndex = j;
            }
        }
        return new int[]{leftIndex, rightIndex, leftSum + rightSum};

    }

    @Test
    public void heapTest() {
        int[] arr = new int[10];
        Random random = new Random(88);
        for (int i = 0; i < 10; i++) {
            arr[i] = Math.abs(random.nextInt() % 100);
        }
        System.out.print("原数组：    ");
        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));
        System.out.println();
        heapSort(arr);
        System.out.print("结果：      ");
        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));
        System.out.println();
    }

    /**
     * 堆排序
     * 1、先把数组变成大顶堆（父节点大于两个子节点）
     * 2、然后把堆顶a[0]排出堆外（把堆顶值放到最后一位，然后缩小堆的值）
     * 这样现在最大值就在外面了，但是现在堆不满足最大堆了。
     * 3、需要重新调整（直接从堆顶开始调整）
     * 然后重复，2,3,步骤知道堆缩小到只有一个元素为止
     * @param arr
     */
    public void heapSort(int[] arr) {
        int heapSize = arr.length - 1;
        buildMaxHeap(arr);
//        System.out.print("大顶堆数组：");
//        Arrays.stream(arr).forEachOrdered(o -> System.out.print(o + " "));
//        System.out.println();
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapAdjust(arr, 0, i - 1);
        }
    }

    //构建出大顶堆
    //从最后一个有叶子节点的节点开始，从后向前调整
    private static void buildMaxHeap(int[] a) {
        for (int i = (a.length) / 2 - 1; i >= 0; i--) {
            heapAdjust(a, i, a.length - 1);
        }
    }

    //调整堆，使其恢复为大顶堆
    //左右叶子节点变换后都需要重新调整，因为两个叶子节点都有可能不满足条件需要调整
    private static void heapAdjust(int[] a, int index, int heapSize) {
        int left, right, largest;
        left = (index * 2) + 1;   //左孩子
        right = (index * 2) + 2;  //右孩子
        if (left <= heapSize && a[left] > a[index]) {
            largest = left;
            swap(a, index, largest);
            heapAdjust(a, largest, heapSize);
        }
        if (right <= heapSize && a[right] > a[index]) {
            largest = right;
            swap(a, index, largest);
            heapAdjust(a, largest, heapSize);
        }
    }

    public static void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }


}
