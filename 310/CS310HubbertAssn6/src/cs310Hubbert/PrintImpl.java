package cs310Hubbert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import cs310Hubbert.PropertyLogImpl.MapEntry;

/**
 *
 * @author Jerum Hubbert
 */
public class PrintImpl {

	private String fileName = null;

	/**
	 * Constructor which creates our data for the list which are passed in.
	 * 
	 * @param fileName
	 *            String the name of the file the report will be saved too.
	 */
	public PrintImpl(String fileName) {
		this.fileName = fileName;
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
	 * This method prints a request report for the office This will list
	 * Realtors and properties they want reported on.
	 * 
	 * @param vData
	 *            Vector of all data need for this report
	 */
	public void printOfficeRequestReport(Vector<MapEntry<Realtor, Vector<Property>>> vData) {
		try {
			PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");
			for (MapEntry<Realtor, Vector<Property>> entry : vData) {

				if (entry.value == null) {
					System.out.println("Realtor " + entry.key.getLicenseNum() + " does not exist");
					writer.println("Realtor " + entry.key.getLicenseNum() + " does not exist");
					continue;
				}

				System.out.println("Realtor " + entry.key.getLicenseNum() + ", " + entry.key.getFirstName() + " "
						+ entry.key.getLastName());
				writer.println("Realtor " + entry.key.getLicenseNum() + ", " + entry.key.getFirstName() + " "
						+ entry.key.getLastName());

				for (Property property : entry.value) {
					if (property.getSold()) {
						System.out.println("\tProperty " + property.getMlsNum() + " is SOLD");
						writer.println("\tProperty " + property.getMlsNum() + " is SOLD");
					} else {
						System.out.println("\tProperty " + property.getMlsNum() + " is available for $"
								+ property.getAskingPrice());
						writer.println("\tProperty " + property.getMlsNum() + " is available for $"
								+ property.getAskingPrice());
					}
				}
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("failed to read to file " + this.fileName);
			e.printStackTrace();
		}
	}
}
