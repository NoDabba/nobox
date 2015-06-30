/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author sdoddi
 * this class will create unique ID for that node
 */
public final class OrderIDGeneratorUtil {
	
	

	public static void main(String argsp[]) {
		NextSequenceNumber sequenceGenerator = new NextSequenceNumber();
		
		
		long currentTimeInMillis =  System.currentTimeMillis();
		System.out.println("currentTimeInMillis => " + currentTimeInMillis);
		long mutiplyFactor = 12345678;
		Random random = new Random();
		Map<Long, Long> dataSet = new HashMap<Long, Long>();
		while (true) {
			
			long nextNumber = sequenceGenerator.getNextSequenceNumber();
			long randomNumber1 = mutiplyFactor * nextNumber;
			long randomNumber2 = random.nextInt((12345678-1)+1);
			long total = randomNumber1+randomNumber2;
			//System.out.println("print => "+ total);

			if (!dataSet.containsKey(total))
				dataSet.put(total, total);
			else 
				System.out.println("total => "+ total);
			try {
				Thread.sleep(1);
			}
			catch(Exception ex) {
			}
		}
	}
	
	private static class NextSequenceNumber {
		private AtomicLong number;
		public NextSequenceNumber() {
			number = new AtomicLong(121);//this should be unique when ever we start the server
		}
		public long getNextSequenceNumber() {
			return number.incrementAndGet();
		}
		
	}
}
