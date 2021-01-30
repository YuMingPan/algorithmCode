package com.mj;

public class Queens {

	public static void main(String[] args) {
		new Queens().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号
	 */
	int[] cols;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) {
			return;
		}
		cols = new int[n];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 以行为单位来分叉，二叉树是以节点为单位
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {
			ways++; // 找到了，摆法+1
			show();
			return;
		}
		
		for (int col = 0; col < cols.length; col++) {
			// 利用条件判断把不必要的分支减掉
			if (isValid(row, col)) {
				// 在第row行第col列摆放皇后
				cols[row] = col;
				// 返回(回溯)后位于上一次放置皇后的地方，然后继续循环
				place(row + 1);
			}
		}
	}
	
	/**
	 * 判断第row行第col列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		// 拿到row前面那些行
		for (int i = 0; i < row; i++) {
			// 第col列已经有皇后
			if (cols[i] == col) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
			// 第i行的皇后跟第row行第col列格子处在同一斜线上
			// 通过斜率 row - i肯定是个正数
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
		}
		System.out.println("[" + row + "][" + col + "]=true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
}
