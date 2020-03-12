/**
 * 
 */
package com.sakila;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author bc887d
 *
 */
public class Task implements Runnable {

	private LongAccumulator counter = null;

	public Task(LongAccumulator counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.accumulate(1);

	}

}
