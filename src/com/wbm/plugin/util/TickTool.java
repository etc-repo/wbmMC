package com.wbm.plugin.util;

public class TickTool {
	public static class Real2Tick {
		public static long seconds(int amount) {
			return amount * 20;
		}

		public static long minutes(int amount) {
			return seconds(60) * amount;
		}

		public static long hours(int amount) {
			return minutes(60) * amount;
		}

		public static long days(int amount) {
			return hours(24) * amount;
		}

		public static long weeks(int amount) {
			return days(7) * amount;
		}
	}

	public static class Tick2Real {
		// TODO: 반환값을 LocalDateTime으로 바꾸기
		public static long seconds(int amount) {
			return amount / 20;
		}

		public static long minutes(int amount) {
			return seconds(amount) / 60;
		}

		public static long hours(int amount) {
			return minutes(amount) / 60;
		}

		public static long days(int amount) {
			return hours(amount) / 24;
		}

		public static long weeks(int amount) {
			return days(amount) / 7;
		}
	}
}
