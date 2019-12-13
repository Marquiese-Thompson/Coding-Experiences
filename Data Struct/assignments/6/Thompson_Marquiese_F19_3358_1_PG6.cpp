// Author / s : Marquiese Thompson & Tim George
// Serial Number / s : 66 & 14
//
// Due Date  : 11.20.2019
// Programming Assignment Number  6
// Fall   2019   -   CS 3358    -    001
//
// Instructor:    Husain Gholoom
//
// This program implements searching and sorting algorithms.
// It generates n elements in an array and does various types
// of searching and sorting algorithms as well as the CPU clock
// time for each method.

#include <iostream>
#include <time.h>
#include <cstdlib>
using namespace std;

void Header(); //outputs header of program
void Footer(); // shows footer of program

void populate(char[], int); // populates the array up to the user desired size
void display(char[], int); // displays the array up to the 20th position
bool isValidSize(int); // returns true if size > 0
void seqSearch(char[], int, int&, int, bool&); // recursive sequential search
void selectSort(char[], int, int&); //selection sort for array
void insertSort(char[], int, int&); //insertion sort for array
void quickSortMiddleValue(char[], int, int, int&); //quicksort with next to middle value as pivot
void quickSortFirstValue(char[], int, int, int&); //quicksort with first element as pivot
void quickSortLastValue(char[], int, int, int&); //quicksort with last element as pivot
void results(clock_t, clock_t, int, char[], string, int); //shows results of function
void resultsForSeq(clock_t, clock_t, int, char[], int); //shows result of sequential search function


