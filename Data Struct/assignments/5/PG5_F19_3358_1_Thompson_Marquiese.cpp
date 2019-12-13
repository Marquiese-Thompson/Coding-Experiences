// Author / s : Marquiese Thompson & Tim George
// Serial Number / s : 66 & 14
//
// Due Date  : 11.4.2019
// Programming Assignment Number  5
// Fall   2019   -   CS 3358    -    001
//
// Instructor:    Husain Gholoom
//
// This program implements recursive functions. The program takes a size from the
// user then creates an array with random numbers with that size. Then operates on
// the array in different ways using recursive functions.

#include <iostream>
#include <time.h>
#include <cstdlib>
using namespace std;


void Header(); //outputs header of program
void Footer(); // shows footer of program

bool isValidNumber(int); // analyze the users chosen size
void populateArray(int[], int); // populates the array with random numbers(10 - 200)
void displayArray(int[], int); // displays the contents of the new array
void reverseArray(int[], int); // shows the reverse order of the array
int powered(int, int); // raises the last number to the 2nd power
void squares(int, int); // shows the square values from 1 - the first array number

void displayVertically(int); // displays number vertically
bool isInOrder(int); //checks if the number is in increasing order
int reverseNumber(int, int); //reverses the number
bool isPrimeNumber(int, int); //checks if number is prime or not
//----------------------------------------------------------------------------------
int main()
{
    Header();

    int userSize; // user array size
    cout << "\nEnter The array size. ( Must be >= 5 ) -->\t";
    cin >> userSize;
    cout << "\n";

    if (isValidNumber(userSize) == true) // user input validation
    {
		int intArray[userSize]; // array with the chosen size
		populateArray(intArray, userSize); // populates the array
		displayArray(intArray, userSize); // shows the array

		cout << "\nReversed Array is : ";
		int accurances = (userSize-1);
		reverseArray(intArray, accurances); // shows the array in
											// reverse recursively
		cout << "\n";

		int lastElement = intArray[(userSize - 1)]; // the last element
													//   in the array
		int lastX2 = powered(lastElement, 1); // holds the last element ^ 2

		cout << "\n";
		cout << lastElement << " raised to the 2nd power is: " << lastX2;
		cout << "\n";

		cout << "\nTable of square values 1 - " << intArray[0];
		cout << "\n\nN\tN Squared\n";
		squares(1, (intArray[0]+1));
        cout << "\n";

		cout << intArray[1] << " vertically\n\n";
		displayVertically(intArray[1]);
        cout << "\n";

        bool numberInOrder = isInOrder(intArray[2]); //if number is in increasing order
        if(numberInOrder) {
            cout << intArray[2] << " is in increasing order\n";
        } else {
            cout << intArray[2] << " is not in increasing order\n";
        }
        cout << "\n";

		int reversedNumber = reverseNumber(intArray[3], 0); //the number in reversed order
		cout << intArray[3] << " reversed is " << reversedNumber << "\n";
        cout << "\n";

        bool isPrime = isPrimeNumber(intArray[4], intArray[4] / 2); //if number is prime # or not

        if(isPrime) {
            cout << intArray[4] << " is prime "<< "\n";
        } else {
            cout << intArray[4] << " is not prime "<< "\n";
        }
    }
    else
	{
        cout << "\nInvalid arrays size. Size must be >= 5 and <= 15.\n";
	}

	Footer();
	return 0;
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void Header
//
// displays the proper header message
//****************************************************************
//----------------------------------------------------------------------------------
void Header()
{
    cout << "*** Welcome to My Recursion APP ***\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void Footer
//
// displays the proper footer message
//*****************************************************************
//----------------------------------------------------------------------------------
void Footer()
{
	cout << "\n\nTim George & Marquiese Thompson - Programmers\n";
	cout << "November 2019\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// bool isValidNumber
//
// returns true is the chosen number is (16 > number < 4)
//*****************************************************************
//----------------------------------------------------------------------------------
bool isValidNumber(int user)
{
    if(user > 15 || user < 5)
        return false;
    else
        return true;
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void populateArray
//
// populates the array with random numbers 10 - 200
//*****************************************************************
//----------------------------------------------------------------------------------
void populateArray(int intArray[], int arrSize)
{
    srand(time(0));
    for(int i = 0; i < arrSize; i++)
        intArray[i] = (10 + rand()%190); // adds random numbers to fill the array
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void displayArray
//
// displays the array contents
//*****************************************************************
//----------------------------------------------------------------------------------
void displayArray(int intArray[], int arrSize)
{
    cout << "\nThe Generated array is : ";

    for(int i = 0; i < arrSize; i++)
        cout << intArray[i] << " "; // displays the array contents

    cout<< "\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void reverseArray
//
// recursive function that show the array in reverse order
//*****************************************************************
//----------------------------------------------------------------------------------
void reverseArray(int intArray[], int times)
{
    if(times > -1)
    {
        cout << intArray[times] << " "; // displaying the array contents in reverse
        reverseArray(intArray, (times - 1));
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// int powered
//
// multiplies the last element in the array by itself
//*****************************************************************
//----------------------------------------------------------------------------------
int powered(int last, int power)
{
    if(power < 2)
        return (last * powered(last, (power + 1)));
}
//----------------------------------------------------------------------------------
//******************************************************************
// void squares
//
// displays the square numbers starting form 1 until the first
// element in the array
//*****************************************************************
//----------------------------------------------------------------------------------
void squares(int index, int first)
{
    if(index < first)
    {
        cout << index <<"\t" << (index*index) << "\n"; // show the number then squared
        squares((index + 1), first);
    }
}
//----------------------------------------------------------------------------------
//******************************************************************
// void displayVertically
//
// displays a number in vertical format
//*****************************************************************
//----------------------------------------------------------------------------------
void displayVertically(int num)
{
    if (num >= 10)
        displayVertically(num / 10);

    int digit = num % 10; //get the digit from number
    cout << digit << endl;
}
//----------------------------------------------------------------------------------
//******************************************************************
// bool isInOrder
//
// checks if number is in increasing order or not
//*****************************************************************
//----------------------------------------------------------------------------------
bool isInOrder(int num)
{
    int lastDigit = num % 10; //gets last digit from number
    num = num / 10; //divides by 10 to get remaining numbers

    if(num == lastDigit) {
        return true;
    } else if (lastDigit <= (num % 10)) {
        return false;
    } else {
        return isInOrder(num);
    }
}
//----------------------------------------------------------------------------------
//******************************************************************
// int reverseNumber
//
// returns the number in reversed order
// int number is the number to reverse
// int reversed is the reversed number
//*****************************************************************
//----------------------------------------------------------------------------------
int reverseNumber(int number, int reversed)
{
    if(number == 0)  {
        return reversed;
    }

    int noLastDigit = number / 10; //number without the last digit from number
    int lastDigit = (number % 10); //gets the last digit of the number
    int nextNumber = reversed * 10; //adds a zero for each digit
    reversed = nextNumber + lastDigit; //adds the digit to the number
    return reverseNumber(noLastDigit, reversed);
}
//----------------------------------------------------------------------------------
//******************************************************************
// bool isPrimeNumber
//
// checks if number is a prime number
// int num is the number to check if prime or not
// int divisor is to see if num is divisible by it or not
//*****************************************************************
//----------------------------------------------------------------------------------
bool isPrimeNumber(int num, int divisor)
{
    if(num < 2) {
        return false;
    } else if(divisor == 1) {
        return true;
    } else {
        if (num % divisor == 0) {
            return false;
        } else {
            return isPrimeNumber(num, divisor - 1);
        }
    }
}
