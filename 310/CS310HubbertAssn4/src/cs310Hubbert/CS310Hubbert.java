package cs310Hubbert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jerum Hubbert
 *
 */
public class CS310Hubbert {

	@SuppressWarnings("rawtypes")
	static RealtorLogImpl realtorLogImpl = new RealtorLogImpl();
	static PropertyLogImpl propertyLogImpl = new PropertyLogImpl();

	static final String INPUT_FILENAME = "input/assn4input1.txt";

	/**
	 * Reads the data from the INPUT FILE
	 */
	static void readDataFile() {
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
		} catch(Exception e){
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
		try{
			 realtor = new Realtor(realtorArr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if(realtor == null){
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
		
		try{
			property = new Property(propertyArr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if(property == null){
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

	/**
	 * The main method which run the entire program
	 * 
	 * @param args
	 *            String[] options arguments passed in when running the program.
	 */
	public static void main(String[] args) {
		//first run
		readDataFile();
		createReport("output/assn3initialReport.txt");
		
		//display data
		realtorLogImpl.traverseDisplay();
		propertyLogImpl.traverseDisplay();
		
		//clean up list
		realtorLogImpl.cleanUp();
		propertyLogImpl.cleanUp();
		
		//second run
		createReport("output/assn3cleanReport.txt");
	}

}
