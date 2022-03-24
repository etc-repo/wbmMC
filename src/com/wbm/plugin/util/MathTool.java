package com.wbm.plugin.util;

public class MathTool {
//	public static isBetween(int a, int b,int c) {
//		
//	}

	// 경계포함
	public static boolean isIn(double a, double target, double b) {
		double big = a, small = b;

		if (a < b) {
			big = b;
			small = a;
		}

		if (small <= target && target <= big) {
			return true;
		}

		return false;
	}

	// 경계포함 x
	public static boolean isBetween(double a, double target, double b) {
		double big = a, small = b;

		if (a < b) {
			big = b;
			small = a;
		}

		if (small < target && target < big) {
			return true;
		}

		return false;
	}

	public static int getDiff(int a, int b) {
		return Math.abs(a - b);
	}

	public static int getBetweenRandom(int a, int b) {
		int small = Math.min(a, b);
		int big = Math.max(a, b);
		return getInRandom(small + 1, big - 1);
	}

	public static int getInRandom(int a, int b) {
		int diff = getDiff(a, b);
		int randomDiff = (int) (Math.random() * (diff + 1));
		int min = Math.min(a, b);
		return min + randomDiff;
	}
}
