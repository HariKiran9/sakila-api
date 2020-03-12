package com.sakila;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.gson.Gson;

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

	private static final String USER_AGENT = "Mozilla/5.0";

	private static String sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		StringBuffer response = new StringBuffer();
		if (responseCode == 200) {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} // while
		} // if
		if (in != null) {
			in.close();
		}
		// print result
//		System.out.println("Response : " + response.toString());
		return response.toString();
	}

	public static double calculateHoldingValue(String date) {
		System.out.print("Date : " + date);
		double returnValue = 0.0f;
		String url1 = "https://api.myjson.com/bins/vf9ac";
		String url2 = "https://api.myjson.com/bins/1eleys";
		try {

			Gson gson = new Gson();

			String strJSON1 = sendGet(url1);
			Price price = gson.fromJson(strJSON1, Price.class);

//			List<Trans> priceList = new ArrayList<>();
			int total = price.getTotalRecords();
			System.out.println(" Price Total Records : " + total);
//			for (Trans trans : price.getData()) {
//				priceList.add(trans);
//			}

			String strJSON2 = sendGet(url2);
			Quantity quantiy = gson.fromJson(strJSON2, Quantity.class);
			int total2 = quantiy.getTotalRecords();
			System.out.println(" Quantity Total Records : " + total2);
//			List<Trans> quantityList = new ArrayList<>();
//			for (Trans trans : quantiy.getData()) {
//				quantityList.add(trans);
//			}

			List<Trans> priceList2 = price.getData();
			List<Trans> quantityList2 = quantiy.getData();

			for (Trans trans : priceList2) {
				if (trans.getDate().equalsIgnoreCase(date)) {
					for (Trans trans2 : quantityList2) {
						if (trans2.getDate().equalsIgnoreCase(date)
								&& trans.getSecurity().equalsIgnoreCase(trans2.getSecurity())) {
							String output = "Company : " + trans.getSecurity() + ", Price : " + trans.getPrice()
									+ ", Quantity : " + trans2.getQuantity();
							System.out.println(output);
							returnValue = returnValue + trans.getPrice() * trans2.getQuantity();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

}
