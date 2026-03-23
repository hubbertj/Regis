// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 1, Program 1
// Program Description:
//		Assume an airport parking service wants to display the sales by parking type in their lot.
//		The parking type, number of cars, and total collected in one week are:
//		In the valet parking, 93 cars with total collected of $ 6,696.75
//		In the covered parking, 532 cars with total collected of $ 23,501.00
//		In the uncovered parking, 3881 cars with total collected of $ 139,122.20
//		In the overflow lot, 0 cars with total collected of $ 0.00

// Input: n/a
// Processing: n/a
// Output: printout of ABC Parking data.
// -------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting output
#include <string> //for using strings in my function.
using namespace std;

void printLine(){
	cout << "+" << left << setw(67) << setfill('-') << right << "+"<< setfill(' ') << endl;
}
void row(string aStr, double bb, double cb){
	cout <<"       " << setw(20) << left << aStr << setw(20) << setprecision(0) << fixed
			<< right << bb << setw(20) << setprecision(2) << fixed << right  << cb << endl;
}

int main()
{
	cout << setw(16.75) << left << "Lot Name:" << setw(16.75) << right  << "ABC Parking" << endl;
	cout << setw(16.75) << left << "Week:" << setw(16.75) << right  << "Sept 7-13, 2014" << endl;
	cout << endl;
	printLine();
	cout <<"       " << setw(20) << left << "Parking Type" << setw(20) << right << "Number of Cars"
			<< setw(20) << right << "Total Collected" << endl;
	printLine();
	row("Valet", 93, 6696.75);
	row("Covered", 532, 23501.00);
	row("Uncovered", 3881, 139122.20);
	row("Overflow", 0, 0.00);
	printLine();
	cout << endl;

	return 0;
}
