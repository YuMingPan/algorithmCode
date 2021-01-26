package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			// begin(索引)从哪开始 end(索引)到哪结束 ，要赋予ij意义
			for (int begin = 1; begin <= end; begin++) {
				// 下面的比较考不考虑等号影响到排序的稳定性
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
				}
			}
		}
	}

}
