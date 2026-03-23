// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 2, Program 1
// Program Description: Write a program to compute the electro-magnetic
//						force between 2 charged objects. The program shall.
// Processing: Calculates the area of circle
// Output: Displays the radius and area
// -------------------------------------------------------
#include <iostream> // for I/O
#include <iomanip> // for formatting output
#include <cmath> // for using pow function

using namespace std;


double const PI = 3.1415926535;
double const E = 8.854e-12;
double const C_CONSTANT = (1/(4*PI*E));

double cFirstObject;
double cSecondObject;
double betweenObjs;
double force;


// this funciton prints my header (self-defined)
void printHeader(){
	cout << setw(50) << "Calculation of Electro-magnetic Force between two Objects" << endl;
	cout << setw(25) << right <<  "by" << endl;
	cout << setw(33) << right <<  "Mr. Jerum Hubbert" << endl;
}

// this funciton is used to get all my inputs.
void getInputs(){
	cout << setw(70) << left << "Enter charge of object 1 (in coulombs):";
	cin  >> cFirstObject;
	cout << setw(70) << left << "Enter charge of object 2 (in coulombs):";
	cin >> cSecondObject;
	cout << setw(70) << left << "Enter distance between the centers of the two objects (in meters):";
	cin >> betweenObjs;
}

void calcResults(){
	force = C_CONSTANT * ((cFirstObject * cSecondObject)/(betweenObjs * betweenObjs));
}

//all my final results are displayed with this function.
void printResults(){
	cout << setw(50)<< "RESULTS:"<< endl;
	cout << endl;
	cout << setw(30)<<left<<"Charge of first object:"<<setw(15)<<right<<cFirstObject<<setw(10)<<left<<" coulombs"<< endl;
	cout << setw(30)<<left<<"Charge of second object:"<<setw(15)<<right<<cSecondObject<<setw(10)<<left<<" coulombs"<< endl;
	cout << setw(30)<<left<<"Distance between objects"<<setw(15)<<right<<betweenObjs<<setw(10)<<left<<" meters"<< endl;
	cout << endl;
	cout << setw(30)<<left<<"Permittivity of Free Space:"<<setw(15)<<right<<E<<setw(10)<<left<<" coulombs^2 per Newton meters^2"<< endl;
	cout << setw(30)<<left<<"Coulomb's Constant:"<<setw(15)<<right<<C_CONSTANT<<setw(10)<<left<<" Newton meters^2 Coulombs^-2"<< endl;
	cout << endl;
	cout << setw(30)<<left<<"Electromagnetic Force:"<<setw(15)<<right<<force<<setw(10)<<left<<" Newtons"<< endl;
}

//my main is very readable.
int main()
{
	printHeader();
	cout << endl;
	getInputs();
	calcResults();
	cout << endl;
	printResults();

	return 0;
}
