// -------------------------------------------------------
// Author: Jerum Hubbert
// Class: CS361
// Assignment: Week 3, Program 2
// Program Description: The IRS allows a taxpayer to contribute to a ROTH IRA,
//		if his/her income is not over a specified limit and his/her filing status allows contributions.
//		You will write a program that carries out the logic of a simplified worksheet to determine how much
//		may be contributed to a ROTH IRA.
// Processing: calculate how much a user can contributions to a ROTH IRA.
// Input: 1. Single, 2. Married, Filing Jointly, 3. Head of Household, 4. Qualifying Widow, 5. Married, Filing Separately.
// Output: 1. Possible contribution limit, 2. Adjusted Gross Income, 3. Income Limit, and 9. Maximum ROTH IRA Contribution Limit.
// -------------------------------------------------------

#include <iostream> // for I/O
#include <iomanip> // for formatting my output.
#include <cmath> // for using calculating the output. and ceil function.
#include <cctype> //used to uppercase my letters.

using namespace std;

double getLineEight(double one, double seven){
	int multiple = 10;
	double unrounded = abs(one - seven);

	int remainder = fmod(unrounded, multiple);
	if (remainder == 0){
		// no remainder = a multiple of 10.
	   return unrounded;
	} else if (unrounded < 0){
		//if the number is somehow negative.
		return - (unrounded - remainder);
	}
	//rounds my number
	return ceil(unrounded/ 10) * 10;
}

int main() {
	//constants
	const int SINGLE = 1;
	const int MARRIED_FILING_JOINTLY = 2;
	const int HEAD_OF_HOUSEHOLD = 3;
	const int WIDOWER = 4;
	const int MARRIED_FILING_SEPARTELY = 5;

	//input variables.
	int status;
	int age;

	//the status as a string form.
	string strStatus;


	double lineOne = 0;
	double lineTwo = 0;
	double lineThree = 0;
	double lineFour = 0;
	double lineFive = 0;
	double lineSix = 0;
	double lineSeven = 0;
	double lineEight = 0;
	double lineNine = 0;

	//title output
	cout << setw(50) << left << "ROTH IRA Contribution Limit Worksheet Program" << endl;
	cout << endl;

	//get status type
	cout << setw(20) << left << "1 - Single" << endl;
	cout << setw(20) << left << "2 - Married, Filing Jointly" << endl;
	cout << setw(20) << left << "3 - Head of Household" << endl;
	cout << setw(20) << left << "4 - Qualifying Widow(er)" << endl;
	cout << setw(20) << left << "5 - Married, Filing Separately" << endl;
	cout << setw(47) << left << "Enter your filing status from above (1-5):";
	cin >> status;
	cout << endl;

	if (status == MARRIED_FILING_SEPARTELY){
			char answer = 'N';
			cout << setw(70) << left << "Did you live with your spouse for any part of the year (Y/N)?";
			cin >> answer;
			cout << endl;

			answer = toupper(answer);
			if (answer == int('N')){
				cout << setw(70) << left << "Contributions to a ROTH IRA are not allowed for taxpayers who:" << endl;
				cout << setw(5) << left << " " << setw(65) << left << "-File as Married Filing Separately" << endl;
				cout << setw(5) << left << " " << setw(65) << left << "-Did not live with their spouse for any part of the year." << endl;
				cout << setw(5) << left << " " << setw(65) << left << "Therefore, you cannot contribute to a ROTH IRA!" << endl;
//				system("PAUSE");
				return 1;
			}
	}

	//get age
	cout << setw(47) << left << "Enter your age:";
	cin >> age;
	cout << endl;

	if(age > 50){
		lineOne = 6500.00;
	}else{
		lineOne = 5500.00;
	}

	// get income
	cout << setw(47) << left << "Enter Adjusted Gross Income for IRA purposes:";
	cin >> lineTwo;
	cout << endl;
	cout << endl;
	cout << endl;

	cout << setw(100) << left << "ROTH IRA Contribution Limit Worksheet" << endl;
	cout << endl;

	if(status == SINGLE){
		lineThree = 112000.00;
		strStatus = "Single";
	}else if(status == HEAD_OF_HOUSEHOLD){
		lineThree = 112000.00;
		strStatus = "Head of Household";
	}else if (status == MARRIED_FILING_JOINTLY){
		lineThree = 178000.00;
		strStatus = "Married, Filing Separately";
	}else if(status == WIDOWER){
		lineThree = 178000.00;
		strStatus = "Qualifying Widow(er)";
	}else if(status == MARRIED_FILING_SEPARTELY){
		lineThree = 0;
		strStatus = "Married, Filing Separately";
	}

	cout << setw(50) << left << "Filing Status:" << setw(50) << strStatus << endl;
		cout << endl;
		cout << setw(50) << left << "1. Possible contribution limit:" << setw(50) << fixed << setprecision(2) <<lineOne << endl;
		cout << endl;
		cout << setw(75) << left << "2. Adjusted Gross Income" << setw(25) << fixed << setprecision(2) << lineTwo << endl;
		cout << setw(75) << left << "3. Income Limit:" << setw(25) << fixed << setprecision(2) <<lineThree << endl;

	if(!(lineTwo <= lineThree)){

		lineFour = (lineTwo - lineThree);

		if(status == SINGLE || status == HEAD_OF_HOUSEHOLD){
			lineFive = 15000.00;
		}else{
			lineFive = 10000.00;
		}

		cout << setw(75) << left << "4. Amount over Limit:" << setw(25) << fixed << setprecision(2) <<lineFour << endl;
		cout << endl;
		cout << setw(50) << left << "5. Division amount:" << setw(50) << fixed << setprecision(2) <<lineFive << endl;


		float tmpPoint = (lineFour / lineFive);

		tmpPoint = ceil(tmpPoint * 1000) / 1000;

		if(tmpPoint > 1.0){
			//Cannot contribute to a ROTH IRA.
			lineNine = 0;
			cout << endl;
			cout << setw(50) << left << "   Income too high – You cannot contribute to a ROTH IRA!" << endl;
			system("PAUSE");
			return 6;
		}else{
			lineSix = tmpPoint;
			lineSeven = (lineSix * lineOne);
			lineEight = getLineEight(lineOne, lineSeven);

			if(lineEight > 200){
				lineNine = lineEight;
			}else{
				lineNine = 200;
			}

			cout << setw(50) << left << "6. Reduction Percentage:" << setw(50) << fixed << setprecision(2) <<lineSix << endl;
			cout << setw(50) << left << "7. Reduction Amount:" << setw(50) << fixed << setprecision(2) <<lineSeven << endl;
			cout << setw(50) << left << "8. Reduced contribution limit:" << setw(50) << fixed << setprecision(2) <<lineEight << endl;
			cout << endl;
			cout << setw(75) << left << "9. Maximum ROTH IRA Contribution Limit" << setw(25) << fixed << setprecision(2) <<lineNine << endl;
		}

	}else{
		lineNine = lineOne;
		cout << endl;
		cout << setw(75) << left << "9. Maximum ROTH IRA Contribution Limit" << setw(25) << fixed << setprecision(2) <<lineNine << endl;
		system("PAUSE");
		return 4;
	}
	return 0;
}
