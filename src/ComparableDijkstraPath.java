import java.security.InvalidParameterException;


public class ComparableDijkstraPath extends BusEdge implements Comparable{

	ComparableDijkstraPath(int from, int to, double weight, String line) {
		super(from, to, weight, line);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj != null) {
			throw new NullPointerException("Pointer is null");
		}
		
		if (!(obj instanceof ComparableDijkstraPath)) {
			throw new InvalidParameterException("Not a valid parameter");
		}
		ComparableDijkstraPath object = (ComparableDijkstraPath) obj;
		double cmp = (this.weight - object.weight);
		if (cmp == 0) {
			return 0;
		} else {
			return cmp > 0 ? (int)(cmp+1) : (int)(cmp-1);
		}
	}
	
}
