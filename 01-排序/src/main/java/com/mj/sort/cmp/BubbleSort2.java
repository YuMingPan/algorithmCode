package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

	/**
	 * 如果数据太随机，则优化了也不一定有用，还额外增加了复杂度
	 */
	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			// 每沉入一个最大的数据我都可以看看剩下的数据是否有序
			boolean sorted = true;
			// 基于第一次遍历我会把所有元素都遍历一次，第n次同理
			for (int begin = 1; begin <= end; begin++) {
				// 进入if则代表有交换，传入的数组并不是完全有序的，更改flag
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sorted = false;
				}
			}
			// 如果第一次遍历完发现数据完全有序，提前终止外层for循环
			if (sorted) break;
		}
	}

}
