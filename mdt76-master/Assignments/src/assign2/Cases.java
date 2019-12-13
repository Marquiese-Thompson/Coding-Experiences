package Assignment2;

import java.util.Scanner;
    //--------------------------------------------------------------------------------------
/**
 * @author Marquiese Thompson
 * @author Gilbert Velasquez
 */
public class Cases {
    //--------------------------------------------------------------------------------------
        /**
         * Load new movie review collection (given a folder or a file path)
         *
         * @param reviewH allows the user to access the ReviewHandler class
         * @param user the users input through
         */
    public void one(ReviewHandler reviewH, Scanner user)
    {
        System.out.println("\nEnter Path Of Reviews");
        System.out.print("Path : ");
        String path = user.nextLine();
        boolean marq = true;
        while(marq) {
            System.out.println("Enter Real Class of Reviews:\n0. Negative\n1. Positive\n2. Unknown");
            System.out.print("Choice ------> ");
            int realClass = user.nextInt();
            if(realClass == 0 || realClass == 1||realClass == 2)
            {
                reviewH.loadReviews(path, realClass);
                marq = false;
            }
            else
            {
                System.err.println("ERROR: Incorrect Real Class, Enter 0 or 1 or 2 ");
            }
        }
    }
    //--------------------------------------------------------------------------------------
        /**
         * Delete movie review from database (given its id).
         *
         * @param reviewH allows the user to access the ReviewHandler class
         * @param user the users input through
         */
    public void two(ReviewHandler reviewH, Scanner user)
    {
        if(reviewH.size() == 0)//checks the database to see if its empty and informs the user
        {
            System.out.println("Database is empty!");
        }
        else
        {
            System.out.print("\nEnter ID Number to Delete: ");
            int id = user.nextInt();
            reviewH.deleteReview(id);
        }
    }
    //--------------------------------------------------------------------------------------
        /**
         * Search movie reviews in database by id or by matching a substring.
         *
         * @param reviewH allows the user to access the ReviewHandler class
         * @param user the users input through
         */
    public void three(ReviewHandler reviewH, Scanner user)
    {
        if(reviewH.size() == 0)//checks the database to see if its empty and informs the user
        {
            System.out.println("\nDatabase is empty!");
        }
        System.out.print("\nEnter ID Number of movie review: ");
        int id = user.nextInt();
        MovieReview movieRevs = reviewH.searchById(id);
        if(movieRevs == null)
        {
            System.out.println("\nError: Movie Review with this ID Was Not Found!! ");
        }
        else
        {
            reviewH.printReviewID(movieRevs);
        }
    }
    //--------------------------------------------------------------------------------------
}
