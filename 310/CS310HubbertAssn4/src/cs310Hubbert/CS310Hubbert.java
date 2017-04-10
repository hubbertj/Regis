package cs310Hubbert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import cs310Hubbert.Car.CAR_TYPES;

/**
 * @author Jerum Hubbert
 *
 */
public class CS310Hubbert {

	@SuppressWarnings("rawtypes")
	static RealtorLogImpl realtorLogImpl = new RealtorLogImpl();
	static PropertyLogImpl propertyLogImpl = new PropertyLogImpl();
	static VehicleUsageImpl vehicleUsage = new VehicleUsageImpl();
	static RealtorQueueImpl realtorQueue = new RealtorQueueImpl();
	static RealtorQueueImpl realtorLuxuryQueue = new RealtorQueueImpl();
	static CarStackImpl carStack;
	static CarStackImpl carStackLuxury;

	/**
	 * The main method which run the entire program
	 * 
	 * @param args
	 *            String[] options arguments passed in when running the program.
	 */
	public static void main(String[] args) {
		final String INPUT_FILENAME_REALTOR_PROPERTY = "input/assn4input1.txt";
		final String INPUT_FILENAME_CAR_INFO = "input/carInfo1a.txt";

		readDataFile(INPUT_FILENAME_REALTOR_PROPERTY);
		// display data
		realtorLogImpl.traverseDisplay();
		propertyLogImpl.traverseDisplay();
		// clean up list
		realtorLogImpl.cleanUp();
		propertyLogImpl.cleanUp();
		createReport("output/assn4cleanReport.txt");

		initStacks();
		processCarInfo(INPUT_FILENAME_CAR_INFO);
		createCarUsageReport("output/carUsageReport.txt");

	}

	/**
	 * init the stack and adds car to them;
	 */
	static void initStacks() {
		// Basic Stack
		carStack = new CarStackImpl();
		carStack.push(new Car(1, "BasicCar1", CAR_TYPES.BASIC));
		carStack.push(new Car(2, "BasicCar2", CAR_TYPES.BASIC));
		carStack.push(new Car(3, "BasicCar3", CAR_TYPES.BASIC));
		carStack.push(new Car(4, "BasicCar4", CAR_TYPES.BASIC));

		// Luxury Stack
		carStackLuxury = new CarStackImpl();
		carStackLuxury.push(new Car(5, "LuxuryCar5", CAR_TYPES.LUXURY));
		carStackLuxury.push(new Car(6, "LuxuryCar6", CAR_TYPES.LUXURY));
		carStackLuxury.push(new Car(7, "LuxuryCar7", CAR_TYPES.LUXURY));
	}

