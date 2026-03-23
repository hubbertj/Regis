// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 4, Program 2
// Program Description:	A program to identify the program,
//						using 1 to 3 input letters from the program abbreviation.

// Processing: The input 1 to 3 input letters from the user.
// Input: Program abbreviation.
// Output: The program which you are serching for or "Unknown Program" if program isn't found.
// -------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cctype> //used to uppercase my letters.

using namespace std;

int main() {

	//varaible which holds my user input.
	char programAbv;
	char secondLet;
	char thirdLet;
	string strOut = "Unknown Program";

	//Outputs my title:
	cout << setw(50) << left
			<< "The School of Computer and Information Sciences catalogy search:"
			<< "\n\n";
	cout << setw(50) << left
			<< "Enter programs first letter of addrevation (exmaple. Business Technology Management (B)):";
	cin >> programAbv;
	programAbv = toupper(programAbv);
	cout << "\n";

	switch (int(programAbv)) {
	case int('B'):
		strOut = "BS in Business Technology Management (BTM)";
		break;
	case int('D'):
		strOut = "MS in Database Technologies (DT)";
		break;
	case int('E'):
		strOut = "Certificate in E-Security (ES)";
		break;
	case int('C'):
		cout << setw(30) << left
				<< "Enter programs second letter of addrevation:";
		cin >> secondLet;
		secondLet = toupper(secondLet);

		switch (secondLet) {
		case int('I'):
			strOut = "BS in Computer Information Systems (CIS)";
			break;
		case int('N'):
			strOut = "BS in Computer Networking (CN)";
			break;
		case int('S'):
			strOut = "BS in Computer Science (CS)";
			break;
		default:
			strOut = "Unknown Program";
		}
		break;
	case int('I'):
		cout << setw(30) << left
				<< "Enter programs second letter of addrevation:";
		cin >> secondLet;
		secondLet = toupper(secondLet);
		switch (secondLet) {
		case int('A'):
			strOut = "MS in Information Assurance (IA)";
			break;
		case int('T'):
			strOut = "MS in Information Technology Management (ITM)";
			break;
		default:
			strOut = "Unknown Program";
		}
		break;
	case int('S'):
		cout << setw(30) << left
				<< "Enter programs second letter of addrevation:";
		cin >> secondLet;
		cout << "\n";
		secondLet = toupper(secondLet);
		switch (secondLet) {
		case int('A'):
			cout << setw(30) << left
					<< "Enter programs third letter of addrevation:";
			cin >> thirdLet;
			secondLet = toupper(thirdLet);
			switch (thirdLet) {
			case int('N'):
				strOut = "Certificate in Storage Area Networks (SAN)";
				break;
			default:
				strOut = "Unknown Program";
			}
			break;
		case int('E'):
			cout << setw(30) << left
					<< "Enter programs third letter of addrevation:";
			cin >> thirdLet;
			secondLet = toupper(thirdLet);
			switch (thirdLet) {
			case int('P'):
				strOut = "MS in Software Engineering Practices (SEP)";
				break;
			case int('T'):
				strOut = "MS in Systems Engineering Technologies (SET)";
				break;
			default:
				strOut = "Unknown Program";
			}
			break;
		default:
			strOut = "Unknown Program";
		}
		break;
	default:
		//redundent but who knowns what could happen.
		strOut = "Unknown Program";
		break;
	}

//output
	cout << setw(80) << internal << strOut;

	return 0;
}

