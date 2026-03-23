// ----------------------------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 5, Program 2
// Program Description: A program to output accumlated values for each month,
//given a set amount saved each month, until the accumlated amount reaches a
//set goal amount
// Processing: The total amount saved every month.
// Input: Amount to save every month, annual interest rate, and the dollar
//goal amount.
// Output: The amount saved each month and the number of months needed to
//achive your saving goal.
// ----------------------------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cmath> // used to round my doubles

using namespace std;

//this function usings a math formula to get my monthly factor.
double getMonthlyFactor(double months, double monthInt) {
	return pow((1 + monthInt), months);
}

//this function usings a math formula total accumulated for x amount of months
double totalAmountAcc(double months, double factor, double monAmount,
		double monthInt) {
	return ((factor - 1) * monAmount) / monthInt;
}

int main() {
	double monAmount;
	double annIntRate;
	double monthlyInt;
	double goal;
	double currentMonth;

	double acculatedAmount = 0.00;

	string title = "Program will calculate the amount accumulated every"
			" month you save, until you reach your goal.";

	//all error strings
	string err1 = "Invalid amount -- must save between 5 and 5000 dollars a "
			"month. Try again.";
	string err2 = "Invalid amount -- annual interest rate must be between 1 "
			"and 25 percent. Try again.";
	string err3 = "Invalid amount -- goal amount must be between 0 and "
			"999,999.99 dollars. Try again.";

	cout << setw(100) << left << title << "\n\n";
	cout << setw(85) << "Enter the dollar amount to be saved each month:";
	cin >> monAmount;

	while (monAmount < 5 || monAmount > 5000) {
		cout << setw(85) << left << err1;
		cin >> monAmount;
	}

	cout << setw(85) << left
			<< "Enter the annual interest rate (between 1 and 25):";
	cin >> annIntRate;

	while (annIntRate < 1 || annIntRate > 25) {
		cout << setw(85) << left << err2;
		cin >> annIntRate;
	}

	monthlyInt = (annIntRate / 100) / (double) 12;

	cout << setw(85) << left << "Enter the dollar amount of your goal:";
	cin >> goal;

	while (goal < 0 || goal > 999999.99) {
		cout << setw(85) << left << err3;
		cin >> goal;
	}

	goal = floor((goal * 100) + 0.5) / 100.0;

	cout << setprecision(2);
	cout << fixed;
	cout << "\n\n";

	cout << setw(14) << left << "Monthly Number";
	cout << setw(30) << right << "Total Accumulated" << "\n";
	cout << setw(14) << left << "--------------";
	cout << setw(30) << right << "-----------------" << "\n";

	// starts the loop;
	int i = 1;
	while (goal > acculatedAmount) {
		double tmpAcc = totalAmountAcc((double) i,
				getMonthlyFactor((double) i, monthlyInt), monAmount,
				monthlyInt);
		tmpAcc = floor((tmpAcc * 100) + 0.5) / 100.0;

		acculatedAmount += tmpAcc;

		cout << setw(7) << internal << i;
		cout << setw(30) << internal << acculatedAmount << "\n";
		currentMonth = i;
		i++;
	}
	cout << "\n";
	cout << setw(50) << left << "At an annual interest rate of" << right
			<< annIntRate << "%" << "\n";
	cout << setw(50) << left << "With a monthly savings of" << right
			<< monAmount << "\n";
	cout << setw(50) << left << "It will take" << right << currentMonth
			<< " months" << "\n";
	cout << setw(50) << left << "To reach your goal of" << right << goal
			<< "\n";
	cout << setw(50) << left << "With a final accumulation of" << right
			<< acculatedAmount << "\n";
}

