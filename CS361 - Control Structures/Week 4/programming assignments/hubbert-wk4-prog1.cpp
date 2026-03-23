// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 4, Program 1
// Program Description:	A department store would like you to write a program to
//						compute the bills for an upcoming sale. You will also write a
//						test plan for this program.
// Processing: Calculate the total bill for a customer.
// Input: Customer type, total amount of purchase, and credit card type.
// Output: Customer Category, Rewards Club Discount Rate, Total Purchase Amount, Less Discounted Amount
//		Subtotal after discount, Plus Sales Tax, and
// -------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cctype> //used to uppercase my letters.

using namespace std;

double getDiscountRate(char type, double total) {

//user-defined const
	const double GENERAL_PUBLIC_CUSTOMER = 3;
	const double GENERAL_PUBLIC_CUSTOMER01 = 5;
	const double GENERAL_PUBLIC_CUSTOMER02 = 8;
	const double GENERAL_PUBLIC_CUSTOMER03 = 16;
	const double STORE_REWARDS_CLUB_MEMBER = 3;
	const double STORE_REWARDS_CLUB_MEMBER01 = 7;
	const double STORE_REWARDS_CLUB_MEMBER02 = 12;
	const double STORE_REWARDS_CLUB_MEMBER03 = 20;
	const double STORE_CREDIT_CARD_HOLDER = 20;
	const double GOLD_CARD_DISCOUNT = 25;

	if (type == int('P')) {
		if (total < 50) {
			return GENERAL_PUBLIC_CUSTOMER;
		} else if (total < 100) {
			return GENERAL_PUBLIC_CUSTOMER01;
		} else if (total < 200) {
			return GENERAL_PUBLIC_CUSTOMER02;
		} else {
			//total has to be over 200 if the other conditions failed.
			return GENERAL_PUBLIC_CUSTOMER03;
		}
	} else if (type == int('R')) {
		if (total < 50) {
			return STORE_REWARDS_CLUB_MEMBER;
		} else if (total < 100) {
			return STORE_REWARDS_CLUB_MEMBER01;
		} else if (total < 200) {
			return STORE_REWARDS_CLUB_MEMBER02;
		} else {
			//total has to be over 200 if the other conditions failed.
			return STORE_REWARDS_CLUB_MEMBER03;
		}
	} else if (type == int('S')) {
		return STORE_CREDIT_CARD_HOLDER;
	} else if (type == int('G')) {
		return GOLD_CARD_DISCOUNT;
	}

//if all conditions fail we will return 0;
	return 0.0;
}

int main() {
	//define const
	const double TAX_RATE = 7.5;

	string strCustomerType;

	//user inputs values.
	char customerType;
	char creditCardType;
	double totalAmountPurchase;
	double discountRate;

	//final totals
	double lessDiscount;
	double subtotal;
	double totalBill;
	double tax;

	//title output
	cout << setw(50) << left << "Department Store Sale Bill Calculator" << endl;
	cout << endl;

	//get inputs
	cout << setw(50) << left << "Customer Type Choices:" << endl;
	cout << setw(10) << left << " " << setw(40) << "P – general public" << endl;
	cout << setw(10) << left << " " << setw(40) << "R – rewards club member"
			<< endl;
	cout << setw(10) << left << " " << setw(40) << "C – credit card holder"
			<< endl;
	cout << endl;

	cout << setw(50) << left << "Enter customer type:";
	cin >> customerType;
	customerType = toupper(customerType);
	cout << endl;

	cout << setw(50) << left << "Enter total amount of purchase:";
	cin >> totalAmountPurchase;
	cout << endl;

	if (customerType == int('C')) {
		cout << setw(50) << left
				<< "Credit card type (S for standard, G for gold):";
		cin >> creditCardType;
		creditCardType = toupper(creditCardType);
		cout << endl;
	}

	cout << endl;

	//set str name for customer type.
	if (customerType == int('P')) {
		strCustomerType = "General Public";
	} else if (customerType == int('R')) {
		strCustomerType = "Rewards Club";
	} else if (customerType == int('C')) {
		if (creditCardType == int('G')) {
			strCustomerType = "Gold Card Holder";
		} else {
			strCustomerType = "Credit Card Holder";
		}
	}

	//gets discount rate.
	if (creditCardType == int('G') || creditCardType == int('S') ) {
		discountRate = (getDiscountRate(creditCardType, totalAmountPurchase)
				/ 100);
	} else {
		discountRate = (getDiscountRate(customerType, totalAmountPurchase)
				/ 100);
	}

	//calculate final totals.
	lessDiscount = (totalAmountPurchase * discountRate);
	subtotal = totalAmountPurchase - lessDiscount;
	tax = (subtotal * (TAX_RATE / 100));
	totalBill = subtotal + tax;

	//receipt output
	cout << setw(50) << left << "Department Store Bill" << "\n\n";
	cout << setw(5) << " ";
	cout << setw(80) << left << "Customer Category:      ";
	cout << right << strCustomerType << "\n";

	cout << setw(5) << " ";
	cout << setw(80) << left << "Discount Rate:          ";
	cout << right << (discountRate * 100) << "%" << "\n";
	cout << setw(97) << right << "------------" << "\n";

	cout << setw(5) << " ";
	cout << setw(80) << left << "Total Purchase Amount:  ";
	cout << right << fixed << setprecision(2) << "$" << totalAmountPurchase
			<< "\n";

	cout << setw(5) << " ";
	cout << setw(80) << left << "Less Discounted Amount: ";
	cout << right << fixed << setprecision(2) << "$" << lessDiscount << "\n";
	cout << setw(97) << right << "------------" << "\n";

	cout << setw(5) << " ";
	cout << setw(80) << left << "Subtotal after discount:";
	cout << right << fixed << setprecision(2) << "$" << subtotal << "\n";

	cout << setw(5) << " ";
	cout << setw(80) << left << "Plus Sales Tax (7.5%)   ";
	cout << right << fixed << setprecision(2) << "$" << tax << "\n";
	cout << setw(97) << right << "------------" << "\n";

	cout << setw(5) << left << " ";
	cout << setw(80) << left << "Total Bill:             ";
	cout << right << fixed << setprecision(2) << "$" << totalBill << endl;

	return 0;
}

