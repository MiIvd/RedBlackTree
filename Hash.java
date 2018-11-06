package data_structures;

import java.util.Iterator;

/**
 * The Hash data structure has O(1) time complexity (best case) for add, remove, and find
 * for an object in the data structure. The methods in the Hash data structure are defined
 * by the HashI interface. The Hash consists of an array of Linked Lists,
 * the Linked Lists are defined by the HashListI interface.
 * 
 * @author
 *
 * @param <K> The key for entries in the hash
 * @param <V> The value for entries in the hash
 */

public class Hash<K, V> implements HashI<K, V> {
	class HashElement<K,V> implements Comparable <HashElement <K,V>> {
		K key;
		V value;
	public HashElement (K key, V value) {
		this.key = key;
		this.value = value;
	}
	public int compareTo(HashElement<K,V> o) {
		return (((Comparable<K>)this.key).compareTo(o.key));
	}
	}
		int tableSize;
		int numElements;
		LinkedList<HashElement<K,V>>[] harray;
		double maxLoadFactor;
		
		public Hash (int tableSize) {
			this.tableSize = tableSize;
			harray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];
			for(int i = 0; i<tableSize; i++)
			harray[i] = new LinkedList<HashElement<K,V>>();
				maxLoadFactor = 0.75;
				numElements = 0;
		}

		 /**  
		 * Adds the given key/value pair to the dictionary.  Returns 
		 * false if the dictionary is full, or if the key is a duplicate. 
		 * Returns true if addition succeeded. 
		 *  
		 * @param key the key to add
		 * @param value the value associated with the key
		 * @return true if the key/value are added to the hash.
		 */
	
		public boolean add(K key, V value) {
			if (loadFactor() > maxLoadFactor)
				resize (tableSize * 2);
			
			HashElement<K,V> he = new HashElement (key, value);
				int hashval = key.hashCode();
				hashval = hashval & 0x7FFFFFFF;
				hashval = hashval % tableSize;
				harray[hashval].add(he);
				numElements++;
				return true;
			}

		/**
		 * Deletes the key/value pair identified by the key parameter. 
		 * Returns true if the key/value pair was found and removed, 
		 * otherwise returns false.
		 *  
		 * @param key
		 * @return
		 */
		
		public boolean remove(K key) {
			int hashval = (key.hashCode() & 0x7FFFFFFF) % tableSize;
			if (isEmpty() == true) {
				return false;
			}
			for (HashElement<K,V> o : harray[getIndex(key)]) {
				if (((Comparable<HashElement<K,V>>)o).compareTo(new HashElement<K,V>(o.key,o.value)) == 0) {
					if (harray[hashval].remove(o) != null) {
						numElements--;
						return true;
					}
				}
			}
			return false;
		}
		
		private int getIndex(K key) {
			int hashIndex = key.hashCode() % tableSize;
			if (hashIndex < 0)
				hashIndex = hashIndex + tableSize;
			return hashIndex;
		}
		
		/**
		 * Change the value associated with an existing key.
		 * @param key The key to change
		 * @param value
		 * @return
		 */
		public boolean changeValue(K key, V value) {
			int hashval = (key.hashCode() & 0x7FFFFFFF) % tableSize;
				for (HashElement<K,V> he : harray[hashval]) {
					if (((Comparable <K>)he.key).compareTo(key) == 0) {
						he.value = value;
						return true;
					}
				}
				return false;
		}
		
		
		/**
		 * Test whether the hash has the entry associated with the key
		 * @param key the key to look for
		 * @return whether it is there.
		 */
		public boolean contains(K key) {
			int hashval = (key.hashCode() & 0x7FFFFFFF) % tableSize;
			if (isEmpty())
				return false;
			for (HashElement<K,V> he : harray[hashval])
				if (((Comparable<K>)he.key).compareTo(key) == 0) {
					return true;
		}
		return false;
		} 
		
		/**
		 * Returns the value associated with the parameter key. 
		 * Returns null if the key is not found or the dictionary is empty. 
		 * @param key the key to find the value for
		 * @return the value
		 */
		public V getValue(K key) {
			int hashval = (key.hashCode() & 0x7FFFFFFF) % tableSize;
				for (HashElement<K,V> he : harray[hashval])
					if (((Comparable<K>)he.key).compareTo(key) == 0)
						return he.value;
						return null;
		}
		/**
		 * Returns the number of key/value pairs currently stored in the dictionary 
		 * @return
		 */
		public int size() {
			return tableSize;
		}

		/**
		 * Returns true if the dictionary is empty 
		 * @return whether the dictionary is empty
		 */
		public boolean isEmpty() {
			return numElements == 0;
		}
		
		/**
		 * Make the dictionary empty
		 */
		
		public void makeEmpty(){
			tableSize = 0;
		}

		/**
		 * Returns the current load factor of the dictionary (lambda)
		 * @return the loadFactor
		 */
		
		public double loadFactor() {
			return numElements / tableSize;
		}
		
		/**
		 * Get the maximum load factor (at which point we need to resize)
		 * @return the maximum load factor of the hash
		 */
		
		public double getMaxLoadFactor() {
			return maxLoadFactor;
		}
			
		/**
		 * Set the maximum load factor (at which point we need to resize)
		 * @param loadfactor the maximum load factor
		 * @return 
		 */
		
		public void setMaxLoadFActor(double loadfactor) {
			maxLoadFactor = loadfactor;
		}
		
		/**
		 * Resizes the dictionary
		 * @param newSize the size of the new dictionary
		 */
		
		public void resize(int newSize) {
			LinkedList<HashElement<K,V>>[] newArray = (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];
			for (int i=0; i<newSize; i++) {
				newArray[i] = new LinkedList<HashElement<K,V>>();
			}
			for (K key : this) {
				V val = getValue(key);
				HashElement<K,V> he = new HashElement<>(key,val);
				int hashval = (key.hashCode() & 0x7FFFFFFF) % newSize;
				newArray[hashval].add(he);
			}
			harray = newArray;
			tableSize = newSize;
		}
		
		/**
		 * Returns an Iterator of the keys in the dictionary, in ascending 
		 * sorted order 
		 */
		
		public Iterator<K> iterator() {
			return new IteratorHelper<K>();
		}
		class IteratorHelper<T> implements Iterator <T> {
			T[] Keys;
			int position;
			public IteratorHelper() {
				Keys = (T[]) new Object [size()];
				int p = 0;
				for (int i = 0; i < tableSize; i++) {
					LinkedList<HashElement<K,V>> list = harray[i];
					for (HashElement<K,V> h:list)
						Keys[p++] = (T) h.key;
				}
				position = 0;
			}
			public boolean hasNext() {
				return position < Keys.length;
			}
			public T next() {
				if (!hasNext())
				return null;
				return Keys[position++];
			}

	}
}
