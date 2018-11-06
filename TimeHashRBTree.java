package timeDataStructures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import data_structures.Hash;
import data_structures.HashI;
import data_structures.LinkedList;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;
import exceptions.FileFormatException;

/**
 * A class to time my RedBlackTree and Hash versus the Java API RedBlackTree and Hash
 * @author Milad Balkhinezhad
 */
class TimeHashRBTree {
	private static final String SMALL_FILE = "src/data/top-250k.ip";
	private static final String LARGE_FILE = "src/data/top-1m.ip";
	private static long start, stop;

	public static void main(String[] args) {
		try {
			Hash(LARGE_FILE);
			JavaHash(LARGE_FILE);
			RBTree(LARGE_FILE);
			JavaRBTree(LARGE_FILE);
		} catch (FileFormatException e) {
			e.printStackTrace();
		}
	}
	
/**
 * Stores URLs and IPAddresses into my Hash and times how long it takes to sort from start to stop.
 * @param filename
 * @return hash
 * @throws FileFormatException
 */
	public static HashI<URL, IPAddress> Hash(String filename) throws FileFormatException {
		int size = 1000000;
		Hash<URL, IPAddress> hash = new Hash<URL, IPAddress>(size);
		String line;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start = System.currentTimeMillis();
		try {
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				IPAddress ip = new IPAddress(value[1]);
				hash.add(url, ip);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Hash Load Time is " + (stop - start));

		start = System.currentTimeMillis();
		try {
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				if (!hash.contains(url)) {
					System.out.println("Hash contains error");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Hash Search Time is " + (stop - start));
		return hash;
	}
	
/**
 * Stores URLs and IPAddresses into the Java API Hash and times how long it takes to sort from start to stop.
 * @param filename
 * @return hashTable
 * @throws FileFormatException
 */
	public static HashMap<URL, IPAddress> JavaHash(String filename) throws FileFormatException {
		int size = 1000000;
		String line;

		HashMap<URL, IPAddress> hashTable = new HashMap<URL, IPAddress>(size);
		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				IPAddress ip = new IPAddress(value[1]);
				hashTable.put(url, ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Java Hash Load Time is " + (stop - start));

		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Java Hash Search Time is " + (stop - start));
		return hashTable;
	}
	
/**
 * Stores URLs and IPAddresses into my RedBlackTree and times how long it takes to sort from start to stop.
 * @param filename
 * @return redBlackTree
 * @throws FileFormatException
 */
	public static RedBlackTree<URL, IPAddress> RBTree(String filename) throws FileFormatException {
		String line;

		RedBlackTree<URL, IPAddress> redBlackTree = new RedBlackTree<URL, IPAddress>();
		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				IPAddress ip = new IPAddress(value[1]);
				redBlackTree.add(url, ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Java Tree Load Time is " + (stop - start));

		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				if (!redBlackTree.contains(url))
					System.out.println("Red Black Tree contains an error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Java Tree Load Time is " + (stop - start));
		return redBlackTree;
	}
	
/**
 * Stores URLs and IPAddresses into the Java API RedBlackTree and times how long it takes to sort from start to stop.
 * @param filename
 * @return redBlackTree
 * @throws FileFormatException
 */
	public static TreeMap<URL, IPAddress> JavaRBTree(String filename) throws FileFormatException {
		String line;

		TreeMap<URL, IPAddress> redBlackTree = new TreeMap<URL, IPAddress>();
		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
				IPAddress ip = new IPAddress(value[1]);
				redBlackTree.put(url, ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("My Tree Load Time is " + (stop - start));

		start = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] value = line.split("\\s+");
				if (value.length != 2) {
					throw new FileFormatException("Incorrect Format");
				}
				URL url = new URL(value[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("My Tree Load Time is " + (stop - start));
		return redBlackTree;
	}
}
