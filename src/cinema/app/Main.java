package cinema.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class Main {
    static JFrame jFrame= getFrame();
    static  JPanel jPanel = new JPanel();


    public static void main(String[]string ){


        JTextField textNameField = new JTextField(20);
        textNameField.setBounds(50 , 50, 20, 10);
        JTextField textSurnameField = new JTextField(20);

        JTextField textFilmNameField = new JTextField(20);



        JMenuBar mb = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu info = new JMenu("Info");

        file.add(new JMenuItem("Film sheldue"));

        info.add(new JMenuItem("Total number of viewers"));
        info.add(new JMenuItem("Number of films"));
        info.add(new JMenuItem("The number of viewers of the specified film"));
        info.add(new JMenuItem("The movie that has the biggest sweat"));
        info.add(new JMenuItem("The total amount from the sale of tickets"));
        info.add(new JMenuItem("The number of spectators who purchased a ticket for the specified hour"));
        mb.add(file);
        mb.add(info);

        JButton jButton = new JButton("text");



        jPanel.add(textNameField);
        jPanel.add(textSurnameField);
        jPanel.add(textFilmNameField);

        jPanel.add(jButton);

        jFrame.setJMenuBar(mb);

        jFrame.add(jPanel);

        jFrame.revalidate();


    }



    static class MyComponent extends JComponent{
        protected void paintComponent(Graphics g){
            Font font = new Font("Arial", Font.BOLD, 20);
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(font);
            g2.drawString("Hello world", 20 ,20);
            Line2D l2= new Line2D.Double(100,100,300,300);
            g2.draw(l2);
        }
    }

    static JFrame getFrame(){
        JFrame mainFrame = new JFrame(){};
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        mainFrame.setBounds(dimension.width / 2 - 150, dimension.height / 2 - 150, 400, 400);
        mainFrame.setTitle("Cinema App");
        return mainFrame;
    }


}
