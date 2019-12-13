package Assignment2;

import java.util.Scanner;

//--------------------------------------------------------------------------------------
public class Menu
{
    /**
    *Display Menu
    *
    * @return users menu Choice
     */
    public int menu()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\nHello, what would you like to do?\n");
        System.out.println("0. Exit program.");
        System.out.println("1. Load new movie review collection (given a folder or a file path).");
        System.out.println("2. Delete movie review from database (given its id).");
        System.out.println("3. Search movie reviews in database by id or by matching a substring");

        System.out.print("Choice: ");
        int choice = sc.nextInt();

        return choice;
    }
}
