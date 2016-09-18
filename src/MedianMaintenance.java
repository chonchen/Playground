import java.io.File;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianMaintenance {
	private PriorityQueue<Integer> minPQ = new PriorityQueue<>();
	private PriorityQueue<Integer> maxPQ = new PriorityQueue<>(11, Collections.reverseOrder());
	
	public void add(int value){
		if (minPQ.isEmpty() && maxPQ.isEmpty()) maxPQ.add(value);
		else{
			int median = getMedian();
			
			if (value > median) minPQ.add(value);
			else maxPQ.add(value);
		
			rebalance();
		}
	}
	
	public int getMedian(){
		if (minPQ.isEmpty() && maxPQ.isEmpty())
			throw new NoSuchElementException();
		
		if (minPQ.size() > maxPQ.size())
			return minPQ.peek();
		else
			return maxPQ.peek();
	}
	
	private void rebalance(){
		if (minPQ.size() > maxPQ.size() + 1)
			maxPQ.add(minPQ.remove());
		
		else if (maxPQ.size() > minPQ.size() + 1)
			minPQ.add(maxPQ.remove());
	}
	
	public static void main(String[] args){
		
		MedianMaintenance MM = new MedianMaintenance();
		long medianSum = 0;
		String fileName = "/Users/chonchen/Downloads/medianmaint.txt";
		Scanner sc = null;
	    try {
	    	sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	    }
	 
	    while (sc.hasNextLine())
	    {
	    	MM.add(Integer.valueOf(sc.nextLine()));
	    	medianSum += MM.getMedian();
	    }
	    sc.close();
	    
	    System.out.println(medianSum % 10000);
	    
	    
	}
}
