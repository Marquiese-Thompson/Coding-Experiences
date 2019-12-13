// Author / s : Marquiese Thompson
// Serial Number / s : 66
//
// Due Date  : 12.2.2019
// Programming Assignment Number  7
// Fall   2019   -   CS 3358    -    001
//
// Instructor:    Husain Gholoom
//
// This program implements hashing

#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

void Header(); //outputs header of program
void Footer(); // shows footer of program
void Menu(); // displays the menu options

void populate(char[]); // populates the array up to size 15 with upper case letters
void display(char[], int); // displays the array with 3 spaces in between each other

void Search(char[], int&); // searches array for a character
void Insert(char[], int&); // inserts new character in the array
void Delete(char[], int&); // deletes a character from the array


//----------------------------------------------------------------------------------
int main()
{
    Header();

    int arrSize = 20; // constant array size
    char userChoice = 'A'; // user menu choice
    char charArray[arrSize]; // character array with size 20

    populate(charArray);

    int probeCountAdd = 0; //number of linear probes when adding a char
    int probeCountSearch = 0; //number of linear probes when searching for a char
    int probeCountDelete = 0; //number of linear probes when deleting a char

    while(!(userChoice == 'X'))
    {
        Menu(); // displays the menu

        cout << "Choose your option: ";
        cin >> userChoice;
        cout << "\n\n";

        switch(userChoice)
        {
            case 'A':
                cout << "Displaying the generated array.\n";
                display(charArray,arrSize);
                break;
            case 'B':
                Search(charArray, arrSize);
                break;
            case 'C':
                Insert(charArray, arrSize);
                break;
            case 'D':
                Delete(charArray, arrSize);
                break;
            case 'X':
                break;
            default:
                cout << "*** Invalid Option ***";
                break;
        }
    }


    cout << "The number of linear probes when each number is hashed and collision ";
    cout << "occurred when adding a character in the array is " << probeCountAdd << "\n\n";

    cout << "The number of linear probes when each number is hashed and collision ";
    cout << "occurred when searching a character in the array is " << probeCountSearch << "\n\n";

    cout << "The number of linear probes when each number is hashed and collision ";
    cout << "occurred when deleting a character in the array is " << probeCountDelete << "\n\n";

	Footer();
	return 0;
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void Header
//
// displays the proper header message
//*****************************************************************
//----------------------------------------------------------------------------------
void Header()
{
    cout << "Welcome to my Hashing Program\n\n";

    cout << "------------------------------\n\n";

    cout << "A. Creates an char array of size 20. Assigning - to each\n";
    cout << "location in the array indicating that the array is empty.\n";

    cout << "B. Populates 10 elements of the array with random characters\n";
    cout << "between A and Z inclusive.\n";

    cout << "C. If a collision occurs, linear probing will find the next\n";
    cout << "available position / location.\n";

    cout << "D. The generated array will be displayed in 2 lines.\n";
    cout << "Each line contains 15 characters separated by 2 spaces.\n\n";
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
	cout << "\n\n\nThis hashing program was implemented by :\n";
	cout << "Marquiese Thompson - 12-2-2019\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void menu
//
// returns true if the size is an acceptable number
//*****************************************************************
//----------------------------------------------------------------------------------
void Menu()
{
    cout<<"\n\n\nChoose from the following menu options\n\n";

    cout<<"A.  Display the generated array.\n";
    cout<<"B.  Search for a char (between A - Z) in the array. \n";
    cout<<"C.  Insert a new char (between A - Z ) in the array. \n";
    cout<<"D.  Delete a char (between A - Z ) from the array. \n";
    cout<<"X.  End the program. \n\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void HPosition
//
// returns the hashing position of a letter
//*****************************************************************
//----------------------------------------------------------------------------------
int HPosition(char character) {
    return character % 10;
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void RHPosition
//
// returns the rehashing position of a letter to counter collision
//*****************************************************************
//----------------------------------------------------------------------------------
int RHPosition(int position) {
    return (position + 1);
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void populate
//
// populates the array with random capital letters up to position 15
//*****************************************************************
//----------------------------------------------------------------------------------
void populate(char charArray[])
{
    for(int i = 0; i < 20; i++)
        charArray[i] = '*';


    srand(time(NULL));
    for(int i = 0; i < 15; i++)
    {
        char character = (rand() % (90 - 66) + 65); // adds random numbers to fill the array
        int position = HPosition(character);
        while (charArray[position] != '*') {
            position = RHPosition(position);
            if(position > 20) position = 0;
        }
        if (charArray[position] == '*') {
            charArray[position] = character;
        }
    }


    cout << "The Generated Array.\n";
    display(charArray, 20);
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void display
//
// displays the array contents
//*****************************************************************
//----------------------------------------------------------------------------------
void display(char charArray[], int arrSize)
{
    int i; // index number

        for(i = 0; i < 10; i++) // prints the first 10 characters of the array
            cout << charArray[i] << "   ";
            cout << "\n";
        for(i; i < arrSize; i++) // prints the next 10 characters of the array
            cout << charArray[i] << "   ";
            cout << "\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void search
//
// searches the array for a character using hashing (liner probing)
//*****************************************************************
//----------------------------------------------------------------------------------
void Search(char charArray[], int& pCount)
{
    bool isFound = false;
    char character;

    cout << "Enter a char you want to search (between A - Z): ";
    cin >> character;
    cout << endl;

    int position = HPosition(character);
    int count = 0;
    while(!isFound) {
        if(charArray[position] == character) {
            isFound = true;
        } else {
            position = RHPosition(position);
        }
        pCount++;
        count++;
        if(count >= 20) {
           break;
        }
    }

     if(isFound)
        cout<<"The char " << character << " was found at index "<< position << endl<<endl;
     else
        cout << "The char " << character << " was not found in the array\n\n";

}
//----------------------------------------------------------------------------------
//*****************************************************************
// void insert
//
// inserts a character into the array
//*****************************************************************
//----------------------------------------------------------------------------------
void Insert(char charArray[], int& pCount)
{
    bool isThere = false;
    char character;

    cout << "Enter a char you want to insert (between A - Z): ";
    cin >> character;
    cout << endl;

    int position = HPosition(character);
    int count = 0;

    while(!isThere) {
        if(charArray[position] == '*') {
            charArray[position] = character;
            isThere = true;
        } else {
            position = RHPosition(position);
        }
        pCount++;
        count++;
        if(count >= 20) {
           break;
        }
    }

    if(isThere){
        cout << "The char " << character  << " was inserted at index " << position << "\n\n";
    } else {
        cout << "Array is full \n\n";
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void Delete
//
// deletes a character from the array
//*****************************************************************
//----------------------------------------------------------------------------------
void Delete(char charArray[], int& pCount)
{
    bool isFound = false;
    char User_input;

    cout << "Enter a char you want to delete (between A - Z): ";
    cin >> User_input;
    cout << endl;

    int position = HPosition(User_input);
    int count = 0;
    while(!isFound) {
        if(charArray[position] == User_input) {
            charArray[position] = '*';
            isFound = true;
        } else {
            position = RHPosition(position);
        }
        pCount++;
        count++;
        if(count >= 20) {
           break;
        }
    }

    if(isFound){
        cout << User_input  << " is deleted from the array.\n\n";
    } else {
        cout << User_input  << " is not found in the array.\n\n";
    }
}
