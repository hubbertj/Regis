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
	 * @param table
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
//		System.out.println(Arrays.toString(myList));
	}

	/**
	 * 
	 * @param myList
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
//		System.out.println(Arrays.toString(myList));
	}

	/**
	 * 
	 * @param myList
	 */
	/* Merge Sort function */
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
//			if (k == myList.length - 1) {
//				System.out.println(Arrays.toString(myList));
//			}
		}
	}

	/**
	 * 
	 */
	public static void printReportHeader(PrintWriter writer) {
		System.out.println("SORTING RESULTS");
		writer.println("SORTING RESULTS");

		System.out.println("---------------");
		writer.println("---------------");

		System.out.println("\t\tRun 1\tRun 2\tRun 3\tAverage");
		writer.println("\t\t\t\tRun 1\tRun 2\tRun 3\tAverage");
	}

	/**
	 * 
	 */
	public static void printReportLine(PrintWriter writer, String sortName, long runOneTimems, long runTwoTimems,
			long runThreeTimems, long avg) {
		System.out.print(sortName + "\t");
		System.out.print(runOneTimems + "ns\t");
		System.out.print(runTwoTimems + "ns\t");
		System.out.print(runThreeTimems + "ns\t");
		System.out.print(avg + "ns\t\n");

		writer.print(sortName + "\t\t");
		writer.print(runOneTimems + "ns\t");
		writer.print(runTwoTimems + "ns\t");
		writer.print(runThreeTimems + "ns\t");
		writer.print(avg + "ns\t\n");

	}

	/**
	 * 
	 * @param myList
	 * @return
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

	public static long getAverage(Long[] times) {
		long sum = 0;
		for (long time : times) {
			sum += time;
		}
		return (sum / (times.length + 1));
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int LIST_RANGE = 100000;
		int numElements = 50000;

		long bubbleSortNanoSeconds = System.nanoTime();
		long insertionSortNanoSeconds = System.nanoTime();
		long mergeSortNanoSeconds = System.nanoTime();

		Long[] bubbleTimes, insertionTimes, mergeTimes;
		//TODO: Create a 3x3 element 2D results arrays that will hold the time it takes to perform each sort 
		bubbleTimes = new Long[3];
		insertionTimes = new Long[3];
		mergeTimes = new Long[3];

		Integer[] list1;
		Integer[] list2;
		Integer[] list3;
		Random random = new Random();

		// Create the lists:
		list1 = new Integer[numElements];
		list2 = new Integer[numElements];
		list3 = new Integer[numElements];

		// Integer[] tempList = { 10, 20, 100, 1, 99, 76, 89 };
		// Integer[] tempList1 = { 10, 20, 100, 50, 40, 11, 1, 99, 76, 89 };
		// Integer[] tempList2 = { 10, 20, 100, 50, 40, 11, 1, 99, 76, 89, 99,
		// 75, 67 };

		for (int i = 0; i < bubbleTimes.length; i++) {

			// Initialize the values in each list:
			for (int j = 0; j < numElements; j++) {
				list1[j] = list2[j] = list3[j] = random.nextInt(LIST_RANGE);
			}

			System.out.println("Starting sort #1...");
			bubbleSortNanoSeconds = System.nanoTime();
			bubbleSort(list1);
			bubbleSortNanoSeconds = System.nanoTime() - bubbleSortNanoSeconds;
			System.out.println("Bubble Sort time " + (bubbleSortNanoSeconds) + "ns");
			bubbleTimes[i] = bubbleSortNanoSeconds;
			if (!validatedSort(list1)) {
				System.err.println(" Sort error in tempList");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();

			System.out.println("Starting sort #2...");
			insertionSortNanoSeconds = System.nanoTime();
			insertionSort(list2);
			insertionSortNanoSeconds = System.nanoTime() - insertionSortNanoSeconds;
			System.out.println("Insertion Sort time " + (insertionSortNanoSeconds) + "ns");
			insertionTimes[i] = insertionSortNanoSeconds;
			if (!validatedSort(list2)) {
				System.err.println(" Sort error in tempList2");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();

			System.out.println("Starting sort #3...");
			mergeSortNanoSeconds = System.nanoTime();
			mergeSort(list3, 0, list3.length);
			mergeSortNanoSeconds = System.nanoTime() - mergeSortNanoSeconds;
			System.out.println("Merge Sort time " + (mergeSortNanoSeconds) + "ns");
			mergeTimes[i] = mergeSortNanoSeconds;
			if (!validatedSort(list3)) {
				System.err.println(" Sort error in tempList1");
			} else {
				System.out.println(" Sorts validated");
			}

			System.out.println();
		}

		try {
			PrintWriter writer = new PrintWriter(REPORT_OUTPUT, "UTF-8");
			printReportHeader(writer);
			printReportLine(writer, "Bubble Sort", bubbleTimes[0], bubbleTimes[1], bubbleTimes[2],
					getAverage(bubbleTimes));
			printReportLine(writer, "Insertion Sort", insertionTimes[0], insertionTimes[1], insertionTimes[2],
					getAverage(insertionTimes));
			printReportLine(writer, "Merge Sort", mergeTimes[0], mergeTimes[1], mergeTimes[2], getAverage(mergeTimes));
			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + REPORT_OUTPUT);
			e.printStackTrace();
		}
	}

}
