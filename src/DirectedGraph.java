
import java.util.*;

/**
 * Implements a DirectedGraph
 * Can return the shortest path between two nodes
 * and the minimum spanning tree in the graph.
 * 
 * @author Mike Phoohad
 * @author Henrik Andersson
 */
public class DirectedGraph<E extends Edge> {

	private int size;
	protected List<E>[] neighbours;
	private int nodeAmount;
	private PriorityQueue<E> edges;
	
	protected class ComparableDijkstraPath implements Comparable {
		private int node;
		protected double cost;
		private List<E> path;
		
		public ComparableDijkstraPath(int node, double cost, List<E> path) {
			this.node = node;
			this.cost = cost;
			this.path = path;
		}
		
		@Override
		public int compareTo(Object obj) throws NullPointerException {
			if (obj == null) {
				throw new NullPointerException();
			}
			if (!(obj instanceof DirectedGraph.ComparableDijkstraPath)) {
				throw new IllegalArgumentException();
			}
			ComparableDijkstraPath object = (ComparableDijkstraPath) obj;
			
			// Returns 0 if equal, 1 if this is greater than the object, -1 if it's smaller
			return (this.cost == object.cost) ? 0 : ((this.cost > object.cost) ? 1 : -1);
		}		
	}
	
	protected class CompKruskalEdge<E extends Edge> implements Comparator<E> {
		
		public CompKruskalEdge() {}

		@Override
		public int compare(E arg0, E arg1) {
			if (arg0 == null || arg1 == null) {
				throw new NullPointerException();
			}
			
			return arg0.getWeight() == arg1.getWeight() ? 0 : (arg0.getWeight() > arg1.getWeight() ? 1 : -1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public DirectedGraph(int noOfNodes) {
		nodeAmount = noOfNodes;
		neighbours = new List[noOfNodes];
		edges = new PriorityQueue<E>(nodeAmount, new CompKruskalEdge<E>());
	}
	
	public void addEdge(E e) {
		List<E> insert;
		if (e.from >= neighbours.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		edges.add(e); // Here we add all the edges to a priorityQueue for use in MST
		if (neighbours[e.from] == null) {
			insert = new LinkedList<E>();
			insert.add(e);
			size++;
			neighbours[e.from] = insert;
		} else {
			insert = neighbours[e.from];
			insert.add(e);
			size++;
		}
	}

	public Iterator<E> shortestPath(int from, int to) {
		boolean[] nodeVisited = new boolean[nodeAmount];
		PriorityQueue<ComparableDijkstraPath> pq = new PriorityQueue<ComparableDijkstraPath>();
		
		pq.add(new ComparableDijkstraPath(from, 0, new LinkedList<E>()));
		ComparableDijkstraPath cdp;
		while (!pq.isEmpty()) {
			cdp = pq.poll();
			if (!nodeVisited[cdp.node]) {
				if (cdp.node == to) {
					return cdp.path.iterator();
				}
				else {
					nodeVisited[cdp.node] = true;
					for (E e : neighbours[cdp.node]) {
						if (!nodeVisited[e.to]) {
							List<E> newPath = new LinkedList<E>(cdp.path); 
							newPath.add(e);
							pq.add(new ComparableDijkstraPath(e.to, cdp.cost + e.getWeight(), newPath));
						}
					}
				}
			}
		}
		
		return null;
	}
		 
	public Iterator<E> minimumSpanningTree() {
		@SuppressWarnings("unchecked")
		List<E>[] cc = new List[nodeAmount];
		PriorityQueue<E> edgesRemaining = new PriorityQueue<E>(edges);
		E e;
		
		while (!edgesRemaining.isEmpty()) {
			e = edgesRemaining.poll();
			if (cc[e.from] == null) {
				if (cc[e.to] == null) {
					List<E> insert = new LinkedList<E>();
					insert.add(e);
					cc[e.from] = insert;
					cc[e.to] = insert; 
				} else {
					cc[e.from] = cc[e.to];
					cc[e.to].add(e);
				}
			} else if (cc[e.to] == null) {
				cc[e.to] = cc[e.from];
				cc[e.to].add(e);
			}
			
			if (cc[e.from] != cc[e.to]) {
				List<E> shortestList;
				List<E> longestList;
				if (cc[e.from].size() <= cc[e.to].size()) {
					shortestList = cc[e.from];
					longestList = cc[e.to];
				} else {
					shortestList = cc[e.to];
					longestList = cc[e.from];
				}
				 
				for (int i = 0; i < shortestList.size(); i++) {
					E elem = shortestList.get(i);
					longestList.add(elem);
					cc[elem.from] = longestList;
					cc[elem.to] = longestList; 
				}
				
				// Add the edge to the list
				// and change index of cc to point at the list
				longestList.add(e);
				cc[e.from] = longestList;
				cc[e.to] = longestList;
			}
		}
		
		return cc[0].iterator();
	}

}
  
