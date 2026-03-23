// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 7, Program 1
// Program Description: Program to compute a term in an
// arithmetic series starting with 1.
// Processing: This program uses three methods to calculate
// the term for a series of numbers.
// Input: term number to be computed, common difference for term.
// Output: 3 outputs of the term nth you are looking for.
// -------------------------------------------------------

#include <iostream> // used for I/O
#include <iomanip> 	// 	for formatting my output.
using namespace std;

// This returns the term you selected using a standard math formula.
int calcFormulaTerm(int initTerm, int ninth, int commonDifference) {
	return 1 + ((ninth - 1) * commonDifference);
}

//The second function (CalcLoopTerm) will compute
//and return the nth term, using iteration to compute the term (i.e. a loop).
int calcLoopTerm(int initTerm, int ninth, int commonDifference) {
	int count = 1;
	int output = 0;
	int term = initTerm;
	while (count <= ninth) {
		if (count == ninth) {
			return term;
		}
		term += commonDifference;
		count++;
	}
	return output;
}

// This loops using recursion the fucntion will return the value of
// the term you define.
int calcRecursiveTerm(int initTerm, int ninth, int commonDifference) {
	if (ninth == 1) {
		return initTerm;
	} else {
		initTerm += commonDifference;
		return calcRecursiveTerm(initTerm, (ninth - 1), commonDifference);
	}
}

int main() {
	const int INTIAL_VALUE = 1;

	int formulaTerm = 0;
	int iterativeTerm = 0;
	int recursiveTerm = 0;
	int termNum = 0;
	int cd = 0;

	//Header for program
	cout << left
			<< "Program to compute a term in an arithmetic series "
					"starting with "
			<< INTIAL_VALUE << endl;
	cout << endl;

	//starts my loop
	do {
		//Get input one.
		cout << left << "Enter term number to compute (or 0 to exit): ";
		cin >> termNum;

		//Loops if term num is a incorrect value.
		while (termNum < 0) {
			cout << left
					<< "Invalid -- term number must be greater or equal to "
							"0, try again: ";
			cin >> termNum;
		}

		if (termNum == 0) {
			return 0;
		}

		cout << left << "Enter common difference to use: ";
		cin >> cd;
		cout << endl;

		formulaTerm = calcFormulaTerm(INTIAL_VALUE, termNum, cd);
		cout << "Formula Term = " << formulaTerm;
		cout << endl;
		iterativeTerm = calcLoopTerm(INTIAL_VALUE, termNum, cd);
		cout << "Iterative Term = " << iterativeTerm;
		cout << endl;
		recursiveTerm = calcRecursiveTerm(INTIAL_VALUE, termNum, cd);
		cout << "Recursive term = " << recursiveTerm;
		cout << endl;
		cout << endl;

	} while (cd != 0);

	return 0;
}
