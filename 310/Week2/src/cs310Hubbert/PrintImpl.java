package cs310Hubbert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Jerum Hubbert
 */

public class PrintImpl {

	final String OUTPUT_FILENAME = "output/assn2report.txt";

	private ArrayList<Realtor> realtorList;
	private Property[] propertyArr;
	private int propertyNum = 0;

	public PrintImpl(PropertyLogImpl propertyLogImpl, RealtorLogImpl realtorLogImpl) {
		this.realtorList = new ArrayList<Realtor>();
		this.realtorList.addAll(realtorLogImpl.getRealtorLog());
		this.propertyArr = propertyLogImpl.getPropertyArray();
		this.propertyNum = propertyLogImpl.getNumProperties();
		this.init();
	}

	private void init() {
	}

	public void print() {
		try {
			PrintWriter writer = new PrintWriter(this.OUTPUT_FILENAME, "UTF-8");

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
						writer.println(prop.getMlsNum() + "\t" + prop.getStreetAddr() + "\t" + prop.getBedrooms()
						+ "/" + prop.getBathrooms() + "\t" + "$ " + prop.getAskingPrice() + "\t" + sold);
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
			System.err.println("failed to read to file " + this.OUTPUT_FILENAME);
			e.printStackTrace();
		}
	}
}
