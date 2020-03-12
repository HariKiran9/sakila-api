package com.sakila;

import java.util.ArrayList;
import java.util.List;

public class Sample {

	public static void main(String[] args) {
//		example(3);
//		example2(2, 1);
//		example3(100, 0);

		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(1);
		list.add(1);
		list.add(1);
//		System.out.println("Result : " + subarraySum(list));

		// get it right first time
		mySubString();

	}

	public static void mySubString() {
		List<String> list = new ArrayList<String>();
		String str = "get it right first time";
		String words[] = str.split(" ");
		for (String string : words) {
			int index = string.indexOf("t");
			String leftWord = string.substring(0, index);
			String rightWord = string.substring(index, string.length());
			list.add(rightWord + leftWord);
		}

		for (String word : words) {
			System.out.println(word);
		}
	}

	public static long subarraySum(List<Integer> arr) {
		long result = 0, temp = 0;
		for (int i = 0; i < arr.size(); i++) {
			temp = 0;
			for (int j = i; j < arr.size(); j++) {
				temp += arr.get(j);
				result += temp;
			}
		}
		return result;
	}

	public static void example(int x) {
		if (x == 9) {
			x = x + 1;
		} else if (x < 12) {
			x = x + 2;
		} else {
			x = x + 3;
		}
		System.out.println(" x : " + x);
	}

	public static void example2(int n, int sum) {
		while (n != 0) {
			sum = sum * n;
			n = n - 1;
			if (sum < 50) {
				System.out.println("I am late today");
			} else {
				System.out.println("I am not late today");
			}
		}
	}

	public static void example3(int m, int sum) {
		for (int i = 0; i < m; i++) {
			sum = sum + i;
			System.out.println("i: " + i + ", sum : " + sum);
			if (sum > 25)
				break;
		}
	}

}
