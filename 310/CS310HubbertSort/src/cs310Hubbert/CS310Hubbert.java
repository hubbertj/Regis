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
	 * Sorts a list of Integers using bubble sort
	 * 
	 * @param myList The list of Integer which are unordered.
	 */
	public static void bubbleSort(Integer[] myList) {
		int pass = 1;
		boolean exchange = false;
		do {
			exchange = false;
			for (int i = 0; i < myList.length - pass; i++) {
				if (myList[i] > myList[i + 1]) {
					int temp = myList[i];
					myList[i] = myList[i + 1];
					myList[i + 1] = temp;
					exchange = true;
				}
			}
		} while (exchange);
	}

	/**Sorts a list of Integers using insertion sort
	 * 
	 * @param myList The list of Integer which are unordered.
	 */
	public static void insertionSort(Integer[] myList) {
		for (int next = 1; next < myList.length; next++) {
			int nextval = myList[next];
			while (next > 0 && nextval < myList[next - 1]) {
				myList[next] = myList[next - 1];
				next--;
			}
			myList[next] = nextval;
		}
	}

	/**Sorts a list of Integers using merge sort
	 * 
	 * @param myList The list of Integer which are unordered.
	 * @param low The low number from the sub list.
	 * @param high The high number from the sub list.
	 */
	public static void mergeSort(Integer[] myList, int low, int high) {
		int N = high - low;
		if (N <= 1) {
			return;
		}

		int mid = low + N / 2;

		mergeSort(myList, low, mid);
		mergeSort(myList, mid, high);

		Integer[] temp = new Integer[N];

		int i = low;
		int j = mid;

		for (int k = 0; k < N; k++) {
			if (i == mid)
				temp[k] = myList[j++];
			else if (j == high)
				temp[k] = myList[i++];
			else if (myList[j] < myList[i])
				temp[k] = myList[j++];
			else
				temp[k] = myList[i++];
		}

		for (int k = 0; k < N; k++) {
			myList[low + k] = temp[k];
		}
	}
	
	/**
	 *  Prints out the body of the report
	 * @param results A 3d array holding all the report data.
	 */
	public static void printReport(Long[][] results){
		PrintWriter writer;
		try {
			writer = new PrintWriter(REPORT_OUTPUT, "UTF-8");
			printReportHeader(writer);
			printReportLine(writer, "Bubble Sort", results[0][0], results[1][0], results[2][0],
					getAverage(results[0][0], results[1][0], results[2][0]));
			printReportLine(writer, "Insertion Sort", results[0][1], results[1][1], results[2][1],
					getAverage(results[0][1], results[1][1], results[2][1]));
			printReportLine(writer, "Merge Sort", results[0][2], results[1][2], results[2][2],
					getAverage(results[0][2], results[1][2], results[2][2]));
			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + REPORT_OUTPUT);
			e.printStackTrace();
		}
	}

	/**
	 * Prints out the header of the report
	 * 
	 * @param writer The Writer object used for writing to a file.
	 */
	public static void printReportHeader(PrintWriter writer) {
		System.out.println("SORTING RESULTS");
		writer.println("SORTING RESULTS");

		System.out.println("---------------");
		writer.println("---------------");
		
		String format = "%-20s%-20s%-20s%-20s%-20s\n";
		System.out.format(format,"", "Run 1", "Run 2", "Run 3", "Average");
		writer.format(format,"", "Run 1", "Run 2", "Run 3", "Average");
	}

	/** Prints out a report
	 * 
	 * @param writer The Writer object used for writing to a file.
	 * @param sortName Name of the line of the report
	 * @param runOneTimems First result run time.
	 * @param runTwoTimems Second result run time.
	 * @param runThreeTimems Third result run time.
	 * @param avg The average of runtime for all results.
	 */
	public static void printReportLine(PrintWriter writer, String sortName, long runOneTimems, long runTwoTimems,
			long runThreeTimems, long avg) {
		String format = "%-20s";
		String format2 = "%-20s\n";
		System.out.format(format, sortName);
		System.out.format(format, runOneTimems + "ns");
		System.out.format(format, runTwoTimems + "ns");
		System.out.format(format, runThreeTimems + "ns");
		System.out.format(format2, avg + "ns");
		
		writer.format(format, sortName);
		writer.format(format, runOneTimems + "ns");
		writer.format(format, runTwoTimems + "ns");
		writer.format(format, runThreeTimems + "ns");
		writer.format(format2, avg + "ns");
	}

	/**Checks if a list if ordered.
	 * 
	 * @param myList The list of Integer which are unordered.
	 * @return Boolean True if everything if the list passed is sorted.
	 */
	public static Boolean validatedSort(Integer[] myList) {
		if (myList == null) {
			return false;
		}
		// a list of 1 is sorted!
		else if (myList.length == 0 || myList.length == 0) {
			return true;
		}
		for (int i = 0; i < myList.length - 1; i++) {
			if (myList[i] > myList[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Takes in three time results and returns the average time.
	 * 
	 * @param result1 Time result 1
	 * @param result2 Time result 2
	 * @param result3 Time result 3
	 * @return Long The average amount of time.
	 */
	public static long getAverage(Long result1, Long result2, Long result3) {
		return ((result1 + result2 + result3) / 3);
	}

	/**
	 * Main method runs three tests on different sort algorithms.
	 * 
	 * @param args arguments which can be passed in.
	 */
	public static void main(String[] args) {
		final int LIST_RANGE = 100000;
		int numElements = 50000;
		Random random = new Random();
		int sortCount = 1;
		
		Integer[] list1 = new Integer[numElements];
		Integer[] list2 = new Integer[numElements];
		Integer[] list3 = new Integer[numElements];

		long bubbleSortNanoSeconds = System.nanoTime();
		long insertionSortNanoSeconds = System.nanoTime();
		long mergeSortNanoSeconds = System.nanoTime();

		Long[][] results = new Long[3][];
		results[0] = new Long[3];
		results[1] = new Long[3];
		results[2] = new Long[3];
		
		for (int i = 0; i < results.length; i++) {

			// Initialize the values in each list:
			for (int j = 0; j < numElements; j++) {
				list1[j] = list2[j] = list3[j] = random.nextInt(LIST_RANGE);
			}

			System.out.println("Starting sort #"+ (sortCount++) +"...");
			bubbleSortNanoSeconds = System.nanoTime();
			bubbleSort(list1);
			bubbleSortNanoSeconds = System.nanoTime() - bubbleSortNanoSeconds;
			System.out.println("Bubble Sort time " + (bubbleSortNanoSeconds) + "ns");
			results[i][0] = bubbleSortNanoSeconds;
			if (!validatedSort(list1)) {
				System.err.println(" Sort error in tempList");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();

			System.out.println("Starting sort #"+ (sortCount++) +"...");
			insertionSortNanoSeconds = System.nanoTime();
			insertionSort(list2);
			insertionSortNanoSeconds = System.nanoTime() - insertionSortNanoSeconds;
			System.out.println("Insertion Sort time " + (insertionSortNanoSeconds) + "ns");
			results[i][1] = insertionSortNanoSeconds;
			if (!validatedSort(list2)) {
				System.err.println(" Sort error in tempList2");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();

			System.out.println("Starting sort #"+ (sortCount++) +"...");
			mergeSortNanoSeconds = System.nanoTime();
			mergeSort(list3, 0, list3.length);
			mergeSortNanoSeconds = System.nanoTime() - mergeSortNanoSeconds;
			System.out.println("Merge Sort time " + (mergeSortNanoSeconds) + "ns");
			results[i][2] = mergeSortNanoSeconds;
			if (!validatedSort(list3)) {
				System.err.println(" Sort error in tempList1");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();
		}
		
		printReport(results);
	}

}
