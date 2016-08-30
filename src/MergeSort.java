import java.io.File;
import java.util.Scanner;

public class MergeSort {
	
	private long inversions = 0;
	
	public void sort(int[] arrayToSort)
	{
		if (arrayToSort.length <= 1) return;
		
		int[] firstHalf = new int[arrayToSort.length / 2];
		int[] secondHalf = new int[arrayToSort.length - firstHalf.length];
		
		int j = 0;
		for (int i = 0; i < firstHalf.length; i++) firstHalf[i] = arrayToSort[j++];
		for (int i = 0; i < secondHalf.length; i++) secondHalf[i] = arrayToSort[j++];
		
		sort(firstHalf);
		sort(secondHalf);
		
		int fCount = 0;
		int sCount = 0;
		
		for (int i = 0; i < arrayToSort.length; i++)
		{
			if (sCount >= secondHalf.length) arrayToSort[i] = firstHalf[fCount++];
			else if (fCount >= firstHalf.length) arrayToSort[i] = secondHalf[sCount++];
			else if (firstHalf[fCount] <= secondHalf[sCount]) arrayToSort[i] = firstHalf[fCount++];
			else
			{
				arrayToSort[i] = secondHalf[sCount++];
				inversions += (firstHalf.length - fCount);
			}
		}
	}
	
	public long inversions(int[] arrayToSort)
	{
		inversions = 0;
		sort(arrayToSort);
		return inversions;
	}
	
	public static void main(String[] args)
	{
		
		 Scanner sc;
		 String fileName = "/Users/chonchen/Desktop/numrecords.txt";
	     try {
	    	 sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	         return;
	    }
	    
	    int[] intArray = new int[100000];
	    int i = 0;
	    while (sc.hasNextInt())
	    {
	    	intArray[i++] = sc.nextInt();
	    }
		
	    for (int j = 0; j < 20; j++)
	    {
	    	System.out.println(intArray[j]);
	    }
	
		MergeSort m = new MergeSort();
		
		System.out.println(m.inversions(intArray));
		sc.close();
	}
}
