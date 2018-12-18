package core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.smurn.jply.Element;
import org.smurn.jply.ElementReader;
import org.smurn.jply.PlyReader;
import org.smurn.jply.PlyReaderFile;

public class Run {
	
	@Test
	public void run() throws InterruptedException, IOException {
		
		System.out.println("Starting...");
		
		long systime = System.currentTimeMillis();
		
		File plyfolder = new File("./plys");
		File[] filelist = plyfolder.listFiles();
		
		PlyReader ply = new PlyReaderFile(filelist[0]);
		
		int size = ply.getElementCount("vertex");
		
		RawPly rawply = new RawPly(size);
		
		ElementReader reader = ply.nextElementReader();
		
		for (int i = 0; i < size; i++) {
			Element element = reader.readElement();
			double x = element.getDouble("x");
			double y = element.getDouble("y");
			double z = element.getDouble("z");
			rawply.insert(x, y, z);
		}
		
		reader.close();
		ply.close();
		
		long check = System.currentTimeMillis();
		System.out.println("Finished Reading PLY File(s): " + (check - systime) + " ms");
		systime = check;
		
		int divfactor = 100;
		int coordid = rawply.getCoordID();
		
		HashedList hashlist = new HashedList(size/divfactor, coordid, rawply.max[coordid], rawply.min[coordid]);
		
		ExecutorService cache = Executors.newCachedThreadPool();
		
		for (int i = 0; i < size; i++) {
			Point item = rawply.get(i);
			cache.submit(new Runnable(){
				public void run() {
					hashlist.insert(item);
				}
			});
		}
		
		cache.shutdown();
		cache.awaitTermination(1, TimeUnit.DAYS);
		
		check = System.currentTimeMillis();
		System.out.println("Finished Sorting Data: " + (check - systime) + " ms");
		systime = check;
	}
}
