package assign6;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CS3354 Spring 2019 Review Handler Class assignment 2 solution
 @author metsis
 @author tesic
 @author wen
 */
public class ReviewHandler extends AbstractReviewHandler {

    private static int ID = 0;

    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     */
    @Override
    public void loadReviews(String filePath, int realClass) {

        File fileOrFolder = new File(filePath);
        try {
            if (fileOrFolder.isFile()) {
                // File
                if (filePath.endsWith(".txt")) {
                    // Import review
                    MovieReview review = readReview(filePath, realClass);
                    SentimentAnalysisApp.outputArea.append("Created : review "+ review.getId() + "\n");
                    ClassificationThread Cth = new ClassificationThread(review, 0);
                    Cth.start();
                    //Output result: single file
                    SentimentAnalysisApp.outputArea.append("Review imported.\n");
                    SentimentAnalysisApp.outputArea.append("ID: " + review.getId() + "\n");
                    SentimentAnalysisApp.outputArea.append("Text: " + review.getText() + "\n");
                    SentimentAnalysisApp.outputArea.append("Real Class: " + review.getRealPolarity() + "\n");
                    SentimentAnalysisApp.outputArea.append("Classification result: " + review.getPredictedPolarity() + "\n");

                    SentimentAnalysisApp.log.info("Review imported. ID: " + review.getId() + "\n");
                    if (realClass == 2) {
                        SentimentAnalysisApp.outputArea.append("Real class unknown.\n");
                    } else if (realClass == review.getPredictedPolarity()) {
                        SentimentAnalysisApp.outputArea.append("Correctly classified.\n");
                    } else {
                        SentimentAnalysisApp.outputArea.append("Misclassified.\n");
                    }

                } else {
                    // Cannot import non-txt files
                    SentimentAnalysisApp.outputArea.append("Input file path is neither a txt file nor folder.\n");
                }
            } else {
                // Folder
                String[] files = fileOrFolder.list();
                String fileSeparatorChar = System.getProperty("file.separator");
//----------------------------------------------------------------------------------------------
                ThreadPoolExecutor executor =
                        (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

                for (int i = 0; i < files.length; i++) {
                    if (files[i].endsWith(".txt")) {
                        // Only import txt files
                       // Import review
                        MovieReview review = readReview(filePath + fileSeparatorChar + files[i], realClass);
                        // Count correct classified reviews, only real class is known
                        SentimentAnalysisApp.outputArea.append("Created : review "+ review.getId() + "\n");
                        ClassificationThread Cth = new ClassificationThread(review,files.length);
                        executor.execute(Cth);
                    } else {
                        //Do nothing
                    }
                }
                executor.shutdown();
                if (executor.isTerminated())
                {
                    //Do nothing
                }
//----------------------------------------------------------------------------------------------
            }
        } catch (IOException e) {
            SentimentAnalysisApp.log.info(e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Reads a single review file and returns it as a MovieReview object.
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be opened.
     */
    @Override
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException {
        // Read file for text
        Scanner inFile = new Scanner(new FileReader(reviewFilePath));
        String text = "";
        while (inFile.hasNextLine()) {
            text += inFile.nextLine();
        }
        // Remove the <br /> occurences in the text and replace them with a space
        text = text.replaceAll("<br />"," ");

        // Create review object, assigning ID and real class
        MovieReview review = new MovieReview(ID, text, realClass);
        // Update ID
        ID++;

        return review;
    }

    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
    @Override
    public void deleteReview(int id) {

        if (!database.containsKey(id)) {
            // Review with given ID does not exist
            SentimentAnalysisApp.outputArea.append("ID " + id + " does not exist.\n");
            SentimentAnalysisApp.log.info("Review with ID " + id + "not found to be deleted.\n");
        } else {
            database.remove(id);
            SentimentAnalysisApp.outputArea.append("Review with ID " + id + " deleted.\n");
            SentimentAnalysisApp.log.info("Review with ID " + id + " deleted.\n");
        }
    }

    /**
     * Loads review database.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadSerialDB() {
        SentimentAnalysisApp.outputArea.append("Reading database...\n");
        // serialize the database
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream(DATA_FILE_NAME);
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);

            database = (Map<Integer, MovieReview>)input.readObject();
            SentimentAnalysisApp.outputArea.append(database.size() + " entry(s) loaded.\n");

            SentimentAnalysisApp.log.info("Database loaded. " + database.size() + " entry(s) loaded.\n");
            // Find the current maximum ID
            if (! database.isEmpty()) {
                int currMaxId = Collections.max(database.keySet());
                ID = currMaxId + 1;
            } else {
                ID = 1;
            }

            input.close();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            SentimentAnalysisApp.log.info(e.toString());
            e.printStackTrace();
        } finally {
            close(file);
        }
        SentimentAnalysisApp.outputArea.append("Done.\n");
    }


    /**
     * Searches the review database by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not
     * exist in the database.
     */
    @Override
    public MovieReview searchById(int id) {
        if (database.containsKey(id)) {
            return database.get(id);
        }
        return null;
    }


    /**
     * Searches the review database for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    @Override
    public List<MovieReview> searchBySubstring(String substring) {
        List<MovieReview> tempList = new ArrayList<MovieReview>();

        for (Map.Entry<Integer, MovieReview> entry : database.entrySet()){
            if (entry.getValue().getText().contains(substring)) {
                tempList.add(entry.getValue());
            }
        }

        if (!tempList.isEmpty()) {
            return tempList;
        } else {
            // No review has given substring
            return null;
        }

    }
}