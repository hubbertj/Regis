// ----------------------------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 6, Program 1
// Program Description: Write a program based on the
//	previous series that calculates the value of the
//	natural logarithm of 2, given a specified number
//	of terms.
// Processing: Caculates the natural logarithm of
//	two.
// Input: The specified number of terms. The specific number of steps to display.
// Output: The natural logarithm of 2 per the number
//	of terms the user defined.
// ----------------------------------------------------------------------------

#include <iomanip> // for formatting my output.#include <iostream>       // cout#include <string>         // string#include <locale>         // locale, toupper#include <stdio.h>using namespace std;

void pause() {
	cout << endl << "Press any key to continue...";
	cout << endl;
	getchar();
}

string toUpperCase(string str) {
	locale loc;
	string outStr = "";
	for (unsigned int i = 0; i < str.length(); i++) {
		outStr = toupper(str[i], loc);
	}
	return outStr;
}

int getValidateNumber(string textStr) {
	int inputInteger = -1;

	//int has to be positive.
	while (inputInteger < 0) {
		cout << textStr + ": ";
		cin >> inputInteger;
	}
	return inputInteger;
}

double getAmount(string str, int max) {
	double amount = 0;
	do {
		cout << str << ":";
		cin >> amount;
		cout << endl;
	} while (amount < 0 || amount <= max);

	return amount;
}

string getTreatChoice(int num) {
	//constants
	const double FROZEN_YOGURT_PER_OUNCE = .44;
	const double SOFT_SERVE_ICE_CREAM_PER_OUNCE = .49;
	const double PREMIUM_ICE_CREAM_PER_OUNCE = 1.62;
	const double ICE_CREAM_BAR_PER_OUNCE = 3.49;

	string str;

	cout << setprecision(2) << fixed << "Treat Choices:" << endl;
	cout << setw(3) << left << " " << setw(30) << left << "Y - Frozen Yogurt"
			<< "$ " << FROZEN_YOGURT_PER_OUNCE << " per ounce" << endl;
	cout << setw(3) << left << " " << setw(30) << left
			<< "S - Soft Serve Ice Cream" << "$ "
			<< SOFT_SERVE_ICE_CREAM_PER_OUNCE << " per ounce" << endl;
	cout << setw(3) << left << " " << setw(30) << left
			<< "P - Premium Ice Cream" << "$ " << PREMIUM_ICE_CREAM_PER_OUNCE
			<< " per ounce" << endl;
	cout << setw(3) << left << " " << setw(30) << left << "B - Ice Cream Bar"
			<< "$ " << ICE_CREAM_BAR_PER_OUNCE << " per bar" << endl;

	do {
		cout << "For treat #" << num + 1 << ", Enter a choice:";
		cin >> str;
		cout << endl;
		str = toUpperCase(str);
	} while (!(str == "Y" || str == "S" || str == "P" || str == "B"));

	return str;
}

double getBaseTreatCharge(string tType, double amount) {
	//constants
	const double FROZEN_YOGURT_PER_OUNCE = .44;
	const double SOFT_SERVE_ICE_CREAM_PER_OUNCE = .49;
	const double PREMIUM_ICE_CREAM_PER_OUNCE = 1.62;
	const double ICE_CREAM_BAR_PER_OUNCE = 3.49;
	const double SMALL_DISCOUNT = .04;
	const double BIG_DISCOUNT = .08;
	const double TAKE_OUT_LIMIT = 16;
	const double LARGE_ORDER = 32;

	double discount = 0;

	if (amount > TAKE_OUT_LIMIT) {
		if (amount > LARGE_ORDER) {
			discount = amount * BIG_DISCOUNT;
		} else {
			discount = amount * SMALL_DISCOUNT;
		}
	}

	if (tType == "Y") {
		return (FROZEN_YOGURT_PER_OUNCE * amount) - discount;
	} else if (tType == "S") {
		return (SOFT_SERVE_ICE_CREAM_PER_OUNCE * amount) - discount;
	} else if (tType == "P") {
		return (PREMIUM_ICE_CREAM_PER_OUNCE * amount) - discount;
	} else if (tType == "B") {
		return (ICE_CREAM_BAR_PER_OUNCE * amount) - discount;
	} else {
		return 0;
	}
}

double getToppingCharge(string tType, double amount) {
	const double TAKE_OUT_LIMIT = 16;
	const double TOPPING_CHARGE = .63;

	double charge = 0;

	if (tType == "Y" || tType == "S" || tType == "P") {
		if (tType != "p" && amount > TAKE_OUT_LIMIT) {
			return charge;
		}
		int numberOfToppings = getValidateNumber("Enter number of toppings:");

		if (numberOfToppings > 1) {
			charge = (numberOfToppings - 1) * TOPPING_CHARGE;
		}
	}
	return charge;
}

double getTotalCharge(string tType) {
//	Max of 64 ounces for yogurt and soft serve, and 5 scoops for premium
	const double MAX_OF_YOGURT = 64;
	const double MAX_OF_PER = 5;

	double output = 0;
	string ouchStr = "Enter number of ounces:";
	string scoopsStr = "Enter number of scoops:";

	if (tType == "Y") {
		double amount  = getAmount(ouchStr, MAX_OF_YOGURT);
		output = getBaseTreatCharge(tType,amount);
		output += getBaseTreatCharge(tType,amount);
	} else if (tType == "S") {
		double amount  = getAmount(ouchStr, MAX_OF_YOGURT);
		output = getBaseTreatCharge(tType,amount);
		output += getBaseTreatCharge(tType,amount);
	} else if (tType == "P") {
		double amount  = getAmount(scoopsStr, MAX_OF_PER);
		output = getBaseTreatCharge(tType,amount);
		output += getBaseTreatCharge(tType,amount);
	} else if (tType == "B") {
		//I assume we can only purchase one bar at a time?
		output = getBaseTreatCharge(tType,1);
	}


	return output;
}

int main() {

	int numberOfTreats;

	cout << setw(100) << left
			<< "This program will calculate customer bills for frozen treats"
			<< endl;
	cout << endl;
	cout << setw(80) << left << "NEW CUSTOMER" << endl;
	numberOfTreats = getValidateNumber(
			"Number of treats purchased (0 to exit)");

	if (numberOfTreats == 0) {
		return 0;
	}

//my loop for each treat purchased.
	for (int i = 0; i < numberOfTreats; i++) {
		string tType = getTreatChoice(i);
		double totalCost = getTotalCharge(tType);
		cout << "Charge for treat #" << i + 1 << " is $" << totalCost << endl;
		pause();
		cout << endl;

	}
}

