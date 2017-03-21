package CS310Hubbert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jerum Hubbert
 */
public class Main {

	public static void main(String[] args) {

		final String INPUT_FILENAME = "input/assn1input.txt";

		BufferedReader br = null;
		String line = "";
		int lineCount = 0;

		Realtor myRealtor = null;
		Realtor aRealtor = null;

		Property myProperty = null;
		Property aProperty = null;

		// Week 2

		System.out.println("Running Test Set 1a...\n");
		Realtor real = new Realtor("AA0123456", "Jerum", "hubbert", "702-743-6322", 20.6);
		System.out.println(real.toString() + "\n");
		System.out.println("Running Test Set 1b...\n");
		Property prop = new Property("1920394", "19029842", "1700 Toltec Cir", "somewhere", "co", 80152, 5, 3.5, false,
				299562.0);
		System.out.println(prop.toString() + "\n");

		System.out.println("Running Test Set 2a...");

		try {
			// format
			// REALTOR,ADD,licenseNum,firstName,lastName,phoneNumber,commissionPercentage
			// PROPERTY,ADD,mlsNum,licenseNum,streetAddr,city,state,zipCode,numBedrooms,
			// numBathRooms,sold(Y/N),askingPrice

			br = new BufferedReader(new FileReader(INPUT_FILENAME));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] inputRead = line.split(",");
				
				if (lineCount == 0 && inputRead[0].equals("REALTOR")) {
					if (inputRead[1].equals("ADD")) {
						System.out.println("\n");
						myRealtor = setRealtorAttributes(new Realtor(),
								new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6] });
						if (!myRealtor.isLicenseNumValid(myRealtor.getLicenseNum())) {
							System.err.println("License number is invalid!");
						}
						if (!myRealtor.isPhoneNumValid(myRealtor.getPhoneNum())) {
							System.err.println("Phone number is invalid!");
						}

						displayRealtorAttributes(myRealtor);
					}
				} else if (lineCount == 1 && inputRead[0].equals("REALTOR")) {
					System.out.println("Running Test Set 2a...");
					if (inputRead[1].equals("ADD")) {
						System.out.println("\n");
						aRealtor = setRealtorAttributes(new Realtor(),
								new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6] });
						if (!aRealtor.isLicenseNumValid(aRealtor.getLicenseNum())) {
							System.err.println("License number is invalid!");
						}
						if (!aRealtor.isPhoneNumValid(aRealtor.getPhoneNum())) {
							System.err.println("Phone number is invalid!");
						}

						displayRealtorAttributes(aRealtor);
					}
				} else if (lineCount == 2 && inputRead[0].equals("PROPERTY")) {
					System.out.println("Running Test Set 2c...");
					if (inputRead[1].equals("ADD")) {
						System.out.println("\n");
						myProperty = setPropertyAttributes(new Property(),
								new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6],
										inputRead[7], inputRead[8], inputRead[9], inputRead[10], inputRead[11] });

						if (!myProperty.isMlsValid(myProperty.getMlsNum())) {
							System.err.println("MLS number is not valid!");
						}
						if (!myProperty.isStateValid(myProperty.getState())) {
							System.err.println("State must be \"WY\" or \"CO\" ");
						}
						if (!myProperty.isZipcodeValid(myProperty.getZipCode())) {
							System.err.println("Zip code must start with 80, 81, 82, 83");
						}

						displayPropertyAttributes(myProperty);
					}

				} else if (lineCount == 3 && inputRead[0].equals("PROPERTY")) {
					System.out.println("Running Test Set 2d...");
					if (inputRead[1].equals("ADD")) {
						System.out.println("\n");
						aProperty = setPropertyAttributes(new Property(),
								new String[] { inputRead[2], inputRead[3], inputRead[4], inputRead[5], inputRead[6],
										inputRead[7], inputRead[8], inputRead[9], inputRead[10], inputRead[11] });

						if (!aProperty.isMlsValid(aProperty.getMlsNum())) {
							System.err.println("MLS number is not valid!");
						}
						if (!aProperty.isStateValid(aProperty.getState())) {
							System.err.println("State must be \"WY\" or \"CO\" ");
						}
						if (!aProperty.isZipcodeValid(aProperty.getZipCode())) {
							System.err.println("Zip code must start with 80, 81, 82, 83");
						}

						displayPropertyAttributes(aProperty);
					}
				}else{
					break;
				}

				lineCount += 1;
			}

		} catch (FileNotFoundException e) {
			System.err.println("cannot find input/assn1input.txt");
			e.printStackTrace();
		} catch (IOException e) {
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

		System.out.println("\n");
		System.out.println("Running Test Set 3a...\n");

		if (myRealtor.equals(aRealtor)) {
			System.out.println("Realtor objects are equal.\n");
		} else {
			System.out.println("Realtor objects are not equal!\n");
		}
		System.out.println("Running Test Set 3b...\n");
		if(myProperty.equals(aProperty)){
			System.out.println("Property objects are equal.\n");
		}else{
			System.out.println("Property objects are not equal!\n");
		}

	}

	public static Realtor setRealtorAttributes(Realtor realtor, String[] arr) {
		realtor.setLicenseNum(arr[0]);
		realtor.setFirstName(arr[1]);
		realtor.setLastName(arr[2]);
		realtor.setPhoneNum(arr[3]);
		realtor.setCommission(Double.parseDouble(arr[4]));
		return realtor;
	}

	public static void displayRealtorAttributes(Realtor realtor) {
		System.out.println(realtor.getLicenseNum() + "\n" + realtor.getFirstName() + "\n" + realtor.getLastName() + "\n"
				+ realtor.getPhoneNum() + "\n" + realtor.getCommission());
	}

	public static Property setPropertyAttributes(Property property, String[] arr) {
		property.setMlsNum(arr[0]);
		property.setLicenseNum(arr[1]);
		property.setStreetAddr(arr[2]);
		property.setCity(arr[3]);
		property.setState(arr[4]);
		property.setZipCode(Integer.parseInt(arr[5]));
		property.setBedrooms(Integer.parseInt(arr[6]));
		property.setBathrooms(Double.parseDouble(arr[7]));
		property.setSold(arr[8].equalsIgnoreCase("y"));
		property.setAskingPrice(Double.parseDouble(arr[9]));

		return property;
	}

	public static void displayPropertyAttributes(Property property) {
		System.out.println(property.getMlsNum() + "\n" + property.getLicenseNum() + "\n" + property.getStreetAddr()
				+ "\n" + property.getCity() + "\n" + property.getState() + "\n" + property.getZipCode() + "\n"
				+ property.getSold() + "\n" + property.getAskingPrice());
	}

}
