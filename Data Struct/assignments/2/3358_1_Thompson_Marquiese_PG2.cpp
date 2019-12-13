//  Author / s : Marquiese Thompson & Tim George
//  Serial Number / s : 66 & 14
//  Due Date  : 9.25.2019
//  Programming Assignment Number  2
//  Fall   2019   -   CS 3358    -    001
//
//  Instructor:    Husain Gholoom
//
//  This program implements 2D vector arrays and random number generators
//  to populate them. It also uses math operations to find the perfect
//  matrix by adding each number in a row and comparing it to each row and
//  each column.

#include <iostream>
#include <vector>
#include <time.h>
#include <cstdlib>
using namespace std;

//---------------------------------------------------------------------------------------
void Header();
void Footer();                                  // prototypes
bool isUsable(int user);
void populateMatrix(vector<vector<int> > &matrix, int user);
void runPerfectMatrix(vector<vector<int> > matrix);

//---------------------------------------------------------------------------------------
int main()
{
	Header();

	int USER_SIZE = 0;
	char USER_CHOICE = 'y';

	while(USER_CHOICE != 'N' && USER_CHOICE != 'n')
	{

		cout << "\nEnter the size of the matrix : ";
		cin >> USER_SIZE;
		cout << "\n\n";
		if (isUsable(USER_SIZE) == false)
			{
				cout << "\nERROR! Invalid size must be > 1 an < 11\n\n";
			}
		else
			{
				vector<int> vect; //creating a vector array
				vector<vector<int> > matrix(0, vect); // converting to a 2D vector array
				matrix.resize(USER_SIZE, vector <int>( USER_SIZE ) ); // resizing the array to the user's choice size


                populateMatrix(matrix, USER_SIZE);
                runPerfectMatrix(matrix);
			}

				cout << "\nWould you like to find another perfect matrix  \n-Enter y or Y for yes\n-Enter n or N for no: ";
				cin >> USER_CHOICE;
				cout << endl;

				if(USER_CHOICE != 'y' && USER_CHOICE != 'Y' && USER_CHOICE != 'N' && USER_CHOICE != 'n')
					{
					    cout << "\nERROR *** Invalid choice - Must enter y|Y or n|N\n";

                        cout << "\nWould you like to find another perfect matrix  \n-Enter y or Y for yes\n-Enter n or N for no: ";
                        cin >> USER_CHOICE;
					}

	};

	Footer();
	return 0;
}
//---------------------------------------------------------------------------------------
void Header()
{
    cout << "Welcome to the perfect matrix program. The function of the program is\n"<<
            "to:\n\n";

    cout << "\t1.\t Allow the user to enter the size of the perfect matrix, such as\n\t\t"<<
                    " N. N>=2." << endl;
    cout << "\t2.\t Create a 2 D vector array of a size N x N.\n";
    cout << "\t3.\t Populate a 2 D vector array with distinct random numbers.\n";
    cout << "\t4.\t Display the perfect number, sum for each row, column, and\n\t\t" <<
                    " diagonals then determine whether the numbers in N x N vector\n\t\t"<<
                    " array are perfect matrix numbers\n";
}
//---------------------------------------------------------------------------------------
void Footer()
{
	cout << "\nThis algorithm is implemented By Marquiese Thompson & Tim George\n";
    cout << "September 25 - 2019\n";
}
//---------------------------------------------------------------------------------------
//*****************************************************************
// bool isUsable
//
// Input validation for the user's chosen size.
//*****************************************************************
bool isUsable(int user)
{
    if (user < 2 || user > 10)
        return false;
    else
        return true;
}
//---------------------------------------------------------------------------------------
//*****************************************************************
// void populateMatrix
//
// Populates the matrix by the user's give dimensions with distinct
// random numbers.
//*****************************************************************
//---------------------------------------------------------------------------------------
void populateMatrix(vector<vector<int> > &matrix, int user)
{

    srand(time(0));

    for (int i = 0; i < matrix.size(); i++)
        for (int j = 0; j < matrix.size(); j++)
        {
            bool check;
            int random;
            do{
                random = (1 + rand()%(user * user));
                check = true;
                for (int m = 0; m < matrix.size(); m++)
                    for(int t = 0; t < matrix.size(); t++)
                        if (matrix[m][t] == random)
                    {
                        check = false;
                        break;
                    }
            }while(!check);
        matrix [i][j] = random; // populating the 2D array with random numbers
        }
}
//---------------------------------------------------------------------------------------

// void runPerfectMatrix
//
// Outputs the matrix, finds the perfect number of the matrix,
// finds all columns, rows and diagonal sums,
// and checks if matrix is a perfect matrix.
//*****************************************************************
void runPerfectMatrix(vector<vector<int> > matrix)
{
    cout  << endl << "The perfect matrix that is created for size " << matrix.size() << ": " << endl;

    int perfectNumber = 0; // perfect number starts from 0 and adds all the numbers of matrix
    bool isPerfectMatrix = true; // assumes matrix is perfect at start
                                 // then checks diagonals, rows & column sum
                                 // to see if it matched with perfectNumber

    for(int i = 0; i < matrix.size(); i++) {
        for(int j = 0; j < matrix[i].size(); j++) {
            perfectNumber += matrix[i][j];
            cout << matrix[i][j] << "\t";
        }
        cout << endl;
    }
    perfectNumber /= 3;

    cout  << endl << "The perfect number is: " << perfectNumber << " " << endl << endl;

    for(int i = 0; i < matrix.size(); i++) {
        int rowSum = 0; // sum of each row, hence why it's inside for loop
                        // resets out of inner loop
        for(int j = 0; j < matrix[i].size(); j++) {
            rowSum += matrix[i][j];
        }

        if(rowSum != perfectNumber)
            isPerfectMatrix = false;
        cout << "Sum of Row #" << (i + 1) << ": " << rowSum << endl;
    }

    cout << endl;

    for(int i = 0; i < matrix.size(); i++) {
        int columnSum = 0; // sum of each column, hence why it's inside for loop
                           // resets out of inner loop
        for(int j = 0; j < matrix[i].size(); j++) {
            columnSum += matrix[j][i];
        }

        if(columnSum != perfectNumber)
            isPerfectMatrix = false;
        cout << "Sum of Column #" << (i + 1) << ": " << columnSum << endl;
    }

    cout << endl;

    int diagonal1Sum = 0; // sum of diagonal from top left to bottom right
    int diagonal2Sum = 0; // sum of diagonal from top right to bottom left
    for(int i = 0; i < matrix.size(); i++) {
        for(int j = 0; j < matrix[i].size(); j++) {
            if(i == j) {
   diagonal1Sum += matrix[i][j];
  }
  if((i + j) == (matrix[i].size() - 1)) {
   diagonal2Sum += matrix[i][j];
  }
        }
    }

    if(diagonal1Sum != perfectNumber || diagonal2Sum != perfectNumber)
        isPerfectMatrix = false;

    cout << "Sum of Diagonal #1 " << diagonal1Sum << endl;
    cout << "Sum of Diagonal #2 " << diagonal2Sum << endl;

    cout << endl;

    if(isPerfectMatrix) {
        cout << "The above is a perfect matrix." << endl;
    } else {
        cout << "The above is not a perfect matrix." << endl;
    }

    cout << endl;
}
