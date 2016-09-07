import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {
	private class Node implements Comparable<Node>{
		private int toVertex;
		private double distFromSource;
		
		public Node(int toVertex, double distFromSource){
			this.toVertex = toVertex;
			this.distFromSource = distFromSource;
		}

		@Override
		public int compareTo(Node o) {
			if (this.distFromSource > o.distFromSource) return 1;
			else if (this.distFromSource < o.distFromSource) return -1;
			else return 0;
		}
	}
	
	public double shortestPathDistance(WeightedDiGraph graph, int source, int destination){
		if(!graph.containsVertex(source) || !graph.containsVertex(destination))
			throw new IllegalArgumentException("Graph should contain source and destination vertices");
		
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> edgeTo = new HashMap<>();
		Map<Integer, Double> distTo = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (Integer v: graph.getVertices()){
			edgeTo.put(v, null);
			distTo.put(v, Double.POSITIVE_INFINITY);
		}
		
		pq.insert(new Node(source, 0.0));
		
		while (!pq.isEmpty()){
			Node node = pq.extractMin();
			int v = node.toVertex;
			
			if (visited.contains(v)) continue;
			
			visited.add(v);
			
			for (WeightedEdge edge: graph.getEdgesFrom(v))
			{
				int to = edge.to();
				double distance = node.distFromSource + edge.weight();
				if (distance < distTo.get(to)){
					distTo.put(to, distance);
					edgeTo.put(to, v);
					pq.insert(new Node(to, distance));
				}
			}
		}
		
		if (edgeTo.get(destination) == null) return 1000000;
		
		return distTo.get(destination);
		
	}
	
	public static void main(String[] args){
		
		String fileName = "/Users/chonchen/Downloads/dijkstraData.txt";
		Scanner sc = null;
	    try {
	    	sc = new Scanner(new File(fileName));
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	    }
	    
	    List<String> inputAsList = new LinkedList<>();
	 
	    while (sc.hasNextLine())
	    {
	    	inputAsList.add(sc.nextLine());
	    }
	    sc.close();
	    
	    WeightedDiGraph graph = new WeightedDiGraph();
	    
	    for (String input: inputAsList){
	    	String[] parse = input.split("\t");
	    	int from = Integer.valueOf(parse[0]);
	    	
	    	for (int i = 1; i < parse.length; i++){
	    		String[] edgeData = parse[i].split(",");
	    		int to = Integer.valueOf(edgeData[0]);
	    		double weight = Double.valueOf(edgeData[1]);
	    		graph.addEdge(new WeightedEdge(from, to, weight));
	    	}
	    }
	    
	    Dijkstra d = new Dijkstra();
	    
	    int source = 1;
	    int[] destination = {7,37,59,82,99,115,133,165,188,197};
	    
	    double[] distances = new double[destination.length];
	    
	    for (int i = 0; i < destination.length; i++)
	    {
	    	distances[i] = d.shortestPathDistance(graph, source, destination[i]);
	    }
	    
	    for (double i: distances)
	    {
	    	System.out.print((int)Math.floor(i) + ",");
	    }
		
	}
}
