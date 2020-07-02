package cs310Hubbert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Jerum Hubbert
 */
public class PrintImpl {

	private String fileName = null;
	private ArrayList<Realtor> realtorList;
	private Property[] propertyArr;
	private CarStackImpl carStackLuxury;
	private CarStackImpl carStack;
	private VehicleUsageImpl vehicleUsage;
	private RealtorQueueImpl realtorQueue;
	private RealtorQueueImpl realtorLuxuryQueue;
	private int propertyNum = 0;

	/**
	 * Constructor which creates our data for the list which are passed in.
	 * 
	 * @param propertyLogImpl
	 *            PropertyLogImpl A list of properties
	 * @param realtorLogImpl
	 *            RealtorLogImpl A list of realtors
	 * @param fileName
	 *            String the name of the file the report will be saved too.
	 */
	public PrintImpl(PropertyLogImpl propertyLogImpl, RealtorLogImpl<Realtor> realtorLogImpl, String fileName) {
		this.fileName = fileName;
		this.realtorList = new ArrayList<Realtor>();
		LinkedList<Property> linkListProperty = propertyLogImpl.getProreptyLinkList();
		this.propertyArr = new Property[linkListProperty.size()];
		RealtorNode<Realtor> cNode = realtorLogImpl.getHead();

		if (realtorLogImpl.getSize() > 0) {
			do {
				realtorList.add(cNode.getRealtor());
				cNode = cNode.getNext();
			} while (cNode != null && cNode.getNext() != null);
		}
		for (int i = 0; i < linkListProperty.size(); i++) {
			this.propertyArr[i] = linkListProperty.get(i);
		}
		this.propertyNum = propertyLogImpl.getNumProperties();
	}

	/**
	 * 
	 * A child constructor which is used to run the car report
	 * 
	 * @param propertyLogImpl
	 *            PropertyLogImpl A list of properties
	 * @param realtorLogImpl
	 *            RealtorLogImpl A list of realtors
	 * @param vehicleUsage
	 *            VehicleUsageImpl Hold the data of what realtor has what car
	 * @param carStackLuxury
	 *            CarStackImpl A stack which holds all the high end cars
	 * @param carStack
	 *            CarStackImpl A stack which holds all the basic cars
	 * @param realtorQueue
	 *            RealtorQueueImpl A Queue of realtors waiting for, basic cars
	 * @param realtorLuxuryQueue
	 *            RealtorQueueImpl A Queue of realtors waiting for, high cars
	 * @param fileName
	 *            String the name of the file the report will be saved too.
	 */
	public PrintImpl(PropertyLogImpl propertyLogImpl, RealtorLogImpl<Realtor> realtorLogImpl,
			VehicleUsageImpl vehicleUsage, CarStackImpl carStackLuxury, CarStackImpl carStack,
			RealtorQueueImpl realtorQueue, RealtorQueueImpl realtorLuxuryQueue, String fileName) {
		this(propertyLogImpl, realtorLogImpl, fileName);
		this.carStackLuxury = carStackLuxury;
		this.carStack = carStack;
		this.vehicleUsage = vehicleUsage;
		this.realtorQueue = realtorQueue;
		this.realtorLuxuryQueue = realtorLuxuryQueue;
	}

