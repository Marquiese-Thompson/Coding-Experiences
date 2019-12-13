package assign5;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ReviewHandler extends AbstractReviewHandler {

    private static int ID = 0;
    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     * Also Prints the loaded reviews information in a pop-up frame in a
     * formatted JPanel
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     */
    @Override
    public void loadReviews(String filePath, int realClass) {
        JFrame data = new JFrame();
        data.setSize(650,500);
        data.setTitle("Current Database Content");

        JPanel InfoScreen = new JPanel();
        InfoScreen.setPreferredSize(new Dimension(400, 100));
        InfoScreen.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Information"));
        InfoScreen.setBackground(Color.WHITE);
        InfoScreen.setLayout(new GridLayout(12,1));
        File fileOrFolder = new File(filePath);
        try {
            if (fileOrFolder.isFile()) {
                // File
                if (filePath.endsWith(".txt")) {
                    // Import review
                    MovieReview review = readReview(filePath, realClass);
                    // Add to database
                    database.put(review.getId(), review);
                    //Output result: single file
                    InfoScreen.add(new JLabel("Review imported."));
                    InfoScreen.add(new JLabel("ID: " + review.getId()));
                    InfoScreen.add(new JLabel("Text: " + review.getText()));
                    InfoScreen.add(new JLabel("Real Class: " + review.getRealPolarity()));
                    InfoScreen.add(new JLabel("Classification result: " + review.getPredictedPolarity()));
                    if (realClass == 2) {
                        InfoScreen.add(new JLabel("Real class unknown."));
                    } else if (realClass == review.getPredictedPolarity()) {
                        InfoScreen.add(new JLabel("Correctly classified."));
                    } else {
                        InfoScreen.add(new JLabel("Misclassified."));
                    }
                    InfoScreen.add(new JLabel());

                } else {
                    // Cannot import non-txt files
                    InfoScreen.add(new JLabel("Input file path is neither a txt file nor folder."));
                }
            } else {
                // Folder
                String[] files = fileOrFolder.list();
                String fileSeparatorChar = System.getProperty("file.separator");
                int counter = 0;
                for (String fileName : files) {
                    if (fileName.endsWith(".txt")) {
                        // Only import txt files
                        // Import review
                        MovieReview review = readReview(filePath + fileSeparatorChar + fileName, realClass);
                        // Add to database
                        database.put(review.getId(), review);
                        // Count correct classified reviews, only real class is known
                        if (realClass != 2 && review.getRealPolarity() == review.getPredictedPolarity()) {
                            counter++;
                        }
                    } else {
                        //Do nothing
                    }
                }
                // Output result: folder
                InfoScreen.add(new JLabel("Folder imported."));
                InfoScreen.add(new JLabel("Number of entries: " + files.length));

                // Only output accuracy if real class is known
                if (realClass != 2) {
                    InfoScreen.add(new JLabel("Correctly classified: " + counter));
                    InfoScreen.add(new JLabel("Misclassified: " + (files.length - counter)));
                    InfoScreen.add(new JLabel("Accuracy: " + ((double)counter / (double)files.length * 100) + "%"));
                }
            }
        } catch (IOException e) {
            JFrame error = new JFrame();
            JPanel message = new JPanel();
            message.add(new JLabel(e.toString()));
            //e.printStackTrace();
            error.add(message);
            error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            error.setVisible(true);
        }
        data.add(InfoScreen);
        //data.pack();
        data.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        data.setVisible(true);
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
        // Classify review
        classifyReview(review);

        return review;
    }

    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
    @Override
    public void deleteReview(int id) {
        JFrame frame = new JFrame();
        frame.setSize(650,500);
        frame.setTitle("Deleting Review Message");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,50));

        JLabel label = new JLabel();
        if (!database.containsKey(id)) {
            // Review with given ID does not exist
            panel.setBackground(Color.RED);
            label.setText("ID " + id + " does not exist.");
        } else {
            database.remove(id);
            panel.setBackground(Color.GREEN);
            label.setText("Review with ID " + id + " deleted.");
        }
        panel.add(label);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Loads review database.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadSerialDB() {
        System.out.print("Reading database...");
        // serialize the database
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream(DATA_FILE_NAME);
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);

            database = (Map<Integer, MovieReview>)input.readObject();
            System.out.println(database.size() + " entry(s) loaded.");

            // Find the current maximum ID
            int currMaxId = Collections.max(database.keySet());
            ID = currMaxId + 1;

            input.close();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            close(file);
        }
        System.out.println("Done.");
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