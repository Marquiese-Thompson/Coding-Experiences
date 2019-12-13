package assign5;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 *
 @author Marquiese Thompson
 @author Gilbert Velasquez
 @version 3.0
 *
 */
public class SentimentAnalysisApp
{
    private static MenuChoice options = new MenuChoice();
    public static void main(String [] args) {
        // Create object
        ReviewHandler rh = new ReviewHandler();
        // Load database if it exists.
        File databaseFile = new File(ReviewHandler.DATA_FILE_NAME);
        if (databaseFile.exists()) {
            rh.loadSerialDB();
        }

        JFrame frame = new JFrame("Sentiment Analysis App 3.0");
        frame.setSize(650, 310);

        JPanel SuperPanel = new JPanel();
        SuperPanel.setLayout(new BorderLayout());

        JLabel amount = new JLabel(String.valueOf(rh.database.size()));

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();


        panel.add(options.menu()); // add the menu to the panel

        // Format and creation of the
        JPanel choice = new JPanel();
        choice.setPreferredSize(new Dimension(300, 90));
        choice.setLayout(new GridLayout(4, 1));
        choice.setBorder(new LineBorder(Color.RED, 5));
        choice.setBackground(Color.yellow);
        choice.getBackground();

        choice.add(new JLabel("Choice is: "));
        //----------------------------------------------------------------------------
        JButton op1 = new JButton("1");
        op1.setActionCommand("option 1");
        op1.addActionListener(new ActionListener() {
            int nEvent = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                options.Option1();
            }
        }); // 1. Load new movie review collection (given a folder or a file path).
        choice.add(op1);
        //----------------------------------------------------------------------------
        JButton op2 = new JButton("2");
        op2.setActionCommand("option 2");
        op2.addActionListener(new ActionListener() {
            int nEvent = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                options.Option2();
            }
        }); // 2. Delete movie review from database (given its id).
        choice.add(op2);
        //----------------------------------------------------------------------------
        JButton op3 = new JButton("3");
        op3.setActionCommand("option 3");
        op3.addActionListener(new ActionListener() {
            int nEvent = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                options.Option3();

            }
        });  // 3. Search movie reviews in database by id or by matching a substring.
        choice.add(op3);
        //----------------------------------------------------------------------------

        panel.add(choice, BorderLayout.WEST);
        SuperPanel.add(panel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setSize(new Dimension(650, 50));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.ORANGE);
        panel.getBackground();

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.ORANGE);
        buttons.getBackground();

        //----------------------------------------------------------------------------
        JButton show = new JButton("Show database");
        show.setActionCommand("Showing database");
        show.addActionListener(new ActionListener() {
            int nEvents = 0; // number of events handled
            public void actionPerformed(ActionEvent e)
            {
                options.showing();
            }
       }); // Shows Database content in a JTable
        buttons.add(show, FlowLayout.LEFT);
        //----------------------------------------------------------------------------
        JButton save = new JButton("Save Database");
        save.setActionCommand("Saving database");
        save.addActionListener(new ActionListener() {
            int nEvent = 0;
            @Override
            public void actionPerformed(ActionEvent e)
            {
                options.saving();
                rh.loadSerialDB();
                amount.setText(String.valueOf(rh.database.size()));
            }
        }); // Saves new database info to the database.ser
        buttons.add(save, FlowLayout.LEFT);
        //----------------------------------------------------------------------------


        buttons.add(new JLabel("Current Database size :"), BorderLayout.WEST);
        buttons.add(amount, BorderLayout.AFTER_LAST_LINE); // Shows database at time of opening and changes after saving if needed

        panel.add(buttons);
        SuperPanel.add(panel, BorderLayout.CENTER);

        panel = new JPanel();
        panel.setSize(new Dimension(650, 500));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        JPanel IntroScreen = new JPanel();
        IntroScreen.setPreferredSize(new Dimension(600, 100));
        IntroScreen.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Information"));
        IntroScreen.setBackground(Color.WHITE);
        IntroScreen.setLayout(new BorderLayout());

        IntroScreen.add(new JLabel("Welcome to the Sentimental Analysis App 3.0 ...."), BorderLayout.NORTH);

        panel.add(IntroScreen);
        SuperPanel.add(panel, BorderLayout.SOUTH);

        frame.add(SuperPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}