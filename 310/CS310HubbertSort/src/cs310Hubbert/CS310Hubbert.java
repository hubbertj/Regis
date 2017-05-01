package cs310Hubbert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 
 * @author Jerum Hubbert
 *
 */
public class CS310Hubbert {
	private static final String REPORT_OUTPUT = "output/sortResults.txt";
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
	public static void printReportHeader(PrintWriter writer){
		System.out.println("SORTING RESULTS");
		writer.println("SORTING RESULTS");
		
		System.out.println("---------------");
		writer.println("---------------");
		
		System.out.println("\t\tRun 1\tRun 2\tRun 3\tAverage");
		writer.println("\t\tRun 1\tRun 2\tRun 3\tAverage");
	}
	
	/**
	 * 
	 */
	public static void printReportLine(PrintWriter writer, String sortName, double runOneTimems, double runTwoTimems, double runThreeTimems){
		Double sum = runOneTimems + runTwoTimems + runThreeTimems;
		Double avg = (sum / 3);
		
		System.out.print(sortName + "\t");
		System.out.print(runOneTimems + "ms\t");
		System.out.print(runTwoTimems + "ms\t");
		System.out.print(runThreeTimems + "ms\t");
		System.out.print(avg + "ms\t\n");
		
		writer.print(sortName + "\t");
		writer.print(runOneTimems + "ms\t");
		writer.print(runTwoTimems + "ms\t");
		writer.print(runThreeTimems + "ms\t");
		writer.print(avg + "ms\t\n");
		
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
		
		
		try {
			PrintWriter writer = new PrintWriter(REPORT_OUTPUT, "UTF-8");
			printReportHeader(writer);
			printReportLine(writer, "Bubble Sort", 23, 23, 23);
			printReportLine(writer, "Bubble Sort", 23, 23, 23);
			printReportLine(writer, "Bubble Sort", 23, 23, 23);	
			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + REPORT_OUTPUT);
			e.printStackTrace();
		}
	}

}
