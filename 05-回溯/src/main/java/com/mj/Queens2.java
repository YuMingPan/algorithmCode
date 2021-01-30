package com.mj;

public class Queens2 {

	public static void main(String[] args) {
		new Queens2().placeQueens(16);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号（相当于Queens中的cols）在此用于展示数据
	 */
	int[] queens;
	/**
	 * 标记着某一列是否有皇后
	 */
	boolean[] cols;
	/**
	 * 标记着某一斜线上是否有皇后（左上角 -> 右下角）
	 */
	boolean[] leftTop;
	/**
	 * 标记着某一斜线上是否有皇后（右上角 -> 左下角）
	 */
	boolean[] rightTop;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) {
			return;
		}
		queens = new int[n];
		cols = new boolean[n];
		leftTop = new boolean[(n << 1) - 1];
		rightTop = new boolean[leftTop.length];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {
			ways++;
//			show();
			return;
		}
		
		for (int col = 0; col < cols.length; col++) {
			// 如果第col列已经有皇后
			if (cols[col]) {
				continue;
			}
			// lt leftTop ；ltIndex 哪一条斜线的索引，算法较为抽象，以八皇后棋盘为基准想象
			int ltIndex = row - col + cols.length - 1;
			if (leftTop[ltIndex]) {
				continue;
			}
			// rt rightTop
			int rtIndex = row +col;
			if (rightTop[rtIndex]) {
				continue;
			}

			// 情况都满足时放置皇后，因为放置了皇后，该列/左右斜线索引标记为true
			queens[row] = col;// 列号直接覆盖，发生回溯不需要还原
			cols[col] = true;
			leftTop[ltIndex] = true;
			rightTop[rtIndex] = true;

			place(row + 1);

			// 回溯时之前放置的皇后的失效
			cols[col] = false;
			leftTop[ltIndex] = false;
			rightTop[rtIndex] = false;
		}
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (queens[row] == col) {
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
