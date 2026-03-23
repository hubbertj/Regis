// ----------------------------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 7, Program 2
//
// Program Description: A program to output accumulated values for each month,
//given a set amount saved each month, until the accumulated amount reaches a
//set goal amount. This program also has a loan option which will determine
// how much interest you will pay for the loan and the monthly payment amount.
//
// Processing: The total amount saved every month and amount paid. The loan calculator
// will process how much money will be paid on the loan.

// Input: Savings Calculator - Year the savings will begin, month which savings
//will begin
// , the annual interest rate for savings account, savings each month, and
//savings goal amount.
//	Loan calculator - Year the loan will begin, month which loan will begin
// , the annual interest rate for the loan, the loan amount, and years
// for loan.
//
// Output: The savings calculator - will output a monthly savings until the
// goal is reached.
// Once the goal is reached it will out a summary of the savings.
//	The loan calculator - will output a summary of inputs and the total amount
// for the loan.
//  It will also output the total interested which was paid on the loan.
//
// ----------------------------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cmath> // used to round my doubles
#include <sstream> // used to convert from int to string
#include <string> // used for strings
using namespace std;
//this function uses a math formula to get my monthly factor.
void displayHeader() {
	cout << "Savings & Loan Payment Calculator" << endl;
}

double getMonthlyFactor(double months, double monthInt) {
	return pow((1 + monthInt), months);
}

///user-defined to get uppercase of string.
string toUpperCase(string str) {
	locale loc;
	string outStr = "";
	for (unsigned int i = 0; i < str.length(); i++) {
		outStr = toupper(str[i], loc);
	}
	return outStr;
}

// function for determining the month in string from a int representation.
string monthToString(int month) {
	if (month > 12) {
		if (month % 12 == 0) {
			month = 12;
		} else {
			month = month % 12;
		}
	}

	string allMonths[] = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	return allMonths[month - 1];
}

//function to determine the end date of a loan.
void getLoanEndDate(int startM, int startY, int lengthsInYears, string &endM,
		string &endY) {
	int year = startY;
	int month = startM;

	lengthsInYears = 12 * lengthsInYears;

	int i = 1;

	while (i < lengthsInYears) {
		if (month > 12 && month % 12 == 1) {
			year++;
		}
		month++;
		i++;
	}

	stringstream ss;
	// turns int to strings
	ss << year;
	endM = monthToString(month);
	endY = ss.str();
}

// generic function for getting a positive number.
int getValidateNumber(string textStr) {
	int inputInteger = -1;

//int has to be positive.
	while (inputInteger < 0) {
		cout << textStr + ": ";
		cin >> inputInteger;
	}
	return inputInteger;
}

// generic function for getting a number which is between the defined ints
double getNum(string str, int low, int max) {
	double amount = 0;
	cout << str << ":";
	cin >> amount;
	while (amount < low || amount > max) {
		cout << "Error input must be between " << low << " and " << max
				<< ", please try again: " << "\n";
		cout << str << ":";
		cin >> amount;
	}
	return amount;
}

//this function uses a math formula total accumulated for x amount of months
double totalAmountAcc(double factor, double monAmount, double monthInt) {
	return ((factor - 1) * monAmount) / monthInt;
}

//gets the start date from the user.
void getStartDate(string type, int &month, int &year) {
	const int CURRENT_YEAR = 2014;
	string msg = "Enter 4-digit year " + type + " will begin";
	string msg1 = "Enter numeric month " + type + " will begin";
	year = (int) getNum(msg, CURRENT_YEAR, CURRENT_YEAR + 5);
	month = (int) getNum(msg1, 1, 12);
}

//gets the annual interest rate and monthly interest rate.
void getInterestRate(double &monthly, double &annual) {
	const double LOWER_INT_LIMIT = 1.00;
	const double HIGH_INT_LIMIT = 20.00;
	string msg = "Enter the annual interest rate";
	annual = getNum(msg, LOWER_INT_LIMIT, HIGH_INT_LIMIT);
	monthly = (annual / 100) / (double) 12;
}

// This function determines if a menu choice is valid or not.
bool isChoiceGood(string str) {
	const string C_EXIT = "E";
	const string C_LOAN = "L";
	const string C_SAVINGS = "S";

	if (str == C_EXIT) {
		return true;
	} else if (str == C_LOAN) {
		return true;
	} else if (str == C_SAVINGS) {
		return true;
	} else {
		return false;
	}
}

// Promotes the user for menu choices
string getMenuChoice() {
	string choice;
	do {
		cout << "Computation Choice:" << endl;
		cout << setw(5) << right << "S";
		cout << setw(10) << left << " - Savings" << endl;
		cout << setw(5) << right << "L";
		cout << setw(10) << left << " - Loan" << endl;
		cout << setw(5) << right << "E";
		cout << setw(10) << left << " - Exit" << endl;
		cout << "Enter your choice: ";
		cin >> choice;
		cout << endl;
		choice = toUpperCase(choice);
	} while (!isChoiceGood(choice));
	return choice;
}

