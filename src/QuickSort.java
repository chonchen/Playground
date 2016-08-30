import java.io.File;
import java.util.Scanner;

public class QuickSort {
	private int comparisons = 0;
	
	public int comparisons(int[] inputArray){
		comparisons = 0;
		sort(inputArray, 0, inputArray.length - 1);
		return comparisons;
	}
	
	public void sort(int[] inputArray, int lo, int hi) {
		
		if (lo >= hi) return;
		
		int mid = lo + (hi - lo) / 2;
		
		int median = findMedian(inputArray, lo, mid, hi);
		
		exchange(inputArray, lo, median);
		
		comparisons += (hi - lo);
		
		int pivot = inputArray[lo];
		int i = lo + 1;
		
		for (int j = lo + 1; j <= hi; j++)
			if (inputArray[j] <= pivot) exchange(inputArray, i++, j);		

		exchange(inputArray, lo, i - 1);
		
		sort(inputArray, lo, i - 2);
		sort(inputArray, i, hi);
	}
	
	private void exchange(int[] inputArray, int a, int b) {
		if (a == b) return;
		int temp = inputArray[a];
		inputArray[a] = inputArray[b];
		inputArray[b] = temp;
	}
	
	private int findMedian(int[] inputArray, int lo, int mid, int hi) {
		int max1 = 0;
		int max2 = 0;
		int loser = 0;
		if (inputArray[lo] > inputArray[hi])
		{
			max1 = lo;
			loser = hi;
		}
		else
		{
			max1 = hi;
			loser = lo;
		}
			
		if (inputArray[loser] > inputArray[mid]) max2 = loser;
		else max2 = mid;
		
		if (inputArray[max1] > inputArray[max2]) return max2;
		else return max1;
	}
	
	
	public static void main(String args[]) {
		

		 Scanner sc;
		 String fileName = "/Users/chonchen/Documents/quicksorttest4.txt";
	     try {
	    	 sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	         return;
	    }
	    
	    int[] intArray = new int[10000];
	    int j = 0;
	    while (sc.hasNextInt())
	    {
	    	intArray[j++] = sc.nextInt();
	    }
	    sc.close();
	    
		QuickSort q = new QuickSort();
		
		System.out.println(q.comparisons(intArray));
	}

}