//----------------------------------------------------------------------------------
int main()
{
    Header();

    int arrSize; // user array size
    cout << "\nEnter the size of the array : ";
    cin >> arrSize;
    cout << "\n\n";

    if(isValidSize(arrSize))
    {
        char charArray[arrSize]; // array with the chosen size
        populate(charArray, arrSize); // populates the array

        cout << "Array elements are : ";
        display(charArray, arrSize); // shows the array

        char selectSortArr[arrSize]; //copy of array for selection sort
        char insertSortArr[arrSize]; //copy of array for insert sort
        char quickSortArr1[arrSize]; //copy of array for quick sort function 1
        char quickSortArr2[arrSize]; //copy of array for quick sort function 2
        char quickSortArr3[arrSize]; //copy of array for quick sort function 3

        for(int i = 0; i < arrSize; i++) {
            selectSortArr[i] = charArray[i]; //adds elements from original to new
            insertSortArr[i] = charArray[i]; //adds elements from original to new
            quickSortArr1[i] = charArray[i]; //adds elements from original to new
            quickSortArr2[i] = charArray[i]; //adds elements from original to new
            quickSortArr3[i] = charArray[i]; //adds elements from original to new
        }

        clock_t start = clock(); //start time for function
        int comparisons = 0; //number of comparisons for sequential search
        bool found = false; //if 80 is found in array

        cout << "\n\nSequential Search\n\n";
        cout << "Searching for 80\n\n";
        seqSearch(charArray, 0, comparisons, arrSize, found);
        clock_t endTime = clock(); //the time it finished the function
        resultsForSeq(start, endTime, comparisons, charArray, arrSize);

        start = clock(); //start time for function
        int swaps = 0; //number of swaps done
        cout << "\n\n\nSelection Sort :\n\n";
        selectSort(selectSortArr, arrSize, swaps);
        endTime = clock();//the time it finished the function
        results(start, endTime, swaps, selectSortArr, "Total Number of Swaps : ", arrSize);

        start = clock(); //start time for function
        comparisons = 0; //number of comparisons done
        cout << "Insertion Sort :\n\n";
        insertSort(insertSortArr, arrSize, comparisons);
        endTime = clock();//the time it finished the function
        results(start, endTime, comparisons, insertSortArr, "Total Number of Comparisons : ", arrSize);

        start = clock(); //start time for function
        cout << "Quick  Sort - Next to the middle element as a pivot :\n\n";
        int recursionCount = 0; //number of recursive calls
        quickSortMiddleValue(quickSortArr1, 0, arrSize - 1, recursionCount);
        endTime = clock();//the time it finished the function
        results(start, endTime, recursionCount, quickSortArr1, "Number of recursive calls: ", arrSize);

        start = clock(); //start time for function
        cout << "Quick  Sort - First element as a pivot :\n\n";
        recursionCount = 0; //number of recursive calls
        quickSortFirstValue(quickSortArr2, 0, arrSize - 1, recursionCount);
        endTime = clock();//the time it finished the function
        results(start, endTime, recursionCount, quickSortArr2, "Number of recursive calls: ", arrSize);

        start = clock(); //start time for function
        cout << "Quick  Sort - last element as a pivot :\n\n";
        recursionCount = 0; //number of recursive calls
        quickSortLastValue(quickSortArr3, 0, arrSize - 1, recursionCount);
        endTime = clock();//the time it finished the function
        results(start, endTime, recursionCount, quickSortArr3, "Number of recursive calls: ", arrSize);
    }
    else
    {
        cout << "*** Error - Invalid input - Size must be > 0 ***\n";
    }


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
    cout << "Searching / Sorting Benchmark\n\n\n";

    cout << "Using a random number generator, we are creating an array of n\n";
    cout << "element of type char then performing the following :\n\n";

    cout << "\t1. Displaying the first 20 numbers.\n\n";

    cout << "\t2. Using recursion, Searching for an element in the array using\n";
    cout << "\t   sequential search and at the end displaying number of comparisons\n";
    cout << "\t   it makes.\n\n";

    cout << "\t3. Sort the original array using Selection Sort  and at the end \n";
    cout << "\t   display the number of swaps it makes.\n\n";

    cout << "\t4. Sorting the array using insertion  Sort  and at the end \n";
    cout << "\t   displaying  the number of comparisons it makes.\n\n";

    cout << "\t5. Sorting the array using Quick Sort  and at the end displaying \n";
    cout << "\t   the number of recursion calls  it makes. Using the next to the \n";
    cout << "\t   middle  value  as a pivot value.\n\n";

    cout << "\t6. Sorting the same array using Quick Sort  and at the end\n";
    cout << "\t   displaying  the number of recursion calls  it makes. Using the\n";
    cout << "\t   first value  as a pivot value.\n\n";

    cout << "\t7. Sorting the same array using Quick Sort  and at the end \n";
    cout << "\t   displaying the number of recursion calls  it makes. Using the \n";
    cout << "\t   last value  as a pivot value. \n\n";

    cout << "\t8. For each of the preceding steps ( 2 thru 7 ), calculating and \n";
    cout << "\t   printing the CPU time before each step starts and after each \n";
    cout << "\t   completed step then calculating actual CPU time for the\n";
    cout << "\t   completion of each step.\n\n";
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
	cout << "\n\n\n11-20-2019\n";
	cout << "Benchmark Algorithm Implemented by :\n";
	cout << "Tim George & Marquiese Thompson\n";
}
//----------------------------------------------------------------------------------
//*****************************************************************
// bool isValid
//
// returns true if the size is an acceptable number
//*****************************************************************
//----------------------------------------------------------------------------------
bool isValidSize(int arrSize)
{
    if(arrSize > 0)
        return true;
    else
        return false;
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void populate
//
// populates the array with random characters up to the user size
//*****************************************************************
//----------------------------------------------------------------------------------
void populate(char charArray[], int arrSize)
{
    srand(time(NULL));
    for(int i = 0; i < arrSize; i++)
        charArray[i] = 33 + (rand() % 93); // adds random numbers to fill the array
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
    if(arrSize >= 20)
    {
        for(int i = 0; i < 20; i++)
            cout << charArray[i] << " ";
    }
    else
    {
        for(int i = 0; i < arrSize; i++)
            cout << charArray[i] << " ";
    }
}
//----------------------------------------------------------------------------------
//****************************************************************
// void seqSearch
//
// recursive function that searches for 80 sequentially
// through the array
//*****************************************************************
//----------------------------------------------------------------------------------
void seqSearch(char charArray[], int index, int& comparisons, int arrSize, bool& found)
{
    if(index < arrSize && found == false)
    {
        if(charArray[index] == '80')
        {
            found = true; //found the element 80 in array
            cout << "80 was found.\n";
        }
        else
        {
            comparisons++; //increment comparison count by 1
            seqSearch(charArray, (index+1), comparisons, arrSize, found);
        }
    }
    else if(index == arrSize && found == false)
    {
        cout << "80 was not found.\n";
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void selectSort
//
// does a selection sort through array and it also
// counts the number of swaps done
//*****************************************************************
//----------------------------------------------------------------------------------
void selectSort(char sortedArray[], int arrSize, int& swaps)
{
    for (int i = 0; i < arrSize - 1; i++)
    {
        int minNum = i; //min number in array
        for (int j = i + 1; j < arrSize; j++)
        {
            if (sortedArray[j] < sortedArray[minNum])
                minNum = j;
        }

        if (i != minNum)
        {
            int tmp = sortedArray[i]; //temp var to be swapped
            sortedArray[i] = sortedArray[minNum];
            sortedArray[minNum] = tmp;
            swaps++;
        }
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void insertSort
//
// does an insertion sort through array and it also
// counts the number of comparisons done
//*****************************************************************
//----------------------------------------------------------------------------------
void insertSort(char sortedArray[], int arrSize, int& comparisons)
{
    for(int i = 1; i < arrSize; i++) {
        int element = sortedArray[i]; //value from array at index i
        int index = i; //hold the index var
        while(index > 0 && sortedArray[index - 1] > element)
        {
            sortedArray[index] = sortedArray[index-1];
            index--;
            comparisons++;
        }
        sortedArray[index] = element;//insert in right place
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void quickSortMiddleValue
//
// does a quick sort through array with
// pivot as next to middle value
// increases recursion count each time method is called
//*****************************************************************
//----------------------------------------------------------------------------------
void quickSortMiddleValue(char charArray[], int left, int right, int& recursionCount)
{
    recursionCount++; //increases recursion count by 1
    int i = left, j = right; //sets i and j to left, right respectively, to loop
    int pivot = charArray[((left + right) / 2) + 1]; //sets the pivot to next to middle value

    while (i <= j) {
        while (charArray[i] < pivot)
            i++;
        while (charArray[j] > pivot)
            j--;
        if (i <= j) {
            int tmp = charArray[i]; //temp value to swap
            charArray[i] = charArray[j];
            charArray[j] = tmp;
            i++;
            j--;
        }
    }

    if (left < j)
        quickSortMiddleValue(charArray, left, j, recursionCount);
    if (i < right)
        quickSortMiddleValue(charArray, i, right, recursionCount);
}

//----------------------------------------------------------------------------------
//*****************************************************************
// void quickSortFirstValue
//
// does a quick sort through array with
// pivot as first element
// increases recursion count each time method is called
//*****************************************************************
//----------------------------------------------------------------------------------
void quickSortFirstValue(char charArray[], int left, int right, int& recursionCount)
{
    recursionCount++; //increases recursion count by 1
    int i = left, j = right; //sets i and j to left, right respectively to loop
    int pivot = charArray[left]; //sets the pivot to the first value

    while (i <= j) {
        while (charArray[i] < pivot)
            i++;
        while (charArray[j] > pivot)
            j--;
        if (i <= j) {
            int tmp = charArray[i]; //temp value to swap
            charArray[i] = charArray[j];
            charArray[j] = tmp;
            i++;
            j--;
        }
    }

    if (left < j)
        quickSortFirstValue(charArray, left, j, recursionCount);
    if (i < right)
        quickSortFirstValue(charArray, i, right, recursionCount);
}

//----------------------------------------------------------------------------------
//*****************************************************************
// void quickSortLastValue
//
// does a quick sort through array with
// pivot as last element
// increases recursion count each time method is called
//*****************************************************************
//----------------------------------------------------------------------------------
void quickSortLastValue(char charArray[], int left, int right, int& recursionCount)
{
    recursionCount++; //increases recursion count by 1
    int i = left, j = right; //sets i and j to left, right respectively to loop
    int pivot = charArray[right]; //sets the pivot to the last value

    while (i <= j) {
        while (charArray[i] < pivot)
            i++;
        while (charArray[j] > pivot)
            j--;
        if (i <= j) {
            int tmp = charArray[i]; //temp value to swap
            charArray[i] = charArray[j];
            charArray[j] = tmp;
            i++;
            j--;
        }
    }

    if (left < j)
        quickSortLastValue(charArray, left, j, recursionCount);
    if (i < right)
        quickSortLastValue(charArray, i, right, recursionCount);
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void results
//
// shows the time results, the sorted array contents,
// and # of recursive calls or # of swaps
//*****************************************************************
//----------------------------------------------------------------------------------
void results(clock_t startTime, clock_t endTime, int value, char sortedArray[], string message, int arrSize)
{
    double timeDiff = (endTime - startTime) / (CLOCKS_PER_SEC / 1000); //difference between time

    cout << "Start Time : " << startTime << " ms" << endl;
    cout << "End Time : " << endTime << " ms" << endl;
    cout << "Actual CPU Clock time : " << timeDiff << " ms" << endl;
    cout << message << value << endl;
    cout << "Sorted Elements : ";
    display(sortedArray, arrSize);
    cout << "\n\n\n";
}

//----------------------------------------------------------------------------------
//*****************************************************************
// void resultsForSeq
//
// shows the time results for the sequential search,
// the array contents and # of comparisons
//*****************************************************************
//----------------------------------------------------------------------------------
void resultsForSeq(clock_t startTime, clock_t endTime, int value, char charArray[], int arrSize)
{
    double timeDiff = (endTime - startTime) / (CLOCKS_PER_SEC / 1000); //difference between time
    cout << "Start Time : " << startTime <<  " ms" << endl;
    cout << "End Time : " << endTime <<  " ms" << endl;
    cout << "Actual CPU Clock time : " << timeDiff << " ms" << endl;
    cout << "Total Number of Comparisons : " << value << endl;
    cout << "Array Elements : ";
    display(charArray, arrSize);
}
