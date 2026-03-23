// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 2, Program 2
// Program Description: Write a program to compute the electro-magnetic
//						force between 2 charged objects. The program shall.
// Processing: Calculates the area of circle
// Input: Cone radius and cone height.
// Output: Displays the Cone Slant Height, Cone Surface Area, and Cone Volume.
// -------------------------------------------------------
#include <iostream> // for I/O
#include <iomanip> // for formatting output
#include <cmath> // for using pow function

using namespace std;


double const PI = 3.14159;

double cRadius;
double cHeight;

double sHeight;
double sArea;
double volumn;


// this funciton prints my header (self-defined)
void printHeader(){
	cout << setw(50) << left << "Program for Right Circular Cone calculations" << endl;
}

// this funciton is used to get all my inputs.
void getInputs(){
	cout << setw(25) << left << "Enter cone radius (in inches):";
	cin  >> cRadius;
	cout << setw(25) << left << "Enter cone height (in inches):";
	cin >> cHeight;
}
//calcs all my final values.
void calcResults(){
	sHeight = sqrt(pow(cHeight, 2) + pow(cRadius,2));
	sArea = (PI*pow(cRadius,2)) + (PI*cRadius*sHeight);
	volumn = (PI*pow(cRadius,2)*cHeight)/3;

}

//all my final results are displayed with this function.
void printResults(){
	cout << setw(50)<< "RESULTS:"<< endl;
	cout << setw(50)<< "For a Right Circular Cone with"<< endl;
	cout << setw(25)<<right<<"Radius of"<<setw(25)<<right<< floor(cRadius * 100 + .5) / 100<<setw(10)<<left<<" inches"<< endl;
	cout << setw(25)<<right<<"Height of"<<setw(25)<<right<<	floor(cHeight * 100 + .5) / 100<<setw(10)<<left<<" inches"<< endl;
	cout << endl;
	cout << setw(25)<<left<< "Cone Slant Height is"<<setw(25) << right << sHeight <<setw(10)<<left<<" inches"<< endl;
	cout << setw(25)<<left<< "Cone Surface Area is"<<setw(25) << right << sArea <<setw(10)<<left<<" square inches"<< endl;
	cout << setw(25)<<left<< "Cone Volume is"<<setw(25) << right << volumn <<setw(10)<<left<<" inches"<< endl;
}

//my main is very readable.
int main()
{
	printHeader();
	cout << endl;
	getInputs();
	calcResults();
	cout << endl;
	cout << endl;
	printResults();

	return 0;
}
