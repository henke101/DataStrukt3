import java.util.ArrayList;


public class ComparableDijkstraPath implements Comparable{
	private ArrayList<BusStop> path;
	private int totalWeight;
	
	ComparableDijkstraPath(ArrayList<BusStop> stops) {
		this.path = stops;
		int count = 0;
		for (BusStop b : path) {
			count++;
		}
		
	}

	@Override
	public int compareTo(Object obj) {
		return 0;
	}
	
}
