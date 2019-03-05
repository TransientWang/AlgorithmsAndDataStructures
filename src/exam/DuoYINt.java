package exam;


/**
 * @author wangfy
 * @Description 多益网络答题
 * @date 2019/3/1
 **/
public class DuoYINt {
    static int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6};
    /**
     * @date 2019/3/1 17:13
     * @return void
     * @Description n 个数，第 i和数的权重是 i。从中抽奖，要求抽中的概率跟权重有关。
     * @Param [args]
     **/
    public static void main(String[] args) {
        double random = Math.random();
        int sumWeight = 0;
        for (int i = 0; i < arr.length; i++) {
            sumWeight += i;
        }
        double left = 0;
        double right = 0;
        for (int j = 0; j < arr.length; j++) {
            right += (double)j / sumWeight;
            if (j != 0) {
                left += (double) (j-1) / sumWeight;
            }
            if (left <= random && right >= random) {
                System.out.println(j);
                break;
            }
        }


    }
}
