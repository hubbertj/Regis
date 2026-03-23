// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 3, Program 1
// Program Description: The local yogurt store would like you to write
//						a program to help them calculate the cost of yogurt purchases.
// Processing: The cost of yogurt purchased by a customer.
// Input: Weight of yogurt, plastic cup or waffle cone, number of toppings, and if punch card is 5 punches.
// Output: A receipt for the yogurt purchased.
// -------------------------------------------------------
#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cmath> // for using calculating the output.
#include <cctype> //used to uppercase my letters.

using namespace std;

// User define function for getting the discount allowed.
double getDiscountPrice(double ounce) {
	//I normally would say these should be a call member but I was told on the last assignment to not use
	//class members
	const double LOWER_DISCOUNT = 0.72;
	const double HIGH_DISCOUNT = 1.58;
	if (ounce > 16) {
		if (ounce > 31) {
			return HIGH_DISCOUNT;
		} else {
			return LOWER_DISCOUNT;
		}
	}
	return 0.0;
}

//my main is very readable.
int main() {
	//user defined constants.
	const double WAFFLE_PRICE = 0.57;
	const double PLASTIC_PRICE = 0.0;
	const double YOGURT_PER_OZ = 0.44;
	const double TOPPING_PRICE = 0.63;

	double discountAmount = 0;
	double weight = 0;
	char containerType;
	string containerToWord = "None";
	bool wantsToppings = false;
	int numberOfToppings = 0;
	bool isPunchCard;

	cout << setw(50) << left << "Yogurt Purchase Calculation Program" << endl;

	cout << setw(25) << left
			<< "Enter the weight of the yogurt, in ounces:               ";
	cin >> weight;
	cout << endl;

	cout << setw(25) << left
			<< "Plastic cup or waffle cone (P/W)?                        ";
	cin >> containerType;
	containerType = toupper(containerType);
	if (containerType == int('P')) {
		containerToWord = "Plastic Cup";
	} else if (containerType == int('W')) {
		containerToWord = "Waffle Cone";
	}
	cout << endl;

	cout << setw(25) << left
			<< "Toppings wanted (T for toppings/N for no toppings)?      ";
	char topping;
	cin >> topping;
	if (topping == int('T') || topping == int('t')) {
		wantsToppings = true;
	} else if (topping == int('N') || topping == int('n')) {
		wantsToppings = false;
	}

	if (wantsToppings) {
		cout << setw(25) << left
				<< "Number of toppings?                                      ";
		cin >> numberOfToppings;
	}
	cout << endl;

	cout << setw(25) << left
			<< "Are there 5 punches on card (yes or no, all lowercase)?  ";
	string punchCard;
	cin >> punchCard;
	cout << endl;

	if (punchCard == "yes") {
		isPunchCard = true;
	} else {
		isPunchCard = false;
	}

	//user defined gets discount price.
	discountAmount = getDiscountPrice(weight);

	//final calculations.
	double yogurtCost = (weight * YOGURT_PER_OZ);
	double toppingCost = 0;
	if (wantsToppings) {
		toppingCost = (numberOfToppings * TOPPING_PRICE);
	}

	double subTotal;
	if (containerType == int('W')) {
		subTotal = (yogurtCost + toppingCost + WAFFLE_PRICE);
	} else {
		subTotal = (yogurtCost + toppingCost + PLASTIC_PRICE);
	}

	double billTotal = (subTotal - discountAmount);

	cout << setw(30) << left << "YOGURT ORDER" << endl;
	cout << setw(30) << left << "Weight (oz):" << right << weight << endl;
	cout << setw(30) << left << "Container:" << right << containerToWord
			<< endl;
	cout << setw(30) << left << "Number of Toppings:" << right
			<< numberOfToppings << endl;
	cout << endl;
	cout << setw(30) << left << "YOGURT BILL" << endl;
	cout << setw(30) << left << "Yogurt Cost:" << right << fixed
			<< setprecision(2) << yogurtCost << endl;
	cout << setw(30) << left << "Plus Toppings:" << right << fixed
			<< setprecision(2) << toppingCost << endl;
	cout << "--------" << endl;
	cout << setw(30) << left << "Subtotal:" << right << fixed << setprecision(2)
			<< subTotal << endl;
	cout << setw(30) << left << "Less Discount:" << right << fixed
			<< setprecision(2) << discountAmount << endl;
	cout << "--------" << endl;
	cout << setw(30) << left << "Bill Total:" << right << fixed
			<< setprecision(2) << billTotal << endl;
	if (isPunchCard) {
		cout << setw(30) << left << "5 punch reduced price:" << right << fixed
				<< setprecision(2) << billTotal / 2 << endl;
	}

	return 0;
}
