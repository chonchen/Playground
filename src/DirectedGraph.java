import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class DirectedGraph {
	
	private Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
	
	public void addVertex(int vertex){
		if (!edgeMap.containsKey(vertex)) edgeMap.put(vertex, new HashSet<Integer>());
	}
	
	public void addEdge(int from, int to){
		addVertex(from);
		addVertex(to);
		edgeMap.get(from).add(to);
	}
	
	public DirectedGraph reverse(){
		
		DirectedGraph reverseGraph = new DirectedGraph();
		
		for (Integer from: edgeMap.keySet())
		{
			Set<Integer> edges = edgeMap.get(from);
			for (Integer to: edges) reverseGraph.addEdge(to, from);
		}
		
		return reverseGraph;
	}
	
	public Map<Integer, Set<Integer>> exportGraph(){
		
		Map<Integer, Set<Integer>> edgeMapCpy = new HashMap<>();
		
		for (Integer v: edgeMap.keySet())
		{
			edgeMapCpy.put(v, new HashSet<Integer>());
		}
		
		for (Integer v: edgeMap.keySet())
		{
			Set<Integer> edges = edgeMap.get(v);
			Set<Integer> edgesCpy = edgeMapCpy.get(v);
			
			for (Integer t: edges) edgesCpy.add(t);
		}
		
		return edgeMapCpy;
	}
	
	public List<List<Integer>> getSCCs(){
		
		List<List<Integer>> sCCs = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> toProcess = new Stack<>();
		
		List<Integer> reversePostOrder = reverse().reversePostOrder();
		for (Integer v: reversePostOrder){
			List<Integer> sCCElements = new LinkedList<>();
			toProcess.push(v);
			
			while(!toProcess.isEmpty()){
				Integer from = toProcess.pop();
				if (!visited.contains(from)){
					visited.add(from);
					sCCElements.add(from);
					Set<Integer> edges = edgeMap.get(from);
					for (Integer e: edges) toProcess.push(e);
				}
			}
			
			sCCs.add(sCCElements);
			
		}
		
		return sCCs;
	}
	
	private List<Integer> reversePostOrder(){
		
		Stack<Integer> toProcess = new Stack<>();
		List<Integer> reversePostOrder = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		Set<Integer> finished = new HashSet<>();
		
		for (Integer v: edgeMap.keySet())
		{
			toProcess.push(v);
			
			while (!toProcess.isEmpty())
			{
				Integer from = toProcess.peek();
				
				if (visited.contains(from)){
					toProcess.pop();
					if (!finished.contains(from))
					{
						reversePostOrder.add(0,from);
						finished.add(from);
					}
				}
				else
				{
					visited.add(from);
					Set<Integer> edges = edgeMap.get(from);
					for (Integer e: edges) toProcess.push(e);
				}
			}
		}
		
		return reversePostOrder;
	}
	

	
	public static void main(String[] args){
		
		String fileName = "/Users/chonchen/Documents/SCC.txt";
		Scanner sc = null;
	    try {
	    	sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	    }
		
	    DirectedGraph test = new DirectedGraph();
	    
		while (sc.hasNextLine())
		{
			String s = sc.nextLine();
			String[] parse = s.split(" ");
			
			test.addEdge(Integer.valueOf(parse[0]), Integer.valueOf(parse[1]));
		}
		
		
		/**
		test.addEdge(0, 1);
		test.addEdge(0, 2);
		test.addEdge(0, 5);
		test.addEdge(1, 3);
		test.addEdge(2, 1);
		test.addEdge(2, 4);
		test.addEdge(4, 3);
		test.addEdge(5, 1);
		
		**/
		
		/**
		Iterable<Integer> RPO = test.reversePostOrder();
		
		for (Integer i: RPO) System.out.println(i);
		**/
		
		List<List<Integer>> sCCs = test.getSCCs();
		
		int[] solution = new int[sCCs.size()];
		
		int count = 0;
		for (List<Integer> elements: sCCs)
		{
			solution[count] = elements.size();
			count++;
		}
		
		Arrays.sort(solution);
		
		for (int i = solution.length - 1; i > solution.length - 6; i--)
		{
			System.out.println(solution[i]);
		}
		
		
	}
}