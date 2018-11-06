package dns_resolver;

/**
 * A URL Object that just knows how to compare URLs
 * @author redwards
 *
 */
public class URL implements Comparable<URL> {

		String address;
		
		public URL (String URL) {
			address = URL;
		}
		
		public String toString() {
			return address.toString();
		}
		public int hashCode() {
			return address.hashCode();
		}
		public boolean equals(URL o) {
			if (address.compareTo(o.address) == 0) {
				return true;
			}
			return false;
		}
		public int compareTo(URL obj) {
			return address.compareTo(obj.address);
		}
		
	}

