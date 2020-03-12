/**
 * 
 */
package com.sakila;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author bc887d
 *
 */
public class AccumalatorEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int processros = Runtime.getRuntime().availableProcessors();
		System.out.println("No.of Processors : " + processros);
		ExecutorService service = Executors.newFixedThreadPool(4);
		try {
			LongAccumulator counter = new LongAccumulator((x, y) -> x + y, 0);

			for (int i = 0; i < 100; i++) {
				service.execute(new Task(counter));
			}
			service.shutdown();

			Thread.sleep(2002);

			System.out.println(counter.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(" isTerminated : " + service.isTerminated());
	}

}
