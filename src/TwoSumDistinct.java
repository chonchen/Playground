import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TwoSumDistinct {
	private Set<Long> set = new HashSet<>();
	
	public void add(long value){
		set.add(value);
	}
	
	public int distinctValues(long lowerBound, long upperBound)
	{
		int count = 0;
		for (long i = lowerBound; i <= upperBound; i++)
		{
			for (Long j: set){
				long difference = i - j;
				if (difference != j && set.contains(difference))
				{
					count++;
					break;
				}	
			}
		}
		return count;
	}
	
	public static void main(String[] args){
		TwoSumDistinct TSD = new TwoSumDistinct();
		
		
		String fileName = "E:/Downloads/2sum.txt";
		Scanner sc = null;
	    try {
	    	sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	    }
	    
	    while (sc.hasNextLine()){
	    	TSD.add(Long.valueOf(sc.nextLine()));
	    }
	    sc.close();
	    System.out.println(TSD.distinctValues(-10000,10000));
	}
}
