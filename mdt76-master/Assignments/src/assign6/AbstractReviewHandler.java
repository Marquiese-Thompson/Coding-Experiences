package assign6;

import sentiment.Sentiment;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CS3354 Spring 2019 Review Handler Abstract Class specification
 * @author Marquiese Thompson
 * @author metsis
 * @author tesic
 * @author wen
 */
public abstract class AbstractReviewHandler {

    public AbstractReviewHandler() {
        database = new HashMap<>();
        sentimentModel = new Sentiment(new File(TRAINED_MODEL_NAME));
    }


    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     */
    public abstract void loadReviews(String filePath, int realClass);

    /**
     * Reads a single review file and returns it as a MovieReview object.
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be openned.
     */
    public abstract MovieReview readReview(String reviewFilePath, int realClass)
            throws IOException;



    /**
     * Classifies a review as negative, or positive by using the text of the review.
     * It updates the predictedPolarity value of the review object and it also
     * returns the predicted polarity.
     * Note: to achieve the classification, this method depends on the external
     * library sentiment.jar.
     * @param review A review object.
     * @return 0 = negative, 1 = positive.
     */
    public int classifyReview(MovieReview review) {
        int polarity = sentimentModel.classifyText(review.getText());
        review.setPredictedPolarity(polarity);
        return polarity;
    }


//----------------------------------------------------------------------------------------------------------------------
    public int counter = 0; // counter for the correctly classified reviews
    public int previous; // records the previous count of correctly classified reviews
    /**
     * ClassificationThread class
     * Using threadpooling and synchronizing ClassificationThread improves
     * the speed of sentiment.jar file in classifying each review
     * @author Marquiese Thompson
     */
    class ClassificationThread extends Thread {
        private MovieReview ReviewName; // local per thread MovieReview variable
        private int FolderSize; // Records folder size when assigned

        /**
         * Assigns the passed parameters to the local variables for that thread
         * @param name The MovieReview to be classified
         * @param folderSize THe folder size if the file is a file otherwise it will be 0
         */
        public ClassificationThread(MovieReview name, int folderSize) {
            this.ReviewName = name;
            this.FolderSize = folderSize;
        }


        /**
         * Increases the number of correctly classified reviews
         */
        public void CounterTracker() {
            if (ReviewName.getRealPolarity() != 2 && ReviewName.getRealPolarity() == ReviewName.getPredictedPolarity())
                counter = counter + 1;
        }

        /**
         * @return The Id of the current movie review
         */
        public int getReviewId() {
            return ReviewName.getId();
        }

        /**
         * Classifies each review in their own threads then places them
         * into the database and finally increases the correctly
         * classified counter when necessary. Displays the information
         * associated with a folder when the final review's id is one less
         * than the folder size.
         */
        public void run() {
            if (getReviewId() == ReviewName.getId()) {
                synchronized (ReviewName) {
                    SentimentAnalysisApp.outputArea.append("Executing : review " + ReviewName.getId() + "\n");
                    classifyReview(ReviewName);
                    CounterTracker();
                    //Add to database
                    database.put(ReviewName.getId(), ReviewName);
                    //waits for the last review to print the output
                    if (FolderSize%ReviewName.getId() == 1 || ReviewName.getId()%FolderSize == (FolderSize-1)) {
                        if (counter >= previous)
                        {
                            counter = counter - previous;
                        }
                        // Output result: folder
                        SentimentAnalysisApp.outputArea.append("Folder imported.\n");
                        SentimentAnalysisApp.outputArea.append("Number of entries: " + FolderSize + "\n");
                        SentimentAnalysisApp.log.info("Folder imported. Number of entries: " + FolderSize + "\n");
                        // Only output accuracy if real class is known
                        if (ReviewName.getRealPolarity() != 2) {
                            SentimentAnalysisApp.outputArea.append("Correctly classified: " + counter + "\n");
                            SentimentAnalysisApp.outputArea.append("Misclassified: " + (FolderSize - counter) + "\n");
                            SentimentAnalysisApp.outputArea.append("Accuracy: " + ((double)counter /
                                    ((double)(FolderSize)) * 100) + "%\n");
                        }
                        previous = counter;
                    }
                }
                yield();
            } else {
                // do nothing
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
    public abstract void deleteReview(int id);

    public void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        }
    }


    /**
     * Saves the database in the working directory as a serialized object.
     */
    public void saveSerialDB() {
        SentimentAnalysisApp.outputArea.append("Saving database...\n");
        //serialize the database
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = new FileOutputStream(DATA_FILE_NAME);
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);

            output.writeObject(database);

            output.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        } finally {
            close(file);
        }
        SentimentAnalysisApp.outputArea.append("Done.\n");

        SentimentAnalysisApp.log.info("Database saved.\n");
    }


    /**
     * Loads review database.
     */
    @SuppressWarnings("unchecked")
    public abstract void loadSerialDB();


    /**
     * Searches the review database by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not
     * exist in the database.
     */
    public abstract MovieReview searchById(int id);


    /**
     * Searches the review database for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    public abstract List<MovieReview> searchBySubstring(String substring);



    /**
     * A map of (id, review) pairs.
     */
    protected Map<Integer, MovieReview> database;

    /**
     * The file name of where the database is going to be saved.
     */
    protected static final String DATA_FILE_NAME = "database.ser";

    private static final String TRAINED_MODEL_NAME = "model.ser";

    private Sentiment sentimentModel;
}