package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

	/**
	 * 排序前(中)完全有序几率很小，但是排序前(中)最后几个数据有序的几率较大
	 * 不仅针对完全有序的情况，还针对最后几个数据有序的情况
	 */
	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			// 如果完全有序，则不会进入内层if，应该让其终止循环(<=1即可)
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, begin - 1) <= 0) {
					swap(begin, begin - 1);
					sortedIndex = begin;
				}
			}
			// 减少外层循环次数
			end = sortedIndex;
		}
	}

}
