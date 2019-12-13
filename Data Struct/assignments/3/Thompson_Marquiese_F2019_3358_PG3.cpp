//  Author / s : Marquiese Thompson & Tim George
//  Serial Number / s : 66 & 14
//
//  Due Date  : 10.07.2019
//  Programming Assignment Number  3
//  Fall   2019   -   CS 3358    -    001
//
//  Instructor:    Husain Gholoom
//
//  This program implements class / objects to present a vending machine.
//  The program sets the price and quantity of each item. The quantity of
//  each item is lowered after payment is received and is sufficient. The
//  program also checks the users input for a valid selection and displays
//  the proper message is it is not valid.

#include <iostream>
using namespace std;

class DispenserType {
    private:
        int quantity; // number of items in dispenser
        int cost; //cost of 1 item in dispenser

    public:
        //--------------------------------------------------//
        //**************************************************//
        // DispenserType(int q, int c)
        //
        // initializes constructor with quantity
        // and cost of items
        //**************************************************//
        //--------------------------------------------------//
        DispenserType(int q, int c)
        {
            quantity = q;
            cost = c;
        }

        //--------------------------------------------------//
        //**************************************************//
        // int getQuantity
        //
        // returns the quantity of item
        //**************************************************//
        //--------------------------------------------------//
        int getQuantity() {
             return quantity;
        }

        //--------------------------------------------------//
        //**************************************************//
        // int getCost
        //
        // returns the cost of the item
        //*************************************************//
        //-------------------------------------------------//
        int getCost() {
             return cost;
        }

        //--------------------------------------------------//
        //**************************************************//
        // void reduceAmount
        //
        // deducts 1 item from dispenser
        //*************************************************//
        //-------------------------------------------------//
        void reduceAmount() {
            if(quantity > 0) {
                quantity -= 1;
            }
        }
};
//---------------------------------------------------------------------------------------
class Register {
    private:
        int cash; //amt of cash in cash register

    public:
        //--------------------------------------------------//
        //**************************************************//
        // Register()
        //
        // initializes constructor with default of 500 coins
        //**************************************************//
        //--------------------------------------------------//
        Register(){ cash = 500; }

        //--------------------------------------------------//
        //**************************************************//
        // Register(int amt)
        //
        // initializes constructor with amt provided
        //**************************************************//
        //--------------------------------------------------//
        Register(int amt){ cash = amt; }

         //--------------------------------------------------//
        //**************************************************//
        // int getCash
        //
        // returns the total cash in register
        //*************************************************//
        //-------------------------------------------------//
        int getCash() {return cash;}


         //--------------------------------------------------//
        //**************************************************//
        // void update
        //
        // updates the cash in the register
        //*************************************************//
        //-------------------------------------------------//
        void update(int deposit)
        {
            cash = cash + deposit;
        }
};
//---------------------------------------------------------------------------------------
void Header(); //outputs header of program
void Footer(); // shows footer of program
void buyProduct(Register* reg, DispenserType* product, int userPrice); //buys the actual product
void selection(Register* reg, DispenserType* product); //get users selection for product
//---------------------------------------------------------------------------------------
int main()
{
	Header();

    DispenserType candy(2, 50);
    DispenserType chips(5, 75);            // assigning a name, quantity,
    DispenserType gum(3, 45);              // and price to each item.
    DispenserType cookies(1, 80);

    Register reg; //create register class instance
    Register* registerPtr = &reg; //pointer to register to update variables


    string user = "0"; // user choice to enter the loop

	do
	{
		cout << "\nSelect one of the following\n\n";

        cout <<"\t1 for Candy\n";
        cout <<"\t2 for Chips\n";
        cout <<"\t3 for Gum\n";         // print out the options here
        cout <<"\t4 for Cookies\n";
        cout <<"\t9 to exit\n";

        cin >> user;

        if(user == "1") // candy choice
        {
            DispenserType* productSelected = &candy; //set product selection to candy dispenser
            selection(registerPtr, productSelected);
        }
        else if (user == "2") // chips choice
        {
            DispenserType* productSelected = &chips; //set product selection to chips dispenser
            selection(registerPtr, productSelected);
        }
        else if (user == "3") // gum choice
        {
            DispenserType* productSelected = &gum; //set product selection to gum dispenser
            selection(registerPtr, productSelected);
        }
        else if (user == "4") // cookies choice
        {
            DispenserType* productSelected = &cookies; //set product selection to cookies dispenser
            selection(registerPtr, productSelected);
        }
        else if (user == "9") // exit choice
        {
            break;
        }
        else
        {
            cout << "Invalid selection\n";
        }
	} while(user != "9");

	Footer();
	return 0;
}
//---------------------------------------------------------------------------------------
//*****************************************************************
// void Header
//
// displays the proper header message
//****************************************************************
//---------------------------------------------------------------------------------------
void Header()
{
    cout << "*** Welcome to Tim & Marquiese's Vending Machine ***\n";
}
//---------------------------------------------------------------------------------------
//*****************************************************************
// void Footer
//
// displays the proper footer message
//*****************************************************************
//---------------------------------------------------------------------------------------
void Footer()
{
	cout << "\n*** Thank you for using Tim & Marquiese's Vending Machine ***\n";
}
//---------------------------------------------------------------------------------------
//*****************************************************************
//void buyProduct
//
// Reduces the amount of the product after user inputs payment.
// Adds money to cash register as well as gets change
//*****************************************************************
//---------------------------------------------------------------------------------------
void buyProduct(Register* reg, DispenserType* product, int userPrice)
{
    cout << endl << "Collect your item at the bottom and enjoy." << endl;

    (*reg).update(userPrice);

     if(userPrice > (*product).getCost()) {
        int change = userPrice - (*product).getCost(); //calculate change from what user deposited from item cost
        cout << "Collect your change " << change << endl;

        int withdraw = (0 - change); //get negative value to subtract from cash register
        (*reg).update(withdraw);
    }

    (*product).reduceAmount();
}
//---------------------------------------------------------------------------------------
//*****************************************************************
//void selection
//
//First checks if the product is still available and if so, allows
//the user to pay. If it is not the proper message is displayed.
//The user is also give a second chance to finish payment.
//*****************************************************************
//---------------------------------------------------------------------------------------
void selection(Register* reg, DispenserType* product)
{
    if((*product).getQuantity() > 0) {
        cout << "Please deposit " << (*product).getCost() << " cents " << endl;
        int userDeposit = 0; //get user's deposit
        cin >> userDeposit;

        if(userDeposit >= (*product).getCost()) {
             buyProduct(reg, product, userDeposit);
        } else {
            cout << "Please deposit another " << ((*product).getCost() - userDeposit) << " cents " << endl;
            int newDeposit = 0; //get extra deposit from user if not the right amount
            cin >> newDeposit;
            if((newDeposit + userDeposit) >= (*product).getCost()) {
                buyProduct(reg, product, newDeposit + userDeposit);
            } else {
                cout << endl;
                cout << "The amount is not enough. Collect what you deposited." << endl;
            }
        }
    } else {
        cout << endl;
        cout << "Sorry, this item is sold out." << endl;
    }

    cout << "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" << endl;
}
