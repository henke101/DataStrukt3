
import java.util.*;

public class DirectedGraph<E extends Edge> {
	protected class ComparableDijkstraPath implements Comparable {
		private int node;
		private int cost;
		private List<E> path;
		
		public ComparableDijkstraPath(int node, int cost, List<E> path) {
			this.node = node;
			this.cost = cost;
			this.path = path;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Object obj) throws IllegalArgumentException {
			if (obj == null || !(obj instanceof DirectedGraph.ComparableDijkstraPath)) {
				throw new IllegalArgumentException();
			}
			ComparableDijkstraPath object = null;
			object = (ComparableDijkstraPath) obj;
			
			return this.cost - object.cost;
		}
		
	}
	
	private int size;
	protected List<E>[] neighbours;

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
/*lägg (startnod, 0, tom väg) i en p-kö
while kön inte är tom
(nod, cost, path) = första elementet i p-kön
if nod ej är besökt
if nod är slutpunkt returnera väg
else 
markera nod besökt
for every v on EL(nod)
if v ej är besökt
lägg in nytt köelement 
för v i p-kön
 * 
 */
		PriorityQueue<ComparableDijkstraPath> pq = new PriorityQueue<ComparableDijkstraPath>();
		
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
