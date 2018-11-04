package cs310Hubbert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import cs310Hubbert.PropertyLogImpl.MapEntry;

/**
 * @author Jerum Hubbert
 *
 */
public class CS310Hubbert {

	static RealtorLogImpl realtorLogImpl = new RealtorLogImpl();
	static PropertyLogImpl propertyLogImpl = new PropertyLogImpl();

	static final String INPUT_FILENAME_OFFICE = "input/assn6input1.txt";
	static final String INPUT_FILENAME_OFFICE_REQUEST = "input/realtorRequests1.txt";
	static final String OUTPUT_FILENAME_OFFICE_REQUEST = "output/assn6salesReport.txt";

	/**
	 * Reads the data from the INPUT FILE for office data only
	 */
	static void readOfficeData() {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(INPUT_FILENAME_OFFICE));
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
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("cannot find " + INPUT_FILENAME_OFFICE);
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
	 * Reads the data from the INPUT FILE for the office request
	 */
	static Vector<MapEntry<Realtor, Vector<Property>>> readOfficeRequestData() {
		BufferedReader br = null;
		String line = "";
		Vector<MapEntry<Realtor, Vector<Property>>> vData = new Vector<MapEntry<Realtor, Vector<Property>>>();
		try {
			br = new BufferedReader(new FileReader(INPUT_FILENAME_OFFICE_REQUEST));

			while ((line = br.readLine()) != null) {
				Vector<Property> vProperty = new Vector<Property>();
				// use space as separator
				String[] inputRead = line.split(" ");
				String realtorLicenseNum = inputRead[0];
				Realtor realtor = realtorLogImpl.find(realtorLicenseNum);
				if (realtor == null) {
					Realtor real = new Realtor();
					real.setLicenseNum(realtorLicenseNum);
					vData.add(propertyLogImpl.new MapEntry<Realtor, Vector<Property>>(real, null));
					continue;
				}

				for (String mls : inputRead) {
					if (mls.equals(inputRead[0])) {
						continue;
					}
					Property property = propertyLogImpl.find(mls);

					if (property != null) {
						vProperty.add(property);
					}
				}
				
				vData.add(propertyLogImpl.new MapEntry<Realtor, Vector<Property>>(realtor, vProperty));
			}
		} catch (FileNotFoundException e) {
			System.err.println("cannot find " + INPUT_FILENAME_OFFICE_REQUEST);
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
		return vData;
	}

	/**
	 * 
	 * Adds a Realtor to our Log
	 * 
	 * @param realtorArr
	 *            String[] a array which holds all data for creating a Realtor
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

		realtorLogImpl.add(realtor);
	}

	/**
	 * Adds a property to our Log
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
		propertyLogImpl.add(property);
	}

	/**
	 * The main method which run the entire program
	 * 
	 * @param args
	 *            String[] options arguments passed in when running the program.
	 */
	public static void main(String[] args) {
		readOfficeData();
		realtorLogImpl.traverseDisplay();
		System.out.println();
		propertyLogImpl.traverseDisplay();
		Vector<MapEntry<Realtor, Vector<Property>>> vData = readOfficeRequestData();
		PrintImpl printer = new PrintImpl(OUTPUT_FILENAME_OFFICE_REQUEST);
		System.out.println("");
		printer.printOfficeRequestReport(vData);
	}

}
