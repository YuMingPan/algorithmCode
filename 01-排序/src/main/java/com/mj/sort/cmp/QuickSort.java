package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		sort(0, array.length);
	}

	/**
	 * 对 [begin, end) 范围的元素进行快速排序
	 * @param begin
	 * @param end
	 */
	private void sort(int begin, int end) {
		// 只有一个元素则直接return
		if (end - begin < 2) return;
		
		// 确定轴点位置 O(n)，即扫描范围内每一个元素，虽然取名为mid但实际上并不一定在正中间
		int mid = pivotIndex(begin, end);
		// 对子序列进行快速排序
		sort(begin, mid); 
		sort(mid + 1, end); 
	} 
	
	/**
	 * 构造出 [begin, end) 范围的轴点元素
	 * @return 轴点元素的最终位置
	 */
	private int pivotIndex(int begin, int end) {
		// 随机选择一个元素跟begin位置进行交换，防止出现最坏时间复杂度的出现
		swap(begin, begin + (int)(Math.random() * (end - begin)));

		// 备份begin位置的元素
		T pivot = array[begin];
		// 让end指向最后一个元素，因为当前范围是左闭右开的
		end--;

		// begin == end 则找到轴点元素索引
		while (begin < end) {
			while (begin < end) {
				if (cmp(pivot, array[end]) < 0) { // 右边元素 > 轴点元素，不要加上等号！可能会导致最坏时间复杂度的出现
					end--;
				} else { // 右边元素 <= 轴点元素
					array[begin++] = array[end];
					break;
				}
			}
			while (begin < end) {
				if (cmp(pivot, array[begin]) > 0) { // 左边元素 < 轴点元素，不要加上等号！
					begin++;
				} else { // 左边元素 >= 轴点元素
					array[end--] = array[begin];
					break;
				}
			}
		}
		
		// 将轴点元素放入最终的位置
		array[begin] = pivot;
		// 返回轴点元素的位置
		return begin;
	}
}
