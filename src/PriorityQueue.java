import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>>{
	
	private List<E> heap = new ArrayList<>();
	
	public PriorityQueue(){
		heap.add(null);
	}
	
	public void insert(E item){
		heap.add(item);
		bubbleUp();
	}
	
	public E extractMin(){
		if (heap.size() == 1) return null;
		swap(heap, 1, heap.size() - 1);
		E min = heap.remove(heap.size() - 1);
		bubbleDown();
		return min;
	}
	
	public boolean isEmpty(){
		return heap.size() < 2;
	}
	
	private void bubbleUp(){
		
		int child = heap.size() - 1;
		
		while (child > 1){
			int parent = findParent(child);
			if (heap.get(child).compareTo(heap.get(parent)) < 0){
				swap(heap, child, parent);
				child = parent;
			}
			else break;
		}
	}
	
	private void bubbleDown(){
		
		int parent = 1;
		
		while (2 * parent < heap.size()){
			int child = findSmallerChild(parent);
			if (heap.get(parent).compareTo(heap.get(child)) > 0){
				swap(heap, parent, child);
				parent = child;
			}
			else break;
		}		
	}
	
	private int findParent(int index){
		if (index < 1 || index > heap.size() - 1)
			throw new IllegalArgumentException();
		
		return (int) Math.floor(index / 2);
	}
	
	private int findSmallerChild(int index){
		if (index < 1 || index > heap.size() - 1)
			throw new IllegalArgumentException();
		
		int child1 = index * 2;
		int child2 = index * 2 + 1;
		
		if (child1 > heap.size() - 1){
			throw new IllegalArgumentException();
		}
		else if (child2 > heap.size() - 1){
			return child1;
		}
		else{
			if (heap.get(child1).compareTo(heap.get(child2)) < 0) return child1;
			else return child2;
		}		
	}
	
	private void swap(List<E> heap, int index1, int index2){
		E temp = heap.get(index1);
		heap.set(index1, heap.get(index2));
		heap.set(index2, temp);
	}
	
	public static void main(String[] args){
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.insert(9);
		pq.insert(8);
		pq.insert(100);
		pq.insert(3);
		
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		
		pq.insert(84);
		pq.insert(93);
		pq.insert(78);
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		System.out.println(pq.extractMin());
		
		
	}
}
