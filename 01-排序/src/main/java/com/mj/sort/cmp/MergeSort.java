package com.mj.sort.cmp;

import com.mj.sort.Sort;

@SuppressWarnings("unchecked")
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
	private T[] leftArray;

	@Override
	protected void sort() {
		leftArray = (T[]) new Comparable[array.length >> 1];
		sort(0, array.length);
	}
	
	/**
	 * 对 [begin, end) 范围的数据进行归并排序
	 */
	private void sort(int begin, int end) {
		// end - begin 是元素数量
		if (end - begin < 2) return;
		
		int mid = (begin + end) >> 1;
		sort(begin, mid);
		sort(mid, end);
		merge(begin, mid, end);
	}
	
	/**
	 * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
	 */
	private void merge(int begin, int mid, int end) {
		//这个li为啥为0？li初始为左边数组第一个，而且这个“左边”数组会被备份，所以并不作用在begin上面
		int li = 0, le = mid - begin;
		int ri = mid, re = end;
		int ai = begin;
		
		// 备份左边数组，begin可不一定从0开始。因为是大数组中的begin到end，而不是0到end
		// leftArray是copy出来的数组
		// array是原数组
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}
		
		// 如果左边还没有结束
		while (li < le) { 
			if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
				array[ai++] = array[ri++];
			} else {
				array[ai++] = leftArray[li++];
			}
		}
	}
}
