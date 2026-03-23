// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 6, Program 2
// Program Description:	Write a program that determines 
// which numbers produce a happy sequence, within a series 
// of test numbers.
// Processing: Calculates a sequence of numbers such as 
// 19 = pow(1,2) + pow(9,2) = 82  82 = pow(8,2) + pow(2,2) = 68
// Input: A stating number and a finishing number.
// Output: Each solution of a sequence of numbers. The sequence 
// will continue until either 1 is reach or the sequnce has 
// returned 50 solutions.
// -------------------------------------------------------

#include <iostream> // 	for I/O
#include <iomanip> 	// 	for formatting my output.
#include <cmath> 	//	used for log10
using namespace std;

// returns a true if the sequence is happy(reaches 1 before 50 solutions).
// returns false if the sequence is unhappy(never reaches one before
// 50 solutions are returned).
bool isNumHappy(int num) {

	const int LOOP_LIMIT = 50;
	const int ROW_COUNT = 10;

	int outputNum = num;
	int count = 1;
	while (outputNum != 1 && count <= LOOP_LIMIT) {
		int dig = outputNum > 0 ? (int) log10((double) outputNum) + 1 : 1;
		int tmpNum = 0;
		for (int i = 0; i < dig; i++) {
			int factor = pow(10, i);
			tmpNum += pow((outputNum / factor) % 10, 2);
		}
		cout << setw(8) << left << tmpNum;
		if (count % ROW_COUNT == 0 || tmpNum == 1) {
			cout << endl;
		}
		outputNum = tmpNum;
		count++;
	}

	//we deincrement becuase we exited the loop.
	count--;

	cout << count << " sequence terms computed" << endl;
	if (count == LOOP_LIMIT) {
		return false;
	} else {
		return true;
	}
}

// Used to get the starting and last number from a user.
void getInputNums(int &fristNum, int &secondNum) {

	const int LOWER_LIMIT = 1;
	const int HIGH_LIMIT = 9999;

	cout << "Enter the first number to test: ";
	cin >> fristNum;
	cout << endl;

	while (fristNum < LOWER_LIMIT || fristNum > HIGH_LIMIT) {
		cout << "Invalid -- must be between " << LOWER_LIMIT << " and "
				<< HIGH_LIMIT << " Try again." << endl;
		cout << "Enter the first number to test: ";
		cin >> fristNum;
		cout << endl;
	}

	cout << "Enter the last number to test: ";
	cin >> secondNum;
	cout << endl;

	while (secondNum < LOWER_LIMIT || secondNum > HIGH_LIMIT
			|| secondNum < fristNum) {
		if (secondNum < fristNum) {
			cout
					<< "Invalid -- must be not be greater then the frist number Try again."
					<< endl;
		} else {
			cout << "Invalid -- must be between " << LOWER_LIMIT << " and "
					<< HIGH_LIMIT
					<< " or greater then the frist number Try again." << endl;
		}

		cout << "Enter the last number to test: ";
		cin >> secondNum;
		cout << endl;
	}
}

int main() {
	int fNum = 0;
	int lastNum = 0;
	cout << setw(100) << left << "Program to test for Happy numbers" << endl;
	getInputNums(fNum, lastNum);
	
	//clears the keyboard buff.
	cin.ignore();

	for (int i = fNum; i <= lastNum; i++) {
		cout << left << "Test sequence for " << i << " is:" << endl;
		bool isHappy = isNumHappy(i);
		cout << endl;
		if (isHappy) {
			cout << "The above sequence of numbers is happy" << endl;
		} else {
			cout << "The above sequence of numbers is unhappy" << endl;
		}
		cout << endl;

		//pauses the program.
		if (lastNum != i) {
			cout << "Press any key to continue..." << endl;
			cin.get();
		}
	}
	return 0;
}

