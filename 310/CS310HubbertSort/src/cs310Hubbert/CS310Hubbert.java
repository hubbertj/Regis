package cs310Hubbert;

import java.util.Random;

/**
 * 
 * @author Jerum Hubbert
 *
 */
public class CS310Hubbert {
	/**
	 * 
	 * @param myList
	 */
	public static void bubbleSort(Integer[] myList) {

	}

	/**
	 * 
	 * @param myList
	 */
	public static void insertionSort(Integer[] myList) {

	}

	/**
	 * 
	 * @param myList
	 */
	public static void mergeSort(Integer[] myList) {

	}
	
	/**
	 * 
	 */
	public static void printReportHeader(){
		System.out.println("SORTING RESULTS");
		System.out.println("---------------");
		System.out.println("\t\tRun 1\tRun 2\tRun 3\tAverage");
	}
	
	/**
	 * 
	 */
	public static void printReportLine(String sortName, double runOneTimems, double runTwoTimems, double runThreeTimems){
		Double sum = runOneTimems + runTwoTimems + runThreeTimems;
		Double avg = (sum / 3);
		
		System.out.print(sortName + "\t");
		System.out.print(runOneTimems + " ms\t");
		System.out.print(runTwoTimems + " ms\t");
		System.out.print(runThreeTimems + " ms\t");
		System.out.print(avg + " ms\t\n");
		
	}

	public static void main(String[] args) {
		int numElements = 50000;
		Integer[] list1;
		Integer[] list2;
		Integer[] list3;
		Random random = new Random();

		// Create the lists:
		list1 = new Integer[numElements];
		list2 = new Integer[numElements];
		list3 = new Integer[numElements];

		// Initialize the values in each list:
		for (int i = 0; i < numElements; i++) {
			list1[i] = list2[i] = list3[i] = random.nextInt(100000);
		}
		
		
		printReportHeader();
		printReportLine("Bubble Sort", 23, 23, 23);
		printReportLine("Bubble Sort", 23, 23, 23);
		printReportLine("Bubble Sort", 23, 23, 23);
	}

}
