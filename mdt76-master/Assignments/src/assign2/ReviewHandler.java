package Assignment2;

import java.util.*;
import java.io.*;
    //--------------------------------------------------------------------------------------
public class ReviewHandler extends AbstractReviewHandler
    {

    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     *
     * @param filePath  The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     *                  2 = Unknown).
     * @throws IOException
     */
    //--------------------------------------------------------------------------------------
    public void loadReviews(String filePath, int realClass) {
        //Find the last Key in Database
        boolean gil = true;
        int num = 1;
        while (gil == true)
        {
            if (database.size() == 0)
            {
                gil = false;
            }
            else if (database.containsKey(num))
            {
                gil = true;
                num++;
            }
            else
                {
                    gil = false;
                }
        }
        if (filePath.endsWith(".txt"))//Check for the File Path
        {
            //Key has been initialized and it is the ending of the database.ser reviews
            System.out.println("Key is " + num);
            //readReview Function returns the movie review object
            try {
                MovieReview movieRev = readReview(filePath, realClass);
                database.putIfAbsent(num, movieRev);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        //Handles Multiple Reviews
        else if (!filePath.endsWith(".txt"))
        {
            String folderPath;
            folderPath = filePath;
            File folder = new File(folderPath);
            String[] files = folder.list();
            String fileSeparatorChar = System.getProperty("file.separator");
            for (int i = 0; i<files.length; i++)
            {   num = i +1;
                String name = folderPath + fileSeparatorChar + files[i];
                System.out.println("File Name : "+name);
                Scanner inFile;
                String revs = "";
                try {
                    inFile = new Scanner(new FileReader(name));
                    while (inFile.hasNextLine()) {
                        revs += inFile.nextLine();
                    }
                } catch (FileNotFoundException fileNot)
                {
                    fileNot.printStackTrace();
                }

                revs = revs.replaceAll("<br />", "");
                revs = revs.replaceAll("\\p{Punct}", " ");
                revs = revs.toLowerCase();

                MovieReview movieRev = new MovieReview(num, revs, realClass);
                int predictedClass = classifyReview(movieRev);
                movieRev.setPredictedPolarity(predictedClass);
                database.putIfAbsent(num, movieRev);
            }
        }
        displayAccuracy(1);
        displayAccuracy(0);
    }
    //--------------------------------------------------------------------------------------
    /**
     * Reads a single review file and returns it as a MovieReview object.
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     *
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass      The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be openned.
     */
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException
    {
        Scanner inFile = null;
        try
        {
            inFile = new Scanner(new FileReader(reviewFilePath));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String  sentence = inFile.nextLine();

        sentence = sentence.replaceAll("<br />", "");
        sentence = sentence.replaceAll("\\p{Punct}", " ");
        sentence = sentence.toLowerCase();

        MovieReview movieRev = new MovieReview(0, sentence, realClass);
        int predictedPolarity = classifyReview(movieRev);
        movieRev.setPredictedPolarity(predictedPolarity);
        return movieRev;
    }
    //--------------------------------------------------------------------------------------
    public void printReviewID(MovieReview movieRev)
    {
        int id = movieRev.getId();
        System.out.println();

        System.out.println("Movie Review with id of " + id + " is :");
        String substring = movieRev.getText().substring(0, 74);
        System.out.print("--------------------------------------------------------------------------------------------------------------");
        System.out.print("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n|\tID\t|\t\t\t\tReview\t\t\t\t|\tReal Class\t|\tPredicted Class\t|\n");
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        String formatId = String.format("%03d", movieRev.getId());

        String mClass;
        if (movieRev.getRealPolarity() == 0)
        {
            mClass = "Negative";
        } else
            {
                mClass = "Positive";
            }
        String mClass2;
        if (movieRev.getPredictedPolarity() == 0)
        {
            mClass2 = "Negative";
        }
        else
            {
                mClass2 = "Positive";
            }
        System.out.println("|\t" + formatId + "\t|\t" + substring + "\t|\t" + mClass + "\t|\t" + mClass2 + "\t|");
        System.out.print("--------------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------------------------");
    }
    //--------------------------------------------------------------------------------------
    public int size()
    {
        return database.size();
    }
    //--------------------------------------------------------------------------------------
    private void displayAccuracy(int Class)
    {
        int reviews = 0;
        int correctRatings = 0;
        for (int i = 1; i <= database.size(); i++)
        {
            MovieReview movieRev = database.get(i);
            if (movieRev.getRealPolarity() == Class)
            {
                reviews++;
                if (movieRev.getPredictedPolarity() == Class)
                {
                    correctRatings++;
                }
            }
        }
        double accuracy = ((correctRatings * 100.00) / reviews);
        String variable = "null";
        if (Class == 0)
        {
            variable = "Negative";
        }
        if (Class == 1)
        {
            variable = "Positive";
        }
        System.out.println("Accuracy is: " + variable + " Review Is: " + accuracy);
    }
    //--------------------------------------------------------------------------------------
    /**
     * Deletes a review from the database, given its id.
     *
     * @param id The id value of the review.
     */
    public void deleteReview(int id)
    {
        MovieReview delete = database.get(id);
        if (delete == null)
        {
            System.out.println("Error: *** MovieReview With This ID Doesnt Exist ***");
        }
        else
        {
            System.out.println("Movie Review with ID " + id + " has been deleted ....");
            database.remove(id, delete);
        }
    }
    //--------------------------------------------------------------------------------------
    /**
     * Loads review database.
     *
     * @throws ClassNotFoundException
     */
    public void loadSerialDB()
    {
        System.out.print("Loading database...");
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try
        {
            file = new FileInputStream(DATA_FILE_NAME);
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            database = ((HashMap<Integer, MovieReview>) input.readObject());
            input.close();
        } catch (IOException | ClassNotFoundException ex)
        {
            System.err.println(ex.toString());
            ex.printStackTrace();
        }
        System.out.println("Loading finished!!");
    }
    //--------------------------------------------------------------------------------------
    /**
     * Searches the review database by id.
     *
     * @param IDnum The id to search for.
     * @return The review that matches the given id or null if not or id not exist
     *
     */
    public MovieReview searchById(int IDnum)
    {
        if (database.containsKey(IDnum))
        {
            MovieReview movieRev = database.get(IDnum);
            return movieRev;
        }
        else
            {
                return null;
            }
    }
    //--------------------------------------------------------------------------------------
    /**
     * Searches the review database for reviews matching a given substring.
     *
     * @param context The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    public List<MovieReview> searchBySubstring(String context)
    {
        context = context.replaceAll("<br />", "");
        context = context.replaceAll("\\p{Punct}", " ");
        context = context.toLowerCase();

        List<MovieReview> list = new ArrayList<MovieReview>();
        for (int i = 1; i <= database.size(); i++)
        {
            MovieReview movieRev = database.get(i);
            String text = movieRev.getText();
            boolean result = text.contains(context);
            if (result)
            {
                list.add(movieRev);
            }
        }
        return list;
    }
}