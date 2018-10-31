package wfy.sort;

public class InsertSort {


    public static <T extends Comparable<? super T>> void insertSort(T[] a) {
        int j = 0;
        for (int i = 1; i < a.length; i++) { //第一个元素，本身就是有序的，所以省略了，直接从第二个开始。
            T tmp = a[i];//保存当前位置p的元素，其中[0,p-1]已经有序
            j = i - 1;  //从当前位置前一个开始比较

            while (j >= 0 && tmp.compareTo(a[j]) < 0) {
                a[j + 1] = a[j];   //元素后移
                --j;  //继续往前查找
            }

            a[j + 1] = tmp;//最后一次多减了,所以要加回来。插入到合适的位置
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {34, 8, 64, 51, 32, 21};
        insertSort(arr);
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }
}
