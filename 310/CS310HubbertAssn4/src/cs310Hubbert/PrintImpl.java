package cs310Hubbert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Jerum Hubbert
 */
public class PrintImpl {

	private String fileName = null;
	private ArrayList<Realtor> realtorList;
	private Property[] propertyArr;
	private int propertyNum = 0;

	/**
	 * Constructor which creates our data for the list which are passed in.
	 * 
	 * @param propertyLogImpl
	 *            PropertyLogImpl A list of properties
	 * @param realtorLogImpl
	 *            RealtorLogImpl A list of realtors
	 * @param fileName String the name of the file the report will be saved too.
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
	 * This method prints the report using the data which was used to create the
	 * object. Currently this is the only report this class prints.
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
}
