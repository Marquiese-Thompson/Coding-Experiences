package assign3;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * @author Marquiese Thompson
 */
public class ReviewHandlerTest {
    //commonly used objects throughout test methods
    public static ReviewHandler reviewHandler = new ReviewHandler();
    public static MovieReview movieReview = null;
    public static String TestData = "Test data";
    public static int realClassTest = 0;
    public static String TestFilePath1 = "TestDirectory/TestFile1.txt";
    //----------------------------------------------------------------

    /**
     * test if the the files created from load is the same now
     * true or false type thing
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void loadSerialDBTest()
    {
        reviewHandler.loadReviews(TestFilePath1,realClassTest);
        reviewHandler.loadSerialDB();
        assertTrue("Test failed Database size is not "+reviewHandler.database.size(), reviewHandler.database.size() == 1);
    }
    //----------------------------------------------------------------

    /**
     * create a file and file path
     * test if the file is open properly
     * and load them correctly
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void loadReviewsTest()
    {
        reviewHandler.loadReviews("TestDirectory", realClassTest);
        assertEquals("Database size should equal ", 4, reviewHandler.database.size());
    }
    //----------------------------------------------------------------

    /**
     * test if a file in the files created read properly
     * Works
     * @throws IOException so readReview does not throw an error
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void readReviewTest() throws IOException
    {
        int TestPolarity = 0;
        movieReview = reviewHandler.readReview(TestFilePath1, TestPolarity);
        MovieReview movieReviewTest = new MovieReview(0, TestData, realClassTest);
        assertEquals("Review(s) is/are not the same as ", movieReviewTest.getText()+" 1", movieReview.getText());
    }
    //----------------------------------------------------------------

    /**
     * Test if database saves to correct file
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void saveSerialDBTest()
    {
        reviewHandler.loadReviews(TestFilePath1,realClassTest);
        reviewHandler.saveSerialDB();
        reviewHandler.loadSerialDB();
        assertTrue("", reviewHandler.database.size() == 1);
    }
    //----------------------------------------------------------------

    /**
     * Test if the an id matches from the user to
     * the file
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void searchByIdTest()
    {
        int TestID = 0;
        movieReview = reviewHandler.searchById(TestID);
        assertTrue("Review ID do not match ",  reviewHandler.database.get(0).getId() == movieReview.getId());
    }
    //----------------------------------------------------------------

    /**
     * Test if the sting matches from the user to
     * the file
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void searchBySubstringTest()
    {
        List<MovieReview> result = reviewHandler.searchBySubstring(TestData+" 1");
        assertTrue("Data does not contain correct data ", result.get(0).getText() == reviewHandler.database.get(0).getText());
    }
    //----------------------------------------------------------------

    /**
     * Tests if the test files are correctly classified
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void classifyReviewTest()
    {
        int TestId = 0;
        movieReview = new MovieReview(TestId, TestData, realClassTest);
        int returned = reviewHandler.classifyReview(movieReview);
        assertFalse("Polarity should be 1 == positive", returned == 0);
    }
    //----------------------------------------------------------------

    /**
     * test if the correct file was deleted then go back
     * and check it in the database
     *         /
     *       /
     * \   /
     *  \/
     */
    @Test
    public void deleteReviewTest()
    {
        int TestId = 1;
        reviewHandler.deleteReview(TestId);
        assertThat(reviewHandler.database.size(), equalTo(3));
        //Test for incorrect id
        TestId = 0;
        reviewHandler.deleteReview(TestId);
        assertThat(reviewHandler.database.size(), equalTo(2));
    }
    //----------------------------------------------------------------

    /**
     * Expected to run before the tests and created the files needed to test
     * @throws IOException to catch an error of the Files not being created
     * properly
     *         /
     *       /
     * \   /
     *  \/
     */
    @BeforeClass
    public static void FileCreator() throws IOException
    {
        File TestDir = new File("TestDirectory");
        TestDir.mkdir();

        FileWriter fileWriter = new FileWriter("TestDirectory/TestFile1.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(TestData + " 1");
        printWriter.close();

        FileWriter fileW = new FileWriter("TestDirectory/TestFile2.txt");
        PrintWriter printW = new PrintWriter(fileW);
        printW.println(TestData + " 2");
        printW.close();

        FileWriter fW = new FileWriter("TestDirectory/TestFile3.txt");
        PrintWriter pW = new PrintWriter(fW);
        pW.println(TestData + " 3");
        pW.close();


    }
}

