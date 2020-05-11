package DataPathDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Image.SCALE_SMOOTH;

/**
 * @author Marquiese Thompson
 * @author Mason Popp
 * @author Maximino Rios
 * @version 1.0
 * @Date 03.30.2020
 */

public class DataPathDemo
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("Processor Datapath Demo 1.0");
        frame.setSize(500,250);
        //----------------------------------------------

        // sets the opening screen up
        JPanel open_screen = new JPanel();
        open_screen.setBackground(Color.MAGENTA);
        open_screen.getBackground();
        open_screen.setLayout(new BorderLayout());
        open_screen.getLayout();

        ImageIcon icon = new ImageIcon("mmm.jpg");
        Image mmm_icon = icon.getImage().getScaledInstance(100,100, SCALE_SMOOTH);
        icon = new ImageIcon(mmm_icon);

        JLabel label = new JLabel(icon);
        label.setSize(100,50);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Impact", Font.BOLD,30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        open_screen.add(label, BorderLayout.CENTER);

        frame.add(open_screen); // adds the open screen to the frame
        frame.setVisible(true); // shows the main screen

        // after 3 seconds the opening screen is refreshed to the main menu
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        frame.remove(open_screen); // removes open screen
        frame.repaint(); // Refresh the app clearing the open screen

        //----------------------------------------------

        // set up for the main menu
        JPanel menu = new JPanel();
        menu.setBackground(Color.white);
        menu.getBackground();
        menu.setLayout(new GridLayout(1,4));

        //----------------------------------------------

        // setup for the Load instruction button and page
        JButton load = new JButton();
        load.add(new JLabel("Load instruction"));
        load.setPreferredSize(new Dimension(100,100));
        load.setActionCommand("Load Instruction");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.remove(menu); // clears the menu from the screen
                frame.repaint(); // refreshes the screen

                // set the stage for the instruction
                JPanel Load_Path = new JPanel();
                Load_Path.setBackground(Color.white);
                Load_Path.getBackground();
                Load_Path.setLayout(new BorderLayout());

                ImageIcon instructMEM = new ImageIcon("instruction.png");
                Image Instruct_icon = instructMEM.getImage().getScaledInstance(80,175, SCALE_SMOOTH);
                instructMEM = new ImageIcon(Instruct_icon);

                JLabel instruct = new JLabel(instructMEM);
                instruct.setBackground(Color.WHITE);

                ImageIcon layout = new ImageIcon("layout.png");
                Image proccesor = layout.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                layout = new ImageIcon(proccesor);

                JLabel datapath = new JLabel(layout);

                // enables the user to draw the datapath
                final JPanel[] draw = {new JPanel()};
                JButton next = new JButton("Draw Path");
                next.setActionCommand("Draw Path");
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        draw[0] = new JPanel(){
                            Point pointStart = null;
                            Point pointEnd   = null;
                            {
                                addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent e) {
                                        pointStart = e.getPoint();
                                    }

                                    public void mouseReleased(MouseEvent e) {
                                        pointStart = null;
                                    }
                                });
                                addMouseMotionListener(new MouseMotionAdapter() {
                                    public void mouseMoved(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                    }

                                    public void mouseDragged(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                        repaint();
                                    }
                                });
                            }
                            public void paint(Graphics g) {
                                super.paint(g);
                                if (pointStart != null) {
                                    g.setColor(Color.RED);
                                    g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                                }
                            }
                        };
                        draw[0].setBackground(new Color(0,0,0,0));
                        draw[0].setPreferredSize(new Dimension(500,250));
                        frame.add(draw[0]);
                        frame.setVisible(true);
                    }
                });

                // goes back to the main menu
                JPanel Back_button = new JPanel();
                Back_button.setLayout(new BorderLayout());
                JButton back = new JButton("Back");
                back.setActionCommand("back");
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.remove(Load_Path);
                        frame.remove(draw[0]);
                        frame.repaint();
                        frame.add(menu);
                        frame.setVisible(true);
                    }
                });

                // shows the correct path
                JButton correct = new JButton("Show Path");
                correct.setActionCommand("path");
                correct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Load_Path.remove(datapath);

                        ImageIcon CPath = new ImageIcon("loadpath.png");
                        Image correction = CPath.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                        CPath = new ImageIcon(correction);
                        JLabel Loadpath = new JLabel(CPath);

                        Load_Path.add(Loadpath);
                        frame.repaint();
                        frame.setVisible(true);
                    }
                });

                JPanel toplevel = new JPanel();
                toplevel.setLayout(new GridLayout(1,2));
                toplevel.add(next);
                toplevel.add(correct);

                Back_button.add(back, BorderLayout.SOUTH);
                Load_Path.add(toplevel, BorderLayout.PAGE_START);
                Load_Path.add(datapath, BorderLayout.EAST);
                Load_Path.add(instruct,BorderLayout.WEST);
                Load_Path.add(Back_button,BorderLayout.PAGE_END);

                frame.add(Load_Path); // adds the Load path panel to the frame
                frame.setVisible(true); // shows the option
            }
        });

        //----------------------------------------------

        // setup for the Store instruction button and page
        JButton Store = new JButton();
        Store.add(new JLabel("Store instruction"));
        Store.setPreferredSize(new Dimension(100,100));
        Store.setActionCommand("Store Instruction");
        Store.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.remove(menu); // clears the menu from the screen
                frame.repaint(); // refreshes the screen

                // set the stage for the instruction
                JPanel Store_Path = new JPanel();
                Store_Path.setBackground(Color.white);
                Store_Path.getBackground();
                Store_Path.setLayout(new BorderLayout());

                ImageIcon instructMEM = new ImageIcon("instruction.png");
                Image Instruct_icon = instructMEM.getImage().getScaledInstance(100,175, SCALE_SMOOTH);
                instructMEM = new ImageIcon(Instruct_icon);

                JLabel instruct = new JLabel(instructMEM);
                instruct.setBackground(Color.WHITE);

                ImageIcon layout = new ImageIcon("layout.png");
                Image proccesor = layout.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                layout = new ImageIcon(proccesor);

                JLabel datapath = new JLabel(layout);

                // enables the user to draw the datapath
                final JPanel[] draw = {new JPanel()};
                JButton next = new JButton("Draw Path");
                next.setActionCommand("Draw Path");
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        draw[0] = new JPanel(){
                            Point pointStart = null;
                            Point pointEnd   = null;
                            {
                                addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent e) {
                                        pointStart = e.getPoint();
                                    }

                                    public void mouseReleased(MouseEvent e) {
                                        pointStart = null;
                                    }
                                });
                                addMouseMotionListener(new MouseMotionAdapter() {
                                    public void mouseMoved(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                    }

                                    public void mouseDragged(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                        repaint();
                                    }
                                });
                            }
                            public void paint(Graphics g) {
                                super.paint(g);
                                if (pointStart != null) {
                                    g.setColor(Color.RED);
                                    g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                                }
                            }
                        };
                        draw[0].setBackground(new Color(0,0,0,0));
                        draw[0].setPreferredSize(new Dimension(500,250));
                        frame.add(draw[0]);
                        frame.setVisible(true);
                    }
                });

                // goes back to the main menu
                JPanel Back_button = new JPanel();
                Back_button.setLayout(new BorderLayout());
                JButton back = new JButton("Back");
                back.setActionCommand("back");
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.remove(Store_Path);
                        frame.remove(draw[0]);
                        frame.repaint();
                        frame.add(menu);
                        frame.setVisible(true);
                    }
                });

                // shows the correct path
                JButton correct = new JButton("Show Path");
                correct.setActionCommand("path");
                correct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Store_Path.remove(datapath);

                        ImageIcon CPath = new ImageIcon("storepath.png");
                        Image correction = CPath.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                        CPath = new ImageIcon(correction);
                        JLabel StorePath = new JLabel(CPath);

                        Store_Path.add(StorePath);
                        frame.repaint();
                        frame.setVisible(true);
                    }
                });

                JPanel toplevel = new JPanel();
                toplevel.setLayout(new GridLayout(1,2));
                toplevel.add(next);
                toplevel.add(correct);

                Back_button.add(back, BorderLayout.SOUTH);
                Store_Path.add(toplevel, BorderLayout.PAGE_START);
                Store_Path.add(datapath, BorderLayout.EAST);
                Store_Path.add(instruct,BorderLayout.WEST);
                Store_Path.add(Back_button,BorderLayout.PAGE_END);

                frame.add(Store_Path); // adds the Store path panel to the frame
                frame.setVisible(true); // shows the option
            }
        });

        //----------------------------------------------

        // setup for the branch instruction button and page
        JButton Branch = new JButton();
        Branch.add(new JLabel("Branch instruction"));
        Branch.setPreferredSize(new Dimension(100,100));
        Branch.setActionCommand("Branch Instruction");
        Branch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.remove(menu); // clears the menu from the screen
                frame.repaint(); // refreshes the screen

                // set the stage for the instruction
                JPanel Branch_Path = new JPanel();
                Branch_Path.setBackground(Color.white);
                Branch_Path.getBackground();
                Branch_Path.setLayout(new BorderLayout());

                ImageIcon instructMEM = new ImageIcon("instruction.png");
                Image Instruct_icon = instructMEM.getImage().getScaledInstance(100,175, SCALE_SMOOTH);
                instructMEM = new ImageIcon(Instruct_icon);

                JLabel instruct = new JLabel(instructMEM);
                instruct.setBackground(Color.WHITE);

                ImageIcon layout = new ImageIcon("Branch.png");
                Image proccesor = layout.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                layout = new ImageIcon(proccesor);

                JLabel datapath = new JLabel(layout);

                // enables the user to draw the datapath
                final JPanel[] draw = {new JPanel()};
                JButton next = new JButton("Draw Path");
                next.setActionCommand("Draw Path");
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        draw[0] = new JPanel(){
                            Point pointStart = null;
                            Point pointEnd   = null;
                            {
                                addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent e) {
                                        pointStart = e.getPoint();
                                    }

                                    public void mouseReleased(MouseEvent e) {
                                        pointStart = null;
                                    }
                                });
                                addMouseMotionListener(new MouseMotionAdapter() {
                                    public void mouseMoved(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                    }

                                    public void mouseDragged(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                        repaint();
                                    }
                                });
                            }
                            public void paint(Graphics g) {
                                super.paint(g);
                                if (pointStart != null) {
                                    g.setColor(Color.RED);
                                    g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                                }
                            }
                        };
                        draw[0].setBackground(new Color(0,0,0,0));
                        draw[0].setPreferredSize(new Dimension(500,250));
                        frame.add(draw[0]);
                        frame.setVisible(true);
                    }
                });

                // goes back to the main menu
                JPanel Back_button = new JPanel();
                Back_button.setLayout(new BorderLayout());
                JButton back = new JButton("Back");
                back.setActionCommand("back");
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.remove(Branch_Path);
                        frame.remove(draw[0]);
                        frame.repaint();
                        frame.add(menu);
                        frame.setVisible(true);
                    }
                });

                // shows the correct path
                JButton correct = new JButton("Show Path");
                correct.setActionCommand("path");
                correct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Branch_Path.remove(datapath);

                        ImageIcon CPath = new ImageIcon("branchpath.png");
                        Image correction = CPath.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                        CPath = new ImageIcon(correction);
                        JLabel Branchpath = new JLabel(CPath);

                        Branch_Path.add(Branchpath);
                        frame.repaint();
                        frame.setVisible(true);
                    }
                });

                JPanel toplevel = new JPanel();
                toplevel.setLayout(new GridLayout(1,2));
                toplevel.add(next);
                toplevel.add(correct);

                Back_button.add(back, BorderLayout.SOUTH);
                Branch_Path.add(toplevel, BorderLayout.PAGE_START);
                Branch_Path.add(datapath, BorderLayout.EAST);
                Branch_Path.add(instruct,BorderLayout.WEST);
                Branch_Path.add(Back_button,BorderLayout.PAGE_END);


                frame.add(Branch_Path); // adds the Branch path panel to the frame
                frame.setVisible(true); // shows the option
            }
        });

        //----------------------------------------------

        // setup for the add instruction button and page
        JButton Add = new JButton();
        Add.add(new JLabel("Add instruction"));
        Add.setPreferredSize(new Dimension(100,100));
        Add.setActionCommand("Add Instruction");
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.remove(menu); // clears the menu from the screen
                frame.repaint(); // refreshes the screen


                // set the stage for the instruction
                JPanel Add_Path = new JPanel();
                Add_Path.setBackground(Color.white);
                Add_Path.getBackground();
                Add_Path.setLayout(new BorderLayout());

                ImageIcon instructMEM = new ImageIcon("instruction.png");
                Image Instruct_icon = instructMEM.getImage().getScaledInstance(100,175, SCALE_SMOOTH);
                instructMEM = new ImageIcon(Instruct_icon);

                JLabel instruct = new JLabel(instructMEM);
                instruct.setBackground(Color.WHITE);

                ImageIcon layout = new ImageIcon("Add.png");
                Image proccesor = layout.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                layout = new ImageIcon(proccesor);

                JLabel datapath = new JLabel(layout);

                // enables the user to draw the datapath
                final JPanel[] draw = {new JPanel()};
                JButton next = new JButton("Draw Path");
                next.setActionCommand("Draw Path");
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        draw[0] = new JPanel(){
                            Point pointStart = null;
                            Point pointEnd   = null;
                            {
                                addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent e) {
                                        pointStart = e.getPoint();
                                    }

                                    public void mouseReleased(MouseEvent e) {
                                        pointStart = null;
                                    }
                                });
                                addMouseMotionListener(new MouseMotionAdapter() {
                                    public void mouseMoved(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                    }

                                    public void mouseDragged(MouseEvent e) {
                                        pointEnd = e.getPoint();
                                        repaint();
                                    }
                                });
                            }
                            public void paint(Graphics g) {
                                super.paint(g);
                                if (pointStart != null) {
                                    g.setColor(Color.RED);
                                    g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                                }
                            }
                        };
                        draw[0].setBackground(new Color(0,0,0,0));
                        draw[0].setPreferredSize(new Dimension(500,250));
                        frame.add(draw[0]);
                        frame.setVisible(true);
                    }
                });

                // goes back to the main menu
                JPanel Back_button = new JPanel();
                Back_button.setLayout(new BorderLayout());
                JButton back = new JButton("Back");
                back.setActionCommand("back");
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.remove(Add_Path);
                        frame.remove(draw[0]);
                        frame.repaint();
                        frame.add(menu);
                        frame.setVisible(true);
                    }
                });

                // shows the correct path
                JButton correct = new JButton("Show Path");
                correct.setActionCommand("path");
                correct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Add_Path.remove(datapath);

                        ImageIcon CPath = new ImageIcon("addpath.png");
                        Image correction = CPath.getImage().getScaledInstance(400, 175, SCALE_SMOOTH);
                        CPath = new ImageIcon(correction);
                        JLabel Addpath = new JLabel(CPath);

                        Add_Path.add(Addpath);
                        frame.repaint();
                        frame.setVisible(true);
                    }
                });

                JPanel toplevel = new JPanel();
                toplevel.setLayout(new GridLayout(1,2));
                toplevel.add(next);
                toplevel.add(correct);

                Back_button.add(back, BorderLayout.SOUTH);
                Add_Path.add(toplevel, BorderLayout.PAGE_START);
                Add_Path.add(datapath, BorderLayout.EAST);
                Add_Path.add(instruct,BorderLayout.WEST);
                Add_Path.add(Back_button,BorderLayout.PAGE_END);

                frame.add(Add_Path); // adds the Add path panel to the frame
                frame.setVisible(true); // shows the option
            }
        });

        //----------------------------------------------
        menu.add(load);
        menu.add(Store);
        menu.add(Branch);
        menu.add(Add);

        frame.add(menu);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
