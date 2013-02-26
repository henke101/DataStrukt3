
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private int size;
	protected List<E>[] neighbours;
	private int nodeAmount;
	
	protected class ComparableDijkstraPath<T extends ComparableDijkstraPath> implements Comparable<T> {
		private int node;
		protected double cost;
		private List<E> path;
		private boolean visited;
		
		public ComparableDijkstraPath(int node, double cost, List<E> path) {
			this.node = node;
			this.cost = cost;
			this.path = path;
			this.visited = false;
		}
		
		@Override
		public int compareTo(T obj) throws NullPointerException {
			if (obj == null) {
				throw new NullPointerException();
			}
			
			// Returns 0 if equal, 1 if this is greater than the object, -1 if it's smaller
			return (this.cost == obj.cost) ? 0 : ((this.cost > obj.cost) ? 1 : -1);
		}

		/**
		 * @return True if visited, false if not
		 */
		public boolean isVisited() {
			return visited;
		}

		/**
		 * @param visited, Set visited to true/false
		 */
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public DirectedGraph(int noOfNodes) {
		nodeAmount = noOfNodes;
		neighbours = new List[noOfNodes];
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
		
//		Integer[] distance = new Integer[nodeAmount];
		
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
		pq.add(new ComparableDijkstraPath(from, 0, null));
		ComparableDijkstraPath cdp;
		while (!pq.isEmpty()) {
			cdp = pq.peek();
//			if (node not visited) {
				if (cdp.node == to) {
					return cdp.path.iterator();
				}

				else {
//					set node to visited;
					for (E e : neighbours[cdp.node]) {
//						if (v not visited) {
							cdp.path.add(e);
							pq.add(new ComparableDijkstraPath(e.to, e.getWeight(), cdp.path));
//						}
					}
				}
//			}
		}
		
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
