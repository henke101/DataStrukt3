
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private int size;
	protected List<E>[] neighbours;
	private int nodeAmount;
	
	protected class ComparableDijkstraPath implements Comparable {
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
		if (e.from >= neighbours.length){
			throw new ArrayIndexOutOfBoundsException();
		}
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
		
		
		return null;
	}

}
  
