//  Author / s : Marquiese Thompson & Tim George
//  Serial Number / s : 66 & 14
//
//  Due Date  : 10.21.2019
//  Programming Assignment Number  4
//  Fall   2019   -   CS 3358    -    001
//
//  Instructor:    Husain Gholoom
//
//  This program implements Stacks and Queues while utilizing Linked lists.

#include <iostream>
using namespace std;

class IntQueue {
    private:
        struct Node {
            int data; // int value for linked list
            Node *link; //gets next value
        };
        Node *head; //the head of the Node/LinkedList
        Node *tail; //the tail of the Node/LinkedList

    public:
        //-------------------------------------------------------
        //*************************************************
        // Constructor
        //
        // Creates an empty queue. Sets head & tail to nullptr
        //*************************************************
        //-------------------------------------------------------
        IntQueue() {
            head = NULL; //sets the head of the linkedlist to empty
            tail = NULL; //sets the tail of the linkedlist to empty
        }

        //-------------------------------------------------------
        //*************************************************
        // void enqueue
        //
        // adds a number to the back of the queue
        //*************************************************
        //-------------------------------------------------------
        void enqueue(int num) {
            Node* temp = new Node(); //creates temp node
            temp->data = num; //sets the value to the node
            temp->link = NULL; //sets next link to be empty

            if(isEmpty()){  //if the queue is empty
                head = temp; //set the head to the node
                tail = temp; //set the tail to the node
            } else { //otherwise
                tail->link = temp; //set the next link in tail to be node
                tail = temp;
            }
            cout << num << " has been inserted successfully." << endl;
        }

        //-------------------------------------------------------
        //*************************************************
        // void enqueueAtPosenqueueAtPos
        //
        // adds a number at a certain position in queue
        //*************************************************
        //-------------------------------------------------------
        void enqueueAtPos(int num, int oldPos) {
            int queueSize = getQueueSize(); //get size of queue
            int pos = oldPos - 1; //to convert to index of queue
            if(oldPos < 1) {
                cout << "Position must be greater than 1" << endl;
                return;
            } else if(oldPos > queueSize) {
                cout << "Position must be less than " << queueSize << endl;
                return;
            }

            Node *temp = head; //creates copy instance of head
            if(pos == 0) { //if position is the first element
                Node *newTemp = new Node(); //creates temp node and sets data
                newTemp->data = num;
                newTemp->link = temp;
                head = newTemp; //sets head to temp node if pos = 0
                return;
            }

            Node *prevTemp = NULL; //sets prev node to this var
            int index = 0; //count index for each position
            while(temp){
                if(index == pos) {
                    Node *newTemp = new Node(); //creates temp node and sets data
                    newTemp->data = num; //sets value for temp node
                    newTemp->link = temp; //passes to the next link
                    prevTemp->link = newTemp; //sets the previous temp to this node
                    break;
                }

                prevTemp = temp; //sets prevTemp to this temp
                temp = temp->link; // iterates to the next link
                index++; //increase index
            }
            cout << num << " has been inserted successfully." << endl;
        }

        //-------------------------------------------------------
        //*************************************************
        // void dequeue
        //
        // removes first element from queue
        //*************************************************
        //-------------------------------------------------------
        void dequeue() {
            if (isEmpty()) {
                cout << "Queue is empty" << endl;
                return;
            }

            if(head == tail) { //if head & tail is equal, its the last element
                head = NULL;
                tail = NULL;
            } else {
                head = head->link; //set head to the next link
            }
        }

        //-------------------------------------------------------
        //*************************************************
        // void remove
        //
        // removes specific element from queue
        //*************************************************
        //-------------------------------------------------------
        void remove(int value) {
            if (isEmpty()) {
                cout << "Queue is empty" << endl;
                return;
            }

            Node** temp = &head; //creates temp head
            while(*temp && (*temp)->data != value)
                temp = &(*temp)->link;

            if(*temp) {
                Node* dataNode = *temp; //creates temp node
                *temp = (*temp)->link; //moves to the next node
                delete dataNode;
                cout << "Element has been removed" << endl;
            } else {
                cout << "Element not found in queue!" << endl;
            }
        }


        //-------------------------------------------------------
        //*************************************************
        // void findStats
        //
        // gets the minimum number, maximum number
        // and the average of the queue
        //*************************************************
        //-------------------------------------------------------
        void findStats(int& minNum, int& maxNum, double& average) {
            if(isEmpty()){
                cout << "The queue is empty" << endl;
                return;
            }

            double sum = 0.0; //sum of queue to get avg
            int queueSize = 0; //size of queue
            minNum = head->data; //set a temp variable for min num
            maxNum = head->data; //set a temp variable for max num

            Node *temp = head; //create temp of head
            while(temp){
                int tempValue = temp->data; //gets the data value of node
                if(tempValue > maxNum)
                    maxNum = tempValue; //sets maxNum to the greatest val
                if(tempValue < minNum)
                    minNum = tempValue; //sets minNum to the smallest val

                sum += tempValue;  //adds up all sum
                temp = temp->link; //passes to the next node
                queueSize++; //increases queue size by 1
            }
            average = sum / queueSize; //gets the average of queue numbers
        }

