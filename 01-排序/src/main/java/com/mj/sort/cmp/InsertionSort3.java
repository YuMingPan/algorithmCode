package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        // 从1位置开始抽牌一直抽到最后
        for (int begin = 1; begin < array.length; begin++) {
            //将source位置的元素插入到dest位置
            insert(begin, search(begin));
        }
    }

    private void insert(int source, int dest) {
        T v = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;
    }

    //利用二分搜索找到 index 位置元素的待插入位置，已经排好序数组的区间范围是 [0, index)
    private int search(int index) {
        int begin = 0;
        // end为有序数组最大索引+1的位置，即有序数组长度
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
