package RBTester;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;

	import data_structures.RedBlackTree;
	import data_structures.LinkedList;
	import dns_resolver.IPAddress;
	import dns_resolver.URL;

	public class RedBlackTreeTester {

		static boolean error = false;
		static int count = 0;
		static int RESET = 1;
		static int[] hardTesting= {100,700,600,350,225,160,285,130,315,300,292,289,287,288,288,288};
		
		public static void main(String[] args)	{
			String filename = "src/data/ips_small.txt";
			LinkedList<IPAddress> list = new LinkedList<>();
			BufferedReader file;
			String line;
		


				/**
				 * Populate the Tree with Integers and a String as a value for every Key.
				 */
				RedBlackTree<Integer,String> treeTest = new RedBlackTree<>();
				String testValue = "**HAKUNA MATATA**";

				
				// Simple tree adding to the Left Only
				for (int i = 10; i > 0; i--)	{
					treeTest.add(i, testValue);
				}
				
				treeTest.print();
				
				for (int i = 4; i > 0; i--)	{
					if (!treeTest.contains(i))	{
						System.err.println("All the numbers from 0 - 19 should be on the Tree but they are not.");
						error = true;
					}
					else {
			//			System.out.println(treeTest.contains());
					}
				}

				//treeTest.print();
		}
	}

