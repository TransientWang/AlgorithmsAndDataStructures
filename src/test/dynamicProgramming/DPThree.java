package test.dynamicProgramming;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/22
 **/
public class DPThree {
    /**
     * @date 2019/3/22 11:36
     * @return int
     * @Description 377. 组合总和 Ⅳ
     * @Param [nums, target]
     **/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 0 ;i <= target ; i++){
            for(int num : nums){
                if(i + num <= target){
                    dp[i+num] += dp[i];
                }
            }
        }
        return dp[target];

    }
}
