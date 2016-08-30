import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class Contractor {
	public static ContractGraph contract(ContractGraph cGraph){
		
		ContractGraph graph = cGraph;
		
		while (graph.numVertices() > 2){
			Map<Integer, List<Integer>> edgeMap = graph.exportGraph();
			int[] randomVertices = graph.getRandomVertices();
			int from = randomVertices[0];
			int to = randomVertices[1];
			
			List<Integer> fromList = edgeMap.get(from);
			List<Integer> toList = edgeMap.remove(to);
			fromList.addAll(toList);
			
			ContractGraph newGraph = new ContractGraph(graph.numVertices() - 1);
			
			for (Integer v: edgeMap.keySet())
			{
				newGraph.addVertex(v);
				
				List<Integer> edgeTo = edgeMap.get(v);
				for (Integer t: edgeTo)
				{
					if (t == to) newGraph.addEdge(v, from);
					else newGraph.addEdge(v, t);
				}
			}
			
			graph = newGraph;
		}
		
		return graph;
	}
	
	public static ContractGraph scanToGraph(String fileName){
		Scanner sc;
	    try {
	    	sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	         return null;
	    }
	    
	    List<String> inputAsList = new LinkedList<>();
	    int numOfLines = 0;
	    
	    while (sc.hasNextLine())
	    {
	    	numOfLines++;
	    	inputAsList.add(sc.nextLine());
	    }
	    sc.close();
	    
	    ContractGraph graph = new ContractGraph(numOfLines);
	    
	    for (String input: inputAsList){
	    	String[] parse = input.split("\t");
	    	int from = Integer.valueOf(parse[0]);
	    	graph.addVertex(from);
	    	
	    	for (int i = 1; i < parse.length; i++)
	    		graph.addEdge(from, Integer.valueOf(parse[i]));
	    }
	    
	    return graph; 
	}
	
	public static void main(String args[]){
		String fileName = "/Users/chonchen/Documents/MinCutInput.txt";
		
		ContractGraph graph = Contractor.scanToGraph(fileName);

		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < 50; i++)
		{
			ContractGraph contracted = Contractor.contract(graph);
			System.out.println(contracted.numEdges());
			int minCut = contracted.numEdges();
			if (minCut < min) min = minCut;
		}
		
		System.out.println("minCut: " + min);
		
	}
	
}
