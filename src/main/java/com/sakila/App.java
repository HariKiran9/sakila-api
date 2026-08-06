package com.sakila;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		int a[] = { 1, 2, 4, 5, 6 };

		String input[] = { "1", "2", "3", "3", "4", "6", "4" };
//		int miss = getMissingNo2(a);
//		System.out.println(miss);

		int a[] = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			a[i] = Integer.parseInt(input[i]);
		}

		// Calculate the max value in given Array
		int max = calculateArrayMaxValue(a);

		// Create another Array of same size
		// By default all values initialize to 0
		// default value of int
		int[] copyArray = new int[100];

		// Iterate through the input array
		// Mark all present numbers in copyArray
		for (int i : a) {
			copyArray[i] = 1;
		}

		// Print the missing numbers
		System.out.print("Missing numbers in an array are : ");

		String result = "";
		for (int i = 1; i <= max; i++) {
			if (copyArray[i] == 0) {
				System.out.print(i + " ");
				result = i + " ";
			}
		}

		result = result + a.length + "\n";
//		System.out.println("abd : " + result);

//		double rate = calculateHoldingValue("20190506");
//		System.out.println(" Rate : " + rate);

		String password = toSHA1("12345");
		System.out.println(" password : " + password);

	}

	public static String toSHA1(String input) {
		StringBuffer sb = new StringBuffer();
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());

			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	static int getMissingNo(int a[]) {
		int x1 = a[0];
		int x2 = 1;
		int n = a.length;

		/*
		 * For xor of all the elements in array
		 */
		for (int i = 1; i < n; i++)
			x1 = x1 ^ a[i];

		/*
		 * For xor of all the elements from 1 to n+1
		 */
		for (int i = 2; i <= n + 1; i++)
			x2 = x2 ^ i;

		System.out.println("Price : " + (x1 ^ x2));
		return (x1 ^ x2);
	}

	static int getMissingNo2(int a[]) {
		int i, total;
		int n = a.length;
		total = (n + 1) * (n + 2) / 2;
		for (i = 0; i < n; i++)
			total -= a[i];
		return total;
	}

	public static int calculateArrayMaxValue(int[] input) {
		// Initialize maximum element
		int max = input[0];
		// Iterating array elements from second and
		// compare every element with current max
		for (int i = 1; i < input.length; i++)
			if (input[i] > max)
				max = input[i];

		return max;

	}

	private static String sendGet(String url) throws Exception {
		System.out.println("\nSending 'GET' request to URL : " + url);

		// Convert string to URI, open stream, and read all lines into one String
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(URI.create(url).toURL().openStream()))) {
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}

	public static double calculateHoldingValue(String date) {
		System.out.println("Date : " + date);
		double returnValue = 0.0;
		String url1 = "https://api.myjson.com/bins/vf9ac";
		String url2 = "https://api.myjson.com/bins/1eleys";

		try {
			// 1. Initialize Jackson's ObjectMapper instead of Gson
			ObjectMapper mapper = new ObjectMapper();

			// 2. Fetch and parse Price Data
			String strJSON1 = sendGet(url1);
			Price price = mapper.readValue(strJSON1, Price.class);
			System.out.println(" Price Total Records : " + price.getTotalRecords());

			// 3. Fetch and parse Quantity Data
			String strJSON2 = sendGet(url2);
			Quantity quantity = mapper.readValue(strJSON2, Quantity.class);
			System.out.println(" Quantity Total Records : " + quantity.getTotalRecords());

			List<Trans> priceList = price.getData();
			List<Trans> quantityList = quantity.getData();

			// 4. PERFORMANCE OPTIMIZATION: Map the quantities by Security name for O(1)
			// lookup
			// Filter by target date first to minimize memory consumption
			Map<String, Double> quantityMap = quantityList.stream().filter(q -> q.getDate().equalsIgnoreCase(date))
					.collect(Collectors.toMap(Trans::getSecurity, Trans::getQuantity,
							(existing, replacement) -> existing + replacement // Handles duplicate securities cleanly
					));

			// 5. Compute holding values using a fast single-pass loop
			for (Trans pTrans : priceList) {
				if (pTrans.getDate().equalsIgnoreCase(date)) {
					String securityName = pTrans.getSecurity();

					// Fast checking inside our localized hash map instead of running an entire
					// nested loop
					if (quantityMap.containsKey(securityName)) {
						Double qty = quantityMap.get(securityName);
						Double prc = pTrans.getPrice();

						System.out.println("Company : " + securityName + ", Price : " + prc + ", Quantity : " + qty);
						returnValue += prc * qty;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

}
