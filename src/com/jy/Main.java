package com.jy;

import java.util.Arrays;

public class Main {
	public static int INT = 4;

	public static void main(String[] args) {
		int[] numbers = new int[] { 2, 4, 3, 6, 3, 2, 5, 5 };
		System.out.println("输入的数组:" + Arrays.toString(numbers));
		System.out.println("数组中只出现1次的两个数:" + Arrays.toString(findNumbersAppearanceOnce(numbers)));
	}

	/**
	 * 寻找数组中只出现一次的两个数
	 * 
	 * @param numbers
	 *            输入的数组
	 * @return 返回一个数组，数组中的两个元素就是只在数组中出现1次的元素
	 */
	public static int[] findNumbersAppearanceOnce(int[] numbers) {
		int[] result = new int[2];
		if (numbers == null || numbers.length < 2)
			throw new RuntimeException("Invalid input!");
		// 将数组中的每个数依次做异或运算的结果
		int resultExcuteOR = 0;
		for (int i : numbers)
			resultExcuteOR ^= i;
		// 按照从右往左第indexBit是否为1分成两组
		// 两组的数分别依次做异或运算
		int indexBit = findFirstBit1(resultExcuteOR);
		for (int i : numbers) {
			if (IsBit1(i, indexBit))
				result[0] ^= i;
			else
				result[1] ^= i;
		}
		// 返回数组中只出现依次的两个数
		return result;
	}

	/**
	 * 寻找一个数用二进制表示，从右到左第一个1出现的索引
	 * 
	 * @param num
	 *            待查找的数
	 * @return 返回找到的索引值
	 */
	public static int findFirstBit1(int num) {
		int indexBit = 0;
		// 将数不断右移后与1做与运算
		while (((num & 1) == 0) && (indexBit < 8 * INT)) {
			num >>>= 1;
			indexBit++;
		}
		return indexBit;
	}

	/**
	 * 判断一个数用二进制表示，从右往左数第indexBit位是否为1
	 * 
	 * @param num
	 *            输入的数
	 * @param indexBit
	 *            从右往左数的位数
	 * @return 如果为1则返回true,否则返回false
	 */
	public static boolean IsBit1(int num, int indexBit) {
		// 先将数右移indexBit位
		num >>>= indexBit;
		// 再与1做与运算
		return (num & 1) == 1;
	}

}
