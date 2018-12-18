package core;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestHarness {
	
	private int numItems = 10000000; // Adjust for new # items.
	private double multFactor = 10; // Adjust for new range of random numbers.
	private int divFactor = 100; // Adjust for new compression factor in the HashTable.
	
	@Test
	public void tests() throws InterruptedException {
		
		/* Main Variables */
		double[] max = new double[3];
		double[] min = new double[3];
		int coordID = 0;
		Point [] points = new Point[numItems];
		
		/* Generate Random Points */
		System.out.println("Generating random Points.");
		
		Random RNG = new Random();
		
		for (int i = 0; i < numItems; i++) {
			double x = RNG.nextDouble()*multFactor;
			double y = RNG.nextDouble()*multFactor;
			double z = RNG.nextDouble()*multFactor;
			points[i] = new Point(x, y, z);
			if (x > max[0])
				max[0] = x;
			else if (x < min[0])
				min[0] = x;
			if (y > max[1])
				max[1] = y;
			else if (y < min[1])
				min[1] = y;
			if (z > max[2])
				max[2] = z;
			else if (z < min[2])
				min[2] = z;
		}
		
		/* Get the coordID for the max/min Arrays */
		double xdif = Math.abs(max[0] - min[0]);
		double ydif = Math.abs(max[1] - min[1]);
		double zdif = Math.abs(max[2] - min[2]);
		if (xdif > ydif && xdif > zdif)
			coordID = 0;
		else if (ydif > xdif && ydif > zdif)
			coordID = 1;
		else
			coordID = 2;
		
		/* Sort the Points */
		long systime = System.currentTimeMillis(); // Get time at start.
		
		HashedList hashlist = new HashedList(numItems/divFactor, coordID, max[coordID], min[coordID]);
		
		ExecutorService cache = Executors.newCachedThreadPool();
		
		for (int i = 0; i < numItems; i++) {
			Point item = points[i];
			cache.submit(new Runnable(){
				public void run() {
					hashlist.insert(item);
				}
			});
		}
		
		cache.shutdown();
		cache.awaitTermination(1, TimeUnit.DAYS);
		
		long check = System.currentTimeMillis(); // Get time at end.
		System.out.println("Finished Sorting: " + (check - systime) + " ms"); // Print total ellapsed time (ms).
		
	}
	
}
