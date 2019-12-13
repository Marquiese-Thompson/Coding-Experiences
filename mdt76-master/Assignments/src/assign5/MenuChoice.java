package assign5;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Marquise Thompson
 * @author Gilbert Velasquez
 */

public class MenuChoice {

    public static ReviewHandler rh = new ReviewHandler();

    /**
     * Prompts the user to provide the real class and the path
     * of the review(s) they wish to load in a new JFrame
     */
    public void Option1()
    {
        JFrame frame = new JFrame("Load Reviews");
        frame.setSize(new Dimension(650,500));

        JPanel SuperPanel = new JPanel();
        SuperPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        panel.add(menu());

        JPanel op1 = new JPanel();
        op1.setPreferredSize(new Dimension(300, 90));
        op1.setLayout(new GridLayout(2, 2));
        op1.setBorder(new LineBorder(Color.RED, 5));
        op1.setBackground(Color.yellow);
        op1.getBackground();


        op1.add(new JLabel("Text file path: "));
        JTextField path = new JTextField("Input file path here");

        op1.add(new JLabel("Real Class of reviews: "));
        JComboBox rClass = new JComboBox();
        String[] classes = {"0. Negative","1. Positive", "2. Unknown"};
        for (int i = 0; i< classes.length; i++)
            {
                rClass.addItem(classes[i]);
            }

        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rClass.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(path.getText()=="Input file path here")// checks if user entered the path yet
                        {
                            JFrame error = new JFrame("Error");
                            error.setLayout(new GridLayout(2,1));
                            JLabel message = new JLabel("You have not entered a path yet");
                            JLabel pt2 = new JLabel("Please close window and reenter path");
                            error.add(message);
                            error.add(pt2);
                            error.pack();
                            error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            error.setVisible(true);
                        }
                        else {
                            String filePath = path.getText();
                            int realClass = rClass.getSelectedIndex();
                            rh.loadReviews(filePath, realClass); // loads file or folder
                        }
                    }
                });
            }
        });
        op1.add(path);
        op1.add(rClass);
        panel.add(op1);
        SuperPanel.add(panel,BorderLayout.NORTH);

        frame.add(SuperPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     *Prompts the user to enter the id of the review they wish
     * to delete then preforms that action and displays the
     * appropriate message
     */
    public void Option2()
    {
        JFrame frame = new JFrame("Delete Reviews");
        frame.setSize(new Dimension(650,500));


        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        panel.add(menu());

        JPanel op2 = new JPanel();
        op2.setPreferredSize(new Dimension(300, 90));
        op2.setLayout(new GridLayout(2, 1));
        op2.setBorder(new LineBorder(Color.RED, 5));
        op2.setBackground(Color.yellow);
        op2.getBackground();

        op2.add(new JLabel("Review ID :"));
        JTextField idnum = new JTextField("Enter ID number here");
        JButton delete = new JButton("Delete");

        delete.setActionCommand("delete");
        delete.addActionListener(new ActionListener(){
            int nEvent = 0;
            public void actionPerformed(ActionEvent e) {
                rh.deleteReview(Integer.parseInt(idnum.getText()));
                rh.saveSerialDB();
                rh.loadSerialDB();
            }
        }); // performs the deletion if id is found in database

        op2.add(idnum);
        op2.add(delete);
        panel.add(op2);


        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Prompts the user to choose wither they want to search by
     * either id or sub-string then preforms that action
     */
    public void Option3()
    {
        JFrame frame = new JFrame("Search Reviews");
        frame.setSize(new Dimension(650,500));

        JPanel SuperPanel = new JPanel();
        SuperPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        JPanel op3 = new JPanel();
        op3.setPreferredSize(new Dimension(300, 90));
        op3.setLayout(new GridLayout(2, 1));
        op3.setBorder(new LineBorder(Color.RED, 5));
        op3.setBackground(Color.yellow);
        op3.getBackground();
        op3.setLayout(new GridLayout(3,1));

        JLabel method = new JLabel("Method of search: ");
        JButton IdOP = new JButton("By ID number");
        IdOP.setActionCommand("delete");
        IdOP.addActionListener(new ActionListener(){
            int nEvent = 0;
            public void actionPerformed(ActionEvent e) {
                MenuChoice.IdSearch();
            }
        }); // search by id

        JButton StrgOP = new JButton("By String ");
        StrgOP.setActionCommand("delete");
        StrgOP.addActionListener(new ActionListener(){
            int nEvent = 0;
            public void actionPerformed(ActionEvent e)
            { MenuChoice.StrgSearch();}
        }); // search by sub-string

        op3.add(method);
        op3.add(IdOP);
        op3.add(StrgOP);
        panel.add(op3);
        SuperPanel.add(panel, BorderLayout.SOUTH);

        frame.add(SuperPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Displays the database content in a new JTable format
     */
    public void showing()
    {
        ReviewHandler rh = new ReviewHandler();
        rh.loadSerialDB();
        JFrame data = new JFrame();
        data.setSize(650,500);
        data.setTitle("Current Database Content");

        JPanel frame = new JPanel();
        frame.setBorder(new LineBorder(Color.BLACK,2));
        frame.getBorder();
        frame.setBackground(Color.GREEN);
        frame.getBackground();
        frame.setSize(500,500);

        DefaultTableModel info = new DefaultTableModel();
        JTable table = new JTable(info);

        info.addColumn("Real Class");
        info.addColumn("Predicted Class");
        info.addColumn("ID #");
        info.addColumn("Review");

        if(rh.database.size()==0)
        {
            frame.add(new JLabel("Nothing is present in database"));
        }
        else {
            for (int i = 0; i < rh.database.size(); i++)
            {
                if (rh.database.containsValue(rh.database.get(i))) {
                    info.addRow(new Object[]
                            {
                                    rh.database.get(i).getRealPolarity(),
                                    rh.database.get(i).getPredictedPolarity(),
                                    rh.database.get(i).getId(),
                                    rh.database.get(i).getText()
                            });
                }
                else
                    {
                        i++;
                    }
                i++;
            }
        }
        frame.add(new JScrollPane(table));
        data.add(frame);
        data.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        data.setVisible(true);

        //auto save database
        rh.saveSerialDB();
    }

    /**
     * Saves database and informs the user in new JFrame
     */
    public void saving()
    {
        JFrame frame = new JFrame("Saving Database");
        frame.setPreferredSize(new Dimension(200,100));

        JPanel SuperPanel = new JPanel();
        SuperPanel.setPreferredSize(new Dimension(200,100));
        SuperPanel.setBorder(new LineBorder(Color.BLACK,2));
        SuperPanel.setBackground(Color.GREEN);
        SuperPanel.getBackground();

        JLabel operation = new JLabel();
        operation.setText("Saving database complete!");
        operation.setHorizontalAlignment(SwingConstants.CENTER);
        rh.saveSerialDB();

        SuperPanel.add(operation);

        frame.add(SuperPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Searches for the users desired review
     * then displays the reviews information if found
     */
    public static void IdSearch()
    {
        JFrame frame = new JFrame("Search by ID");
        frame.setSize(new Dimension(650,500));

        JPanel SuperPanel = new JPanel();
        SuperPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        JPanel search = new JPanel();
        search.setPreferredSize(new Dimension(300, 90));
        search.setLayout(new GridLayout(2, 1));
        search.setBorder(new LineBorder(Color.RED, 5));
        search.setBackground(Color.yellow);
        search.getBackground();
        search.setLayout(new GridLayout(1,2));

        search.add(new JLabel("Enter ID: "));
        JTextField number = new JTextField(4);

        number.setActionCommand("num");
        number.addActionListener(new ActionListener(){
            int nEvent = 0;
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(number.getText());
                MovieReview mr = rh.searchById(id);

                JFrame frame = new JFrame("Found review Info");
                frame.setSize(new Dimension(250,500));

                JPanel panel = new JPanel();
                panel.setSize(new Dimension(250, 100));
                panel.setBorder(new LineBorder(Color.BLACK, 2));
                panel.getBorder();
                panel.setBackground(Color.ORANGE);
                panel.getBackground();
                panel.setLayout(new GridLayout(6,1));

                if(mr == null)
                {
                    panel.add(new JLabel("Review with id "+ id+" was not found"));
                }
                else
                {
                    panel.add(new JLabel("Review with id "+ id+" was found"));
                    panel.add(new JLabel(" "));
                    panel.add(new JLabel("Real Class: "+ mr.getRealPolarity()));
                    panel.add(new JLabel("Predicted class: "+ mr.getPredictedPolarity()));
                    panel.add(new JLabel("Review content : "+mr.getText()));
                }


                frame.add(new JScrollPane(panel));
                //frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        search.add(number);
        panel.add(search);
        SuperPanel.add(panel, BorderLayout.NORTH);
        frame.add(SuperPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Searches the database for the sub-string and then
     * displays the amount of reviews which contains it
     * on an new JFrame
     */
    public static void StrgSearch()
    {
        JFrame frame = new JFrame("Search by Sub-String");
        frame.setSize(new Dimension(650,500));

        JPanel SuperPanel = new JPanel();
        SuperPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(650, 100));
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.getBorder();
        panel.setBackground(Color.CYAN);
        panel.getBackground();

        JPanel search = new JPanel();
        search.setPreferredSize(new Dimension(300, 90));
        search.setLayout(new GridLayout(2, 1));
        search.setBorder(new LineBorder(Color.RED, 5));
        search.setBackground(Color.yellow);
        search.getBackground();
        search.setLayout(new GridLayout(1,2));

        search.add(new JLabel("Enter some review text: "));
        JTextField string = new JTextField(30);

        string.setActionCommand("string");
        string.addActionListener(new ActionListener(){
            int nEvent = 0;
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Found review Info");
                frame.setSize(new Dimension(650,500));

                JPanel panel = new JPanel();
                panel.setSize(new Dimension(650, 100));
                panel.setBorder(new LineBorder(Color.BLACK, 2));
                panel.getBorder();
                panel.setBackground(Color.ORANGE);
                panel.getBackground();
                panel.setLayout(new GridLayout(1,1));

                List<MovieReview> reviewList= rh.searchBySubstring(string.getText());
                if (reviewList == null)
                {
                    panel.add(new JLabel("No reviews found containing ' "+string.getText()+" '"), SwingConstants.CENTER);
                }
                else
                {
                    panel.add(new JLabel(reviewList.size()+" reviews found"), SwingConstants.CENTER);
                }


                frame.add(panel);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        search.add(string, SwingConstants.CENTER);
        panel.add(search);
        SuperPanel.add(new JScrollPane(panel), BorderLayout.NORTH);
        frame.add(SuperPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Creates a panel witch displays the menu options
     * @return a menu panel
     */
    public JPanel menu()
    {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(300, 90));
        menu.setLayout(new GridLayout(4, 1));
        menu.setBorder(new LineBorder(Color.RED, 5));
        menu.setBackground(Color.yellow);
        menu.getBackground();
        menu.add(new JLabel("Menu options"));
        menu.add(new JLabel("1. Load new movie review collection"));
        menu.add(new JLabel("2. Delete movie review from database "));
        menu.add(new JLabel("3. Search movie reviews in database"));

        return menu;
    }
}