        //-------------------------------------------------------
        //*************************************************
        // void display
        //
        // displays queue to user
        //*************************************************
        //-------------------------------------------------------
        void display() {
            if(isEmpty()){
                cout << "The queue is empty" << endl;
                return;
            }
            Node *temp = head; //creates temp of head
            while(temp){ //while temp is not null
                cout << temp->data << " "; //display node
                temp = temp->link; //passes to next node
            }
            cout << endl;
        }

        //-------------------------------------------------------
        //*************************************************
        // void eraseAll
        //
        // clears the entire queue
        //*************************************************
        //-------------------------------------------------------
        void eraseAll() {
            while(head != NULL) {
                Node *temp = head; //creates temp of head
                head = head->link; //passes to the next
                delete temp; //removes from memory
            }
            tail = NULL;
        }

         //-------------------------------------------------------
        //*************************************************
        // int getQueueSize
        //
        // gets the size of the queue
        //*************************************************
        //-------------------------------------------------------
        int getQueueSize() {
            int queueSize = 0; //size of queue

            Node *temp = head; //create temp of head
            while(temp){
                temp = temp->link; //passes to the next node
                queueSize++; //increases queue size by 1
            }
            return queueSize;
        }

        //-------------------------------------------------------
        //*************************************************
        // void isEmpty
        //
        // checks if queue is empty
        //*************************************************
        //-------------------------------------------------------
        bool isEmpty() {
            if(head == NULL) return true;
            return false;
        }
};
//----------------------------------------------------------------------------------
class IntStack {
    private:
         struct Node {
            int data; //int value of node
            Node *link; //next link of node
        };
        Node *top = NULL; //head of stack
    public:
        //-------------------------------------------------------
        //*************************************************
        // Constructor
        //
        // Creates an empty stack. The size parameter is the
        // size of the stack
        //*************************************************
        //-------------------------------------------------------
        IntStack() // Constructor
        {
            top = NULL; //sets top to null at constructor
        }
        //-------------------------------------------------------
        //*************************************************
        //void push
        //
        // pushes the argument onto the stack
        //*************************************************
        //-------------------------------------------------------
        void push(int num)
        {
            Node* temp = new Node(); //creates temp node
            temp->data = num; //sets data to num provided
            temp->link = top;

            top = temp; //top would be the last node (LIFO)

            cout << temp->data << " has been inserted successfully. " << endl;
        }
        //-------------------------------------------------------
        //*************************************************
        // int pop
        //
        // pops the value at the top of the stack off, and
        // copies it into the variable passed as an argument
        //*************************************************
        //-------------------------------------------------------

        void pop()
        {
            if(isEmpty()) {
                cout << "Stack is Empty.\n";
            } else {
                Node *temp = top; //creates temp of top
                top = top->link; //passes on to the next node
                delete(temp);
            }
        }
        //-------------------------------------------------------
        //*************************************************
        // bool isEmpty
        //
        //returns true if the stack is empty, or false otherwise
        //*************************************************
        //-------------------------------------------------------

        bool isEmpty()
        {
            if(top == NULL)
                return true;
            else
                return false;
        }

        //-------------------------------------------------------
        //*************************************************
        // void makeEmpty
        //
        // clears out the stack
        //*************************************************
        //--------------------------------------------------------

        void makeEmpty()
        {
            top = NULL; //sets top to null
        }

        //--------------------------------------------------------
        //*************************************************
        // void display
        //
        // shows the contents of the stack
        //*************************************************
        //--------------------------------------------------------

        void display() {
            if(top == NULL){
                cout << "The Stack is empty" << endl;
                return;
            }
            Node *temp = top; //creates temp instance of top
            while(temp){
                cout << temp->data << " ";
                temp = temp->link; //passes on the next in stack
            }
            cout << endl;
        }

    };
