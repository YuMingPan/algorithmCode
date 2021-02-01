package com.mj;

import java.util.Arrays;

public class CoinChange {
	public static void main(String[] args) {
//		coinChange(new Integer[] {25, 10, 5, 1}, 41);

//		coinChange(new Integer[] {25, 20, 5, 1}, 41);
		coinChange1();
	}

	static void coinChange(Integer[] faces, int money) {
		// 1 5 20 25
		Arrays.sort(faces);
		int coins = 0, idx = faces.length - 1;
		while (idx >= 0) {
			while (money >= faces[idx]) {
				System.out.println(faces[idx]);
				money -= faces[idx];
				coins++;
			}
			idx--;
		}
		System.out.println(coins);
	}

	/**
	 * while循环更好一些，顺便接触一下比较器的知识
	 */
	static void coinChange2(Integer[] faces, int money) {
		// 面值从大到小排序
		Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1); 
		int coins = 0, i = 0;
		// 一直选某个硬币，除非某条件成立
		while (i < faces.length) {
			if (money < faces[i]) { i++; continue; }

			System.out.println(faces[i]);
			money -= faces[i];
			coins++;
		}
		System.out.println(coins);
	}

	// 同一面值可重复选择---用总量去减
	static void coinChange1() {
		int[] faces = {25, 5, 10, 1};
		Arrays.sort(faces); // 1, 5, 10, 25 可选项进行排序
		
		int money = 41, coins = 0;	// 已知总量与可选项加和的总量变量/或直接用已知总量去减，个数的变量
		for (int i = faces.length - 1; i >= 0; i--) {
			if (money < faces[i]) {
				continue;
			}

			System.out.println(faces[i]);
			money -= faces[i];
			coins++;
			// 注意，是每次都从最后去扫描；用i++比较好点
			i = faces.length;
			System.out.println("此时i="+i);
		}
		
		System.out.println(coins +"枚");
	}
}
