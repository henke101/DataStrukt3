
import java.util.*;

public class DirectedGraph<E extends Edge> {
	private int size;
	private List<E>[] neighbours;

	@SuppressWarnings("unchecked")
	public DirectedGraph(int noOfNodes) {
		neighbours = (List<E>[]) new LinkedList[noOfNodes];
	}
	
	public void addEdge(E e) {
		List<E> insert;
		if (neighbours[e.from] == null) {
			insert = new LinkedList<E>();
			insert.add(e);
			size++;
			neighbours[e.from] = insert;
		} else {
			List<E> list = neighbours[e.from];
			if (!list.contains(e)) {
				list.add(e);
				size++;
			}
		}
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
