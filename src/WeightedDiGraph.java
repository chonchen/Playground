import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedDiGraph {
	private Map<Integer, List<WeightedEdge>> edgeMap = new HashMap<>();
	
	public void addVertex(int vertex){
		if (!edgeMap.containsKey(vertex)) edgeMap.put(vertex, new LinkedList<>());
	}
	
	public void addEdge(WeightedEdge edge){
		addVertex(edge.from());
		addVertex(edge.to());
		edgeMap.get(edge.from()).add(edge);
	}
	
	public Set<Integer> getVertices(){
		return edgeMap.keySet();
	}
	
	public List<WeightedEdge> getEdgesFrom(int from){
		if (!edgeMap.containsKey(from)) return null;
		return edgeMap.get(from);
	}
	
	public boolean containsVertex(int vertex){
		return edgeMap.containsKey(vertex);
	}
}
