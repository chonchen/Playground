import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContractGraph {
	
	private Map<Integer, List<Integer>> edgeMap = new HashMap<>();
	private int[] fromVertices;
	
	public ContractGraph(int V){ fromVertices = new int[V]; }
	
	public void addVertex(int vertex){
		edgeMap.put(vertex, new LinkedList<>());
		fromVertices[edgeMap.size() - 1] = vertex;
	}
	
	public void addEdge(int from, int to){
		if (edgeMap.containsKey(from) && from != to) edgeMap.get(from).add(to);	
	}
	
	public int numVertices(){ return edgeMap.size(); }
	
	public int numEdges(){
		int numOfEdges = 0;
		for (int i = 0; i < fromVertices.length; i++)
			numOfEdges += edgeMap.get(fromVertices[i]).size();
		
		return numOfEdges / 2;
	}
	
	public int[] getRandomVertices(){
		
		int fromVertex = fromVertices[(int)(fromVertices.length * Math.random())];
		
		List<Integer> toVertices = edgeMap.get(fromVertex);
		int toVertex = toVertices.get((int)((toVertices.size()) * Math.random()));
		
		int[] vertices = new int[2];
		vertices[0] = fromVertex;
		vertices[1] = toVertex;
		
		return vertices;
	}
	
	public Map<Integer, List<Integer>> exportGraph(){
		
		Map<Integer, List<Integer>> exportMap = new HashMap<>();
		for (Integer v: edgeMap.keySet())
		{
			exportMap.put(v, new LinkedList<Integer>());
		}
		
		for (Integer v: edgeMap.keySet())
		{
			List<Integer> edges = edgeMap.get(v);
			List<Integer> edgesCpy = exportMap.get(v);
			
			for (Integer t: edges) edgesCpy.add(t);
		}
		
		return exportMap;
	}
	
}
