// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 1, Program 2
// Program Description:
//		The airport parking service noticed that most of their customers park in uncovered parking.
//		Therefore, you will write a program that computes a bill for those customers. The daily rate for uncovered parking is $ 9.25.
//		The hourly rate for uncovered parking is 40 cents an hour.
//		Customers are also charged a tax of 7% of the parking charges, and an airport access fee of $ 2.30 per visit.
//		Since the number of days parked will vary, the program will need to do some calculations.
//		Prompt the user for the number of hours the customer parked. Use this figure to calculate the total bill.
//		For every 24 hours, charge the daily rate. Remaining hours will be charged at the hourly rate.
//		(Hint: Use the division operator (/) to determine the number of full days,
//				and use the modulo operator (%) to determine the number of remaining hours over the full days).

// Input:
//		ABC Parking
//		Uncovered Parking Billing Program
//		Enter license plate number: XXX234
//		Enter number of hours parked: 80

// Processing: Calculates how much parking fees are for parking at ABC parking.

// Output: ABC Parking Receipt
// -------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting output
#include <string> //for using strings in my function.
using namespace std;

int hours;
int const HOURS_IN_DAY = 24;
float const DAILY_RATE = 9.25;
float const HOURLY_RATE = .40;
float const AIRPORT_FEE = 2.30;
float const TAX = 7;
string const LOT_NAME = "ABC Parking";
string plate;
string parkType = "U";

float dailyCharge;
float hourlyCharge;
float parkingSubtotal;
float taxCharge;
float totalBill;


void printRct(){
	cout << endl;
	cout << LOT_NAME << " " << "Receipt" << endl;
	cout << endl;

	cout << setw(20) << left << "License Plate:" << setw(10) << right << plate << endl;
	cout << setw(20) << left << "Parking Type:" << setw(10) << right << parkType << endl;
	cout << setw(20) << left << "Hours Parked:" << setw(10) << right << hours << endl;
	cout << endl;

	cout << setw(20) << left << "Daily Charges" << setw(10) << right << setprecision(2) << fixed << dailyCharge << endl;
	cout << setw(20) << left << "Hourly Charges" << setw(10) << right << setprecision(2) << fixed << hourlyCharge << endl;
	cout << setw(30) << right << "---------------" << endl;

	cout << setw(20) << left << "Parking Subtotal" << setw(10) << right << setprecision(2) << fixed << parkingSubtotal << endl;
	cout << setw(20) << left << "Taxes" << setw(10) << right << setprecision(2) << fixed << taxCharge << endl;
	cout << setw(20) << left << "Airport Access Fee" << setw(10) << right << setprecision(2) << fixed << AIRPORT_FEE << endl;
	cout << setw(30) << right << "---------------" << endl;

	cout << setw(20) << left << "Total Parking Bill" << setw(10) << right << setprecision(2) << fixed << totalBill << endl;
}

void calcParkingFee(){

	//Calculate daily charge.
	if (hours > HOURS_IN_DAY ){
		int days = (hours/HOURS_IN_DAY);
		dailyCharge = (days * DAILY_RATE);
	}else{
		dailyCharge = 0;
	}

	//Calculate hourly charge.
	hourlyCharge = (hours * HOURLY_RATE);

	//calculate parking subtotal.
	parkingSubtotal = (hourlyCharge + dailyCharge);

	//calculate tax charge.
	taxCharge = (parkingSubtotal * (TAX/100));

	//calculate out final bill.
	totalBill = (parkingSubtotal + taxCharge + AIRPORT_FEE);
}

int main()
{
	cout << LOT_NAME << endl;
	cout << "Uncovered Parking Billing Program" << endl;
	cout << endl;
	cout << "Enter license plate number: ";
	cin >> plate;
	cout << "Enter number of hours parked: ";
	cin >> hours;
	calcParkingFee();
	printRct();


	return 0;
}