// main function for processing the loan
void processLoan(int startMonth, int startYear, double monthlyInt,
		double annIntRate) {

	const double LOWER_LOAN_AMOUNT = 100;
	const double HIGHER_LOAN_AMOUNT = 900000;
	const int LOWER_LOAN_LIMIT = 1;
	const int HIGHER_LOAN_LIMIT = 30;

	double bAmount = 0;
	int loanYears = 0;

	string input1 = "Enter the total loan amount to be borrowed";
	string input2 = "Enter number of years for loan";

	bAmount = getNum(input1, LOWER_LOAN_AMOUNT, HIGHER_LOAN_AMOUNT);
	loanYears = getNum(input2, LOWER_LOAN_LIMIT, HIGHER_LOAN_LIMIT);

	string endMStr = "";
	string endYStr = "";

// updates references for month end and year end dates.
	getLoanEndDate(startMonth, startYear, loanYears, endMStr, endYStr);

	stringstream ss;

// turns int to strings
	ss << startYear;
	string lStartStr = ss.str();

	int factor = getMonthlyFactor((loanYears * 12), monthlyInt);
	double monthlyPayment = (factor * monthlyInt * bAmount) / (factor - 1);
	double totalLoanAmount = monthlyPayment * (loanYears * 12);
	double totalInterestPaid = totalLoanAmount - bAmount;

	cout << setprecision(2);
	cout << fixed;
	cout << "\n\n";
	cout << setw(50) << left << "For a loan amount of" << setw(20) << right
			<< bAmount << endl;
	cout << setprecision(3);
	cout << setw(50) << left << "At an annual interest rate of" << setw(20)
			<< right << annIntRate << setw(2) << "%" << endl;
	cout << setprecision(0);
	cout << setw(50) << left << "And a loan period of" << setw(20) << right
			<< loanYears << setw(2) << " years" << endl;
	cout << setprecision(2);
	cout << setw(50) << left << "Monthly payment will be" << setw(20) << right
			<< monthlyPayment << endl;
	cout << endl;
	cout << setw(50) << left << "Loan will begin" << setw(20) << right
			<< monthToString(startMonth) + " " + lStartStr << endl;
	cout << setw(50) << left << "Loan will end" << setw(20) << right
			<< endMStr + " " + endYStr << endl;
	cout << endl;
	cout << setw(50) << left << "Total loan payments" << setw(20) << right
			<< totalLoanAmount << endl;
	cout << setw(50) << left << "Original loan amount" << setw(20) << right
			<< bAmount << endl;
	cout << setw(50) << left << " " << setfill('-') << setw(20) << " " << endl;
	cout << setfill(' ') << setw(50) << left << "Total Interest paid will be"
			<< setw(20) << right << totalInterestPaid << endl;
}

// main function for processing a savings account.
void processSavings(int startMonth, int startYear, double monthlyInt,
		double annIntRate) {

	const double LOWER_MONTH_AMOUNT = 10;
	const double MAX_MONTH_AMOUNT = 6000;
	const double LOWER_GOAL_AMOUNT = 100;
	const double HIGHER_GOAL_AMOUNT = 900000;

	double monAmount = 0;
	double goal = 0;
	double currentMonth = 0;
	double acculatedAmount = 0.00;

	string input1 = "Enter the dollar amount to be saved each month";
	string input2 = "Enter the dollar amount of your goal";

	monAmount = getNum(input1, LOWER_MONTH_AMOUNT, MAX_MONTH_AMOUNT);
	goal = getNum(input2, LOWER_GOAL_AMOUNT, HIGHER_GOAL_AMOUNT);

	goal = floor((goal * 100) + 0.5) / 100.0;

	cout << setprecision(2);
	cout << fixed;
	cout << "\n\n";

	cout << setw(14) << left << "Monthly Number" << setw(10) << " " << setw(20)
			<< right << "Total Accumulated" << "\n";
	cout << setfill('-') << setw(14) << left << "";
	cout << setfill(' ') << setw(10) << " ";
	cout << setfill('-') << setw(20) << right << "" << "\n";
	cout << setfill(' ');

// starts the loop;
	int i = startMonth;
	int loopPause = 1;
	cin.ignore();
	while (goal > acculatedAmount) {

		double tmpAcc =
				totalAmountAcc(getMonthlyFactor((double) i, monthlyInt),
				monAmount, monthlyInt);
		tmpAcc = floor((tmpAcc * 100) + 0.5) / 100.0;

		acculatedAmount += tmpAcc;

		if (i > 12 && i % 12 == 1) {
			startYear++;
		}

		stringstream ss;
		ss << startYear;
		string startYearStr = ss.str();

		cout << setw(14) << left << startYearStr + " " + monthToString(i)
				<< setw(30) << right << acculatedAmount << endl;

		if (loopPause % 20 == 0) {
			cout << "Press any key to continue...";
			cin.get();
			displayHeader();
		}

		loopPause++;
		currentMonth++;
		i++;
	}
	cout << "\n";
	cout << setw(40) << left << "To reach your savings goal of" << setw(20)
			<< right << goal << "\n";
	cout << setw(40) << left << "With a monthly savings of" << setw(20)
			<< right
			<< monAmount << "\n";
	cout << setprecision(3);
	cout << setw(40) << left << "At an annual interest rate of" << setw(20)
			<< right << annIntRate << " %" << "\n";
	cout << setprecision(0);
	cout << setw(40) << left << "It will take" << setw(20) << right
			<< currentMonth << " months" << "\n";
	cout << setprecision(2);
	cout << setw(40) << left << "With a final accumulation of" << setw(20)
			<< right << acculatedAmount << "\n";
}

// This function will display the header/ title
int main() {
	string menuChoice = " ";
	int startMonth = 0;
	int startYear = 0;
	double monthlyInt = 0;
	double yearlyInt = 0;

	displayHeader();

	menuChoice = getMenuChoice();

	if (menuChoice == "E") {
		cout << "Exiting the program, thank you come again.";
		return 0;
	} else if (menuChoice == "S") {
		menuChoice = "savings";
	} else if (menuChoice == "L") {
		menuChoice = "loan";
	}

	getStartDate(menuChoice, startMonth, startYear);
	getInterestRate(monthlyInt, yearlyInt);

	if (menuChoice == "savings") {
		processSavings(startMonth, startYear, monthlyInt, yearlyInt);
	} else if (menuChoice == "loan") {
		processLoan(startMonth, startYear, monthlyInt, yearlyInt);
	} else {
		return 0;
	}
}
