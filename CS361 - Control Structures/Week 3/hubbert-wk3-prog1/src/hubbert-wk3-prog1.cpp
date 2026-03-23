// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 3, Program 1
// Program Description: The local yogurt store would like you to write
//						a program to help them calculate the cost of yogurt purchases.
// Processing: The cost of yogurt purchased by a customer.
// Input: Weight of yogurt, plastic cup or waffle cone, number of toppings, and check for punch card.
// Output: A recipt for the yogurt purchased.
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

bool hasToppings;
int numberOfToppings = 0;
bool pCard;

string holderType;




// this funciton prints my header (self-defined)
void printHeader(){
	cout << setw(50) << left << "Program for Right Circular Cone calculations" << endl;
}

// this funciton is used to get all my inputs.
void getInputs(){
	string toppings = "N";
	string card = "no";

	cout << setw(25) << left << "Enter the weight of the yogurt, in ounces:";
	cin  >> cRadius;
	cout << setw(25) << left << "Plastic cup or waffle cone (P/W)?";
	cin >> holderType;
	cout << setw(25) << left << "Toppings wanted (T for toppings/N for no toppings)?";
	cin >> toppings;

	if(toppings == "N"){
		hasToppings = false;
	}else{
		hasToppings = true;
		cout << setw(25) << left << "Number of toppings?";
		cin >> numberOfToppings;
	}

	cout << setw(25) << left << "Are there 5 punches on card (yes or no, all lowercase)?";
	cin >> card;

	if(card == "yes"){
			pCard = false;
		}else{
			pCard = true;
	}
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
