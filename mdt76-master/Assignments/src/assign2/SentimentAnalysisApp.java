package Assignment2;

import java.io.*;
import java.util.*;

/**
 *
 @author metsis
 @author tesic
 @author wen
 @author Marquiese Thompson
 @author Gilbert Velasquez
 */
public class SentimentAnalysisApp
{
    public static void main(String [] args) throws IOException
    {
        ReviewHandler reviewH = new ReviewHandler();
        Menu menu = new Menu();
        Cases cases = new Cases();

        String folderPath = System.getProperty("user.dir");
        File folder = new File(folderPath);
        String[] files = folder.list();
        String fileSeparatorChar = System.getProperty("file.separator");
        for (String fileName : files)
        {
            String name = folderPath + fileSeparatorChar + fileName;
            if(name.endsWith("database.ser"))
            {
                reviewH.loadSerialDB();
                break;
            }
        }
        boolean operating = true;
        while(operating == true)
        {
            Scanner user = new Scanner(System.in);
            try
            {
                int choice = menu.menu();
                switch(choice)
                {
                    case 0:
                        {
                        reviewH.saveSerialDB();
                        operating = false;
                        }
                    break;
                    case 1:
                        {
                        cases.one(reviewH,user);
                        }
                    break;
                    case 2:
                        {
                        cases.two(reviewH,user);
                        }
                    break;
                    case 3:
                        {
                        cases.three(reviewH,user);
                        }
                    break;
                    default:
                        {
                        System.err.println("\nERROR!!: Invalid Choice\n");
                        System.err.print("You can only choose 0-3!!\n\n\n");
                        }
                    break;
                }
            }
            catch(InputMismatchException ex)
            {
                System.err.println("\nERROR!!: Please Enter Numbers!! ");
            }
        }
    }
}