	/**
	 * Gets the file name as a string
	 * 
	 * @return String Name of file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the output file location and name
	 * 
	 * @param fileName
	 *            String Name of file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * This method prints the standard realtor and proerty report using the data
	 * which was used to create the object.
	 */
	public void print() {
		try {
			PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");

			for (Realtor realtor : this.realtorList) {
				int listCount = 0;
				double total = 0.0;
				System.out
						.println(realtor.getLicenseNum() + " " + realtor.getLastName() + ", " + realtor.getFirstName());
				writer.println(realtor.getLicenseNum() + " " + realtor.getLastName() + ", " + realtor.getFirstName());
				for (int i = 0; i < this.propertyNum; i++) {
					if (this.propertyArr[i].getLicenseNum().equals(realtor.getLicenseNum())) {
						listCount++;
						Property prop = this.propertyArr[i];
						total += prop.getAskingPrice();
						String sold = "";
						if (prop.getSold()) {
							sold = "SOLD";
						}
						System.out.println(prop.getMlsNum() + "\t" + prop.getStreetAddr() + "\t" + prop.getBedrooms()
								+ "/" + prop.getBathrooms() + "\t" + "$ " + prop.getAskingPrice() + "\t" + sold);
						writer.println(prop.getMlsNum() + "\t" + prop.getStreetAddr() + "\t" + prop.getBedrooms() + "/"
								+ prop.getBathrooms() + "\t" + "$ " + prop.getAskingPrice() + "\t" + sold);
					}
				}
				writer.print("\n");
				writer.println("Number of Property Listings for Realtor: " + listCount);
				writer.println("Total sales value of Property Listings for Realtor: $" + total);
				writer.print("\n");

				System.out.print("\n");
				System.out.println("Number of Property Listings for Realtor: " + listCount);
				System.out.println("Total sales value of Property Listings for Realtor: $" + total);
				System.out.print("\n");
			}
			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + this.fileName);
			e.printStackTrace();
		}
	}

	/**
	 * This method prints the Car Usage Report using the data which was used to
	 * create the object.
	 */
	@SuppressWarnings("rawtypes")
	public void printCarUsageReport() {
		if (this.carStackLuxury == null || this.carStack == null || this.vehicleUsage == null
				|| this.realtorQueue == null || this.realtorLuxuryQueue == null) {
			return;
		}
		try {
			PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");
			HashMap<Realtor, Car> usage = vehicleUsage.getVehicleAssigmment();
			Iterator iterator = usage.entrySet().iterator();

			System.out.println("CAR USAGE REPORT");
			writer.println("CAR USAGE REPORT");

			while (iterator.hasNext()) {
				Map.Entry pair = (Map.Entry) iterator.next();
				Realtor realtor = (Realtor) pair.getKey();
				Car car = (Car) pair.getValue();
				System.out.println(
						realtor.getFirstName() + " " + realtor.getLastName() + " is using car number " + car.getId());
				writer.println(
						realtor.getFirstName() + " " + realtor.getLastName() + " is using car number " + car.getId());
				iterator.remove();
			}
			System.out.println("\nAVAILABLE CARS");
			writer.println("\nAVAILABLE CARS");
			System.out.println("\t" + Car.CAR_TYPES.BASIC.toString() + " CARS");
			writer.println("\t" + Car.CAR_TYPES.BASIC.toString() + " CARS");

			if (this.carStack.isEmpty()) {
				System.out.println("\t\tNo " + Car.CAR_TYPES.BASIC.toString().toLowerCase() + " cars are available");
				writer.println("\t\tNo " + Car.CAR_TYPES.BASIC.toString().toLowerCase() + " cars are available");
			} else {
				while (this.carStack.peek() != null) {
					Car car = carStack.pop();
					System.out.println("\t\t" + Car.CAR_TYPES.BASIC.toString().toLowerCase() + " car number "
							+ car.getId() + " is available");
					writer.println("\t\t" + Car.CAR_TYPES.BASIC.toString().toLowerCase() + " car number " + car.getId()
							+ " is available");
				}
			}

			System.out.println("\n\t" + Car.CAR_TYPES.LUXURY.toString() + " CARS");
			writer.println("\n\t" + Car.CAR_TYPES.LUXURY.toString() + " CARS");

			if (this.carStackLuxury.isEmpty()) {
				System.out.println("\t\tNo " + Car.CAR_TYPES.LUXURY.toString().toLowerCase() + " cars are available");
				writer.println("\t\tNo " + Car.CAR_TYPES.LUXURY.toString().toLowerCase() + " cars are available");
			} else {
				while (this.carStackLuxury.peek() != null) {
					Car car = carStackLuxury.pop();
					System.out.println("\t\t" + Car.CAR_TYPES.LUXURY.toString().toLowerCase() + " car number "
							+ car.getId() + " is available");
					writer.println("\t\t" + Car.CAR_TYPES.LUXURY.toString().toLowerCase() + " car number " + car.getId()
							+ " is available");
				}
			}

			System.out.println("\nTOP SELLER QUEUE");
			writer.println("\nTOP SELLER QUEUE");

			if (this.realtorLuxuryQueue.getSize() <= 0) {
				System.out.println("There are no top selling realtors waiting");
				writer.println("There are no top selling realtors waiting");
			} else {
				while (this.realtorLuxuryQueue.peek() != null) {
					Realtor realtor = this.realtorLuxuryQueue.remove();
					System.out.println(realtor.getFirstName() + " " + realtor.getLastName() + " is waiting");
					writer.println(realtor.getFirstName() + " " + realtor.getLastName() + " is waiting");
				}
			}

			System.out.println("\nSTANDARD REALTOR QUEUE");
			writer.println("\nSTANDARD REALTOR QUEUE");

			if (this.realtorQueue.getSize() <= 0) {
				System.out.println("There are no realtors waiting");
				writer.println("There are no realtors waiting");
			} else {
				while (this.realtorQueue.peek() != null) {
					Realtor realtor = this.realtorQueue.remove();
					System.out.println(realtor.getFirstName() + " " + realtor.getLastName() + " is waiting");
					writer.println(realtor.getFirstName() + " " + realtor.getLastName() + " is waiting");
				}
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + this.fileName);
			e.printStackTrace();
		}

	}
}