//----------------------------------------------------------------------------------
void Header(); //outputs header of program
void Footer(); // shows footer of program
void addToQueue(IntQueue&); //ask prompt & add to queue
void addToStack(IntStack&); //asks prompt & adds to stack
void eraseStack(IntStack&); //erases entire stack
void removeFromStack(IntStack&); //remove from stack
void addToQueueAtPosition(IntQueue&); //asks prompts & adds to queue
void removeFromQueue(IntQueue&); //dequeue from queue
void removeElementFromQueue(IntQueue&); //remove specific element from queue
void getQueueStats(IntQueue&); //get queue min, max & avg
void eraseQueue(IntQueue&); //erase entire queue
//----------------------------------------------------------------------------------
int main()
{
    string user = "0"; // user choice to enter the loop
    IntQueue intQueue; //creates queue instance
    IntStack intStack; //creates stack instance

	do
	{
        Header();

        cout << "\nYour choice ---> ";
        cin >> user;

        if(user == "A") // choice A
        {
            addToStack(intStack);
        }
        else if (user == "B") // choice B
        {
            removeFromStack(intStack);
        }
        else if (user == "C") // choice C
        {
            addToQueue(intQueue);
        }
        else if (user == "D") // choice D
        {
            addToQueueAtPosition(intQueue);
        }
        else if (user == "E") // choice E
        {
            removeFromQueue(intQueue);
        }
        else if (user == "F") // choice F
        {
            removeElementFromQueue(intQueue);
        }
        else if (user == "G") // choice G
        {
            getQueueStats(intQueue);
        }
        else if (user == "H") // choice H
        {
            eraseStack(intStack);
        }
        else if (user == "I") // choice I
        {
            eraseQueue(intQueue);
        }
        else if (user == "X") // exit choice
        {
            break; // ends loop
        }
        else
        {
            cout << "\n******* Invalid selection\n"; // input validation
            cout << "Only capital letters are accepted.\n\n";
        }
	} while(user != "X");

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
    cout << "Menu options: \n\n";
    cout << "A.\tInsert new element in the stack ( LIFO ).\n";
    cout << "B.\tRemove an element from the stack ( LIFO ).\n";
    cout << "C.\tInsert new element in the queue ( FIFO ).\n";
    cout << "D.\tInsert an element at specific location in the queue.\n";
    cout << "E.\tRemove an element from the queue ( FIFO )\n";
    cout << "F.\tRemove a specific element from the queue\n";
    cout << "G.\tFind Min element in the queue, Max element in the\n";
            cout << "\tqueue, and the Average of the queue.\n";
    cout << "H.\tDelete the entire stack.\n";
    cout << "I.\tDelete the entire queue.\n";
    cout << "X.\tExit the program.\n";
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
	cout << "\nThis LL / Stack & Queue Program is implemented By :\n";
	cout <<  "Tim George & Marquiese Thompson - October 21st, 2019\n";
}

//----------------------------------------------------------------------------------
//*****************************************************************
// void addToQueue
//
// asks user what number would they like added to queue
// then adds element to the queue
//*****************************************************************
//----------------------------------------------------------------------------------
void addToQueue(IntQueue& intQueue)
{
    int num; //int for user input on what element they want added to queue
    cout << "What number would you like add to the Queue?\n";
    cout << "-----> ";
    cin >> num;

    intQueue.enqueue(num);
    intQueue.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void addToStack
//
// adds an element to the stack
//*****************************************************************
//----------------------------------------------------------------------------------
void addToStack(IntStack& intStack)
{
    int user; // user input
    cout << "\nWhat number would you like add to the Stack?\n";
    cout << "-----> ";
    cin >> user;

    intStack.push(user);
    intStack.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void removeFromStack
//
// pops and element out of the stack
//*****************************************************************
//----------------------------------------------------------------------------------
void removeFromStack(IntStack& intStack)
{
    intStack.pop();
    intStack.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void eraseStack
//
// clears every element from the stack
//*****************************************************************
//----------------------------------------------------------------------------------
void eraseStack(IntStack& intStack)
{
    intStack.makeEmpty();
    intStack.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// addToQueueAtPosition
//
// adds an element to the queue at a specific position
//*****************************************************************
//----------------------------------------------------------------------------------
void addToQueueAtPosition(IntQueue& intQueue)
{
    int num; //int for user input on what element they want added to queue
    cout << "What number would you like add to the Queue?\n";
    cout << "-----> ";
    cin >> num;

    int pos; //int for user input on what position they want it added to
    cout << "What position would you like to add it to? (Starting from 1)\n";
    cout << "-----> ";
    cin >> pos;

    intQueue.enqueueAtPos(num, pos);
    intQueue.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void removeFromQueue
//
// removes an element from the queue
//*****************************************************************
//----------------------------------------------------------------------------------
void removeFromQueue(IntQueue& intQueue)
{
    intQueue.dequeue();
    intQueue.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void removeElementFromQueue
//
// removes a specific element form the queue
//*****************************************************************
//----------------------------------------------------------------------------------
void removeElementFromQueue(IntQueue& intQueue)
{
    int num; //int for user input on what element they want removed from the queue
    cout << "What number would you like remove from the Queue?\n";
    cout << "-----> ";
    cin >> num;

    intQueue.remove(num);
    intQueue.display();
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void getQueueStats
//
// shows the maximumn minimum and average numbers from the queue
//*****************************************************************
//----------------------------------------------------------------------------------
void getQueueStats(IntQueue& intQueue)
{
    if(intQueue.isEmpty()) {
        cout << "Queue is empty, no stats can be found\n";
    } else {
        int minNum; //int var for min num
        int maxNum; //int var for max num
        double avg; //double var for avg
        intQueue.findStats(minNum, maxNum, avg);
        cout << "Minimum Number: " << minNum << endl;
        cout << "Maximum Number: " << maxNum << endl;
        cout << "Average: " << avg << endl;
    }
}
//----------------------------------------------------------------------------------
//*****************************************************************
// void eraseQueue
//
// clears out the elements in the queue
//*****************************************************************
//----------------------------------------------------------------------------------
void eraseQueue(IntQueue& intQueue)
{
    intQueue.eraseAll();
    intQueue.display();
}