	static void processCarInfo(String INPUT_FILENAME_CAR_INFO) {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(INPUT_FILENAME_CAR_INFO));
			while ((line = br.readLine()) != null) {
				// use space as separator
				String[] inputRead = line.split(" ");

				String action = inputRead[0].toUpperCase();
				String realtorLicenseNumber = inputRead[1].toUpperCase();

				if (action == null || realtorLicenseNumber == null) {
					continue;
				}

				if (action.equals("REQUEST")) {
					processCarRequest(realtorLicenseNumber);
				} else if (action.equals("RETURN")) {
					processCarReturn(realtorLicenseNumber);
				} else {
					System.err.println("Bad property in file");
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("cannot find " + INPUT_FILENAME_CAR_INFO);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param licenseNumber
	 */
	static void processCarRequest(String licenseNumber) {
		Realtor realtor = realtorLogImpl.getRealtorByLicense(licenseNumber);
		Boolean topSeller = false;

		// only realtor on the list can checkout a car
		if (realtor == null) {
			System.err.println("Unknown realtor " + licenseNumber + " not allowed access to cars. Request ignored.");
			return;
		}

		if (propertyLogImpl.totalPropertyValue(licenseNumber) >= 1_000_000) {
			topSeller = true;
		}

		if (topSeller) {
			// process top seller
			Car car = null;
			if (!carStackLuxury.isEmpty()) {
				car = carStackLuxury.pop();
			} else if (!carStack.isEmpty()) {
				car = carStack.pop();
			} else {
				System.out.println(
						realtor.getFirstName() + " " + realtor.getLastName() + " waiting in luxury realtor queue");
				realtorLuxuryQueue.add(realtor);
				return;
			}
			System.out.println("Top Seller " + realtor.getFirstName() + " " + realtor.getLastName()
					+ " has been assigned " + car.getType().toString().toLowerCase() + " car number " + car.getId());
			vehicleUsage.add(realtor, car);
			return;
		} else {
			// process non top seller
			Car car = null;
			if (!carStack.isEmpty()) {
				car = carStack.pop();
				System.out.println("Standard realtor " + realtor.getFirstName() + " " + realtor.getLastName()
						+ " has been assigned " + car.getType().toString().toLowerCase() + " car number "
						+ car.getId());
				vehicleUsage.add(realtor, car);
			} else {
				System.out.println(
						realtor.getFirstName() + " " + realtor.getLastName() + " waiting in standard realtor queue");
				realtorLuxuryQueue.add(realtor);
				return;
			}
		}
	}

	/**
	 * Process a car return and adds it back in the correct stack
	 * 
	 * @param realtorLicenseNumber
	 *            String the license number of the Realtor
	 */
	static void processCarReturn(String realtorLicenseNumber) {
		Realtor realtor = realtorLogImpl.getRealtorByLicense(realtorLicenseNumber);
		if (realtor == null) {
			return;
		}
		Car returnedCar = vehicleUsage.remove(realtor);

		if (returnedCar != null) {
			System.out.println(realtor.getFirstName() + " " + realtor.getLastName() + " has returned car number "
					+ returnedCar.getId());

			switch (returnedCar.getType()) {
			case LUXURY:
				carStackLuxury.push(returnedCar);
				break;
			case BASIC:
				carStack.push(returnedCar);
				break;
			}
		}
	}

	/**
	 * Reads the data from the INPUT FILE
	 */
	static void readDataFile(String INPUT_FILENAME) {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(INPUT_FILENAME));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] inputRead = line.split(",");

				String type = inputRead[0].toUpperCase();
				String action = inputRead[1].toUpperCase();

				if (type == null && action == null) {
					continue;
				}
				if (action.equals("ADD")) {
					if (type.equals("REALTOR")) {
						realtorAdd(
								new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6] });
					} else if (type.equals("PROPERTY")) {
						propertyAdd(new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6],
								inputRead[7], inputRead[8], inputRead[9], inputRead[10], inputRead[11] });
					}
				} else if (action.equals("DEL")) {
					if (type.equals("REALTOR")) {
						realtordel(inputRead[2]);
					} else if (type.equals("PROPERTY")) {
						propertyDel(inputRead[2]);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("cannot find " + INPUT_FILENAME);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * Adds a Realtor to our list
	 * 
	 * @param realtorArr
	 *            String[] a array which holds all data for creating a realtor
	 */
	@SuppressWarnings("unchecked")
	static void realtorAdd(String[] realtorArr) {
		Realtor realtor = null;
		try {
			realtor = new Realtor(realtorArr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (realtor == null) {
			return;
		}

		if (!realtor.isLicenseNumValid(realtor.getLicenseNum())) {
			System.err.println("Failed to add Realtor, bad license number " + realtor.getLicenseNum() + "\n");
			return;
		}

		if (!realtor.isPhoneNumValid(realtor.getPhoneNum())) {
			System.err.println("Failed to add Realtor " + realtor.getLicenseNum() + ", bad phone number "
					+ realtor.getPhoneNum() + "\n\n");
			return;
		}

		if (realtorLogImpl.isLicenseUnique(realtor.getLicenseNum())) {
			System.out.println("ADDED: Realtor with license " + realtor.getLicenseNum() + " to log.");
			RealtorNode<Realtor> node = new RealtorNode<Realtor>(realtor);
			realtorLogImpl.add(node);
		} else {
			System.out.println("Realtor with license " + realtor.getLicenseNum() + " already exist in log.\n");
		}

	}

	/**
	 * Adds a property to our list
	 * 
	 * @param propertyArr
	 *            String[] a array which holds all data for creating a property
	 */
	static void propertyAdd(String[] propertyArr) {
		Property property = null;

		try {
			property = new Property(propertyArr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (property == null) {
			return;
		}

		if (!property.isMlsValid(property.getMlsNum())) {
			System.err.println("Property " + property.getMlsNum() + " has bad mls number.\n");
		}
		if (!property.isStateValid(property.getState())) {
			System.err.println(
					"Property " + property.getMlsNum() + ", " + property.getState() + " state is not valid.\n");
		}
		if (!property.isZipcodeValid(property.getZipCode())) {
			System.err.println(
					"Property " + property.getMlsNum() + ", " + property.getZipCode() + " zipcode is not valid.\n");
		}
		if (realtorLogImpl.isLicenseUnique(property.getLicenseNum())
				&& propertyLogImpl.isMlsUnique(Integer.parseInt(property.getMlsNum()))) {
			System.out.println("ADDED: Property with MLS number " + property.getMlsNum() + " for realtor "
					+ property.getLicenseNum() + ".\n");
			propertyLogImpl.add(property);
			return;
		}
		if (propertyLogImpl.isMlsUnique(Integer.parseInt(property.getMlsNum()))) {
			System.out.println("ADDED: Property with MLS number " + property.getMlsNum() + "");
			propertyLogImpl.add(property);
		}

	}

	/**
	 * 
	 * Deletes a Realtor from our list using their license number.
	 * 
	 * 
	 * @param license
	 *            String the license number for the Realtor
	 */
	static void realtordel(String license) {
		if (!realtorLogImpl.isLicenseUnique(license)) {
			System.out.println("DELETED: Realtor license " + license + " from log.\n");
			realtorLogImpl.remove(license);
			propertyLogImpl.remove(license);
		} else {
			System.err.println("Realtor license " + license + " not in log.\n");
		}
	}

	/**
	 * Deletes a property from our list using its MLS number.
	 * 
	 * 
	 * @param mlsNum
	 *            String a unique number for a property
	 */
	static void propertyDel(String mlsNum) {
		if (!propertyLogImpl.isMlsUnique(Integer.parseInt(mlsNum))) {
			System.out.println("DELETED: Property with MLS number " + mlsNum + "\n");
		} else {
			System.err.println("Property with MLS number " + mlsNum + " not in log.\n");
		}
	}

	/**
	 * Creates a report so data can be view quickly
	 */
	@SuppressWarnings("unchecked")
	static void createReport(String fileName) {
		System.out.println("Creating report...");
		PrintImpl printImpl = new PrintImpl(propertyLogImpl, realtorLogImpl, fileName);
		System.out.println("Report is located in file: " + printImpl.getFileName() + "\n");
		printImpl.print();
	}

	@SuppressWarnings({ "unchecked" })
	static void createCarUsageReport(String fileName) {
		PrintImpl printImpl = new PrintImpl(propertyLogImpl, realtorLogImpl, vehicleUsage, carStackLuxury, carStack,
				realtorQueue, realtorLuxuryQueue, fileName);
		System.out.println("\nCar usage report is located in file: " + printImpl.getFileName() + "\n");
		printImpl.printCarUsageReport();
	}
}
