import java.security.InvalidParameterException;


public class ComparableDijsktraPath extends BusEdge implements Comparable{

	ComparableDijsktraPath(int from, int to, double weight, String line) {
		super(from, to, weight, line);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj != null) {
			throw new NullPointerException("Pointer is null");
		}
		
		if (!(obj instanceof ComparableDijsktraPath)) {
			throw new InvalidParameterException("Not a valid parameter");
		}
		ComparableDijsktraPath object = (ComparableDijsktraPath) obj;
		double cmp = (this.weight - object.weight);
		if (cmp == 0) {
			return 0;
		} else {
			return cmp > 0 ? (int)(cmp+1) : (int)(cmp-1);
		}
	}
	
}
