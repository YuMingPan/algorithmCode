package com.pym;

import org.junit.Test;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{100,1,1,1}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 求 begin 到 end 范围内的最大连续子数组，左闭右开
     */
    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) {
            return nums[begin];
        }
        int mid = (begin + end) >> 1;

        // 最大连续子数组在中间(跨越左右两侧)
        int maxLeftVal = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum = leftSum + nums[i];
            maxLeftVal = Math.max(maxLeftVal, leftSum);
        }

        int maxRightVal = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum = rightSum + nums[i];
            maxRightVal = Math.max(maxRightVal, rightSum);
        }

        // 最大连续子数组在左侧
        int maxLeftSubArray = maxSubArray(nums, begin, mid);
        // 最大连续子数组在右侧
        int maxRightSubArray = maxSubArray(nums, mid, end);
        int max = Math.max(maxLeftSubArray, maxRightSubArray);
        return Math.max(max, maxLeftVal + maxRightVal);
    }
}