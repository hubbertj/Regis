// ----------------------------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 5, Program 1
// Program Description: Write a program based on the
//	previous series that calculates the value of the
//	natural logarithm of 2, given a specified number
//	of terms.
// Processing: Caculates the natural logarithm of
//	two.
// Input: The specified number of terms. 
//The specific number of steps to display.
// Output: The natural logarithm of 2 per the number
//	of terms the user defined.
// ----------------------------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
using namespace std;

//fucntion which is used to prompt the user and check for error
int userPrompts(string in){
	int terms;
	cout << setw(75)<< left << in << setw(2);
	cin >> terms;

	while(terms <= 0){
		cout << setw(75) << left
		<<"ERROR – Input must be positive and non-zero! Please try again.";
		cin >> terms;
	}
	cout << endl;
	return terms;
}

int main() {
	int terms, steps = 0;
	double curOutput;
	//This bool is used to determine if output factor should be a neg or pos.
	bool sign = true;

	cout << "This program will approximate the natural logarithm of 2";
	cout << endl;

	terms = userPrompts("Enter the number of terms to compute:");
	steps = userPrompts("Display value after every how many setps?");

	cout << setprecision(10);
	cout << fixed;

//starts my loop
	for (int i = 1; i <= terms; i++) {
		if(sign){
			curOutput +=  1.0/(double)i;
			sign = false;
		}else{
			curOutput -=  1.0/(double)i;
			sign = true;
		}


		if ((i % steps) == 0) {
			if(i == terms){
				cout << "Finally, after \n";
			}
			cout << setw(5) << left << i << setw(15) << left
					<< " steps:   ln(2) = " << curOutput << "\n";
		}

		if ( i == terms && !(i % steps == 0)) {
			cout << "Finally, after \n";
			cout << setw(5) << left << i << setw(15) << left
					<< " steps:   ln(2) = "  << curOutput << "\n";
		}
	}
}

