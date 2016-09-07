
public class WeightedEdge{

	private int from;
	private int to;
	private double edgeWeight;
	
	public WeightedEdge(int from, int to, double edgeWeight){
		if (from == to) throw new IllegalArgumentException("from and to must be different");
		
		this.from = from;
		this.to = to;
		this.edgeWeight = edgeWeight;
	}
	
	public int from() { return from; }
	
	public int to() { return to; }
	
	public double weight() { return edgeWeight; }
}
