//  Author / s : Marquiese Thompson
//  Serial Number / s : 66
//  Due Date  : 9.11.2019
//  Programming Assignment Number  1
//  Fall   2019   -   CS 3358    -    001
//
//  Instructor:    Husain Gholoom
//
//  This program implements the hailstone sequence on a positive number
//  of the user's choice. The program then takes the number and divides
//  it by 2 if it is even, but if it is odd then the next number in the
//  sequence will be multiplied by 3 then increased by 1. This process
//  continues until the results equal 1.

#include <iostream>
using namespace std;


void Header()
{
    cout << "Welcome to the hailstone sequence program!\n"<<
            "The function of the program is to  read a number n from the user\n"<<
            "and then tells the user four things.\n";

    cout << "\t1.\t The entire hailstone sequence starting at n, all on one line\n\t\t"<<
                    " with the numbers separated by spaces." << endl;
    cout << "\t2.\t The length of the hailstone sequence that starts with n.\n";
    cout << "\t3.\t The largest number in the hailstone sequence that starts with n.\n";
    cout << "\t4.\t How many of the numbers in that hailstone sequence are even.\n\n\n";
}
//---------------------------------------------------------------------------------------
void Footer()
{
	cout << "\nThe hailstone Algorithm is implemented By Marquiese Thompson\n\n";
}
//---------------------------------------------------------------------------------------
bool isPositive(int num)
{
	if (num > 0)
		return true;
	else if(num < 0)
        return false;
    else
		return false;
}
//---------------------------------------------------------------------------------------
void Hailstone(int user)
{
	int count = 1;
	int order[count];
	order[count-1] = user;
	int EvenCount = 0;
	int largest = user;
	int previous = user;
	while(previous != 1)
	{
		int result;
		if(previous%2 == 0)
			{
				result = (previous/2);
				order[count] = result;
				count = count+1;
				EvenCount = EvenCount + 1;

				if(previous < result)
					largest = result;

				previous = result;
			}
		else
			{
				result = ( (previous*3)+1 );
				order[count] = result;
				count = count+1;

				if(previous < result)
					largest = result;

				previous = result;
			}
	};
	cout << "The sequence of hailstone starting with " << user << " is\n";
	for(int i=0; i<count; i++)
    {
        cout << order[i] << " ";
    }
	cout << endl << "The length of the sequence is " << count<< "\n";
	cout << EvenCount << " of the numbers are even\n";
	cout << "The largest number in the sequence is " << largest << "\n\n";
}
//------------------------------------------------------------------------------------------
int main()
{
	Header();

	int USER_NUMBER = 0;
	char USER_CHOICE = 'y';

	while(USER_CHOICE != 'N' && USER_CHOICE != 'n')
	{

		cout << "\nEnter a Positive integer Number ----> ";
		cin >> USER_NUMBER;
		cout << "\n\n";
		if (isPositive(USER_NUMBER) == false)
			{
				cout << "\nERROR! Invalid number must be > 0\n\n";
			}
		else
			{
				Hailstone(USER_NUMBER);
			}

				cout << "\n\nWould you like to find hailstone sequence of another number\n-Enter y or Y for yes\n-Enter n or N for no\n";
				cin >> USER_CHOICE;
				cout << endl;

				if(USER_CHOICE != 'y' && USER_CHOICE != 'Y' && USER_CHOICE != 'N' && USER_CHOICE != 'n')
					{
					    cout << "\nERROR! Invalid choice\n";

                        cout << "\n\nWould you like to find hailstone sequence of another number\n-Enter y or Y for yes\n-Enter n or N for no\n";
                        cin >> USER_CHOICE;
					}

	};

	Footer();
	return 0;
}
