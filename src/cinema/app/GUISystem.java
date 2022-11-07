package cinema.app;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GUISystem extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;

    protected  MenuBar menuBar = new MenuBar();

    protected  BackgroundPanel backgroundPanel = new BackgroundPanel(WIDTH, HEIGHT, Color.DARK_GRAY);

    protected  RegistrationPanel regPanel = new RegistrationPanel(WIDTH, HEIGHT, Color.DARK_GRAY);

    protected  SchedulePanel schedulePanel = new SchedulePanel(WIDTH, HEIGHT, Color.DARK_GRAY);

    HallPanel hallPanel;

    protected  TicketPanel ticketPanel = new TicketPanel(WIDTH, HEIGHT, Color.DARK_GRAY);

    public static void main(String[]string ) throws FileNotFoundException {
         GUISystem jFrame = new GUISystem("Cinema System");
    }

    private GUISystem(String title) throws FileNotFoundException {
        super(title);
        try {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 150, dimension.height / 2 - 150, WIDTH, HEIGHT);
            this.setTitle("Cinema App");

            //init menu bar
            this.menuBar.initComponents();
            this.setJMenuBar(this.menuBar);

            this.add(this.backgroundPanel);
            this.backgroundPanel.setVisible(true);

            this.backgroundPanel.add(regPanel);
            this.backgroundPanel.add(schedulePanel);
            //this.backgroundPanel.add(hallPanel);
            this.backgroundPanel.add(ticketPanel);

            this.regPanel.initElements();
            this.schedulePanel.initElements();
            //this.hallPanel.initElements();
            this.ticketPanel.initElements();

            this.regPanel.setVisible(true);
            this.schedulePanel.setVisible(false);
            //this.hallPanel.setVisible(false);
            this.ticketPanel.setVisible(false);

            this.regPanel.goToScheduleButton.addActionListener((e) -> {
                this.regPanel.setVisible(false);
                this.schedulePanel.setVisible(true);
                //this.hallPanel.setVisible(false);
                this.ticketPanel.setVisible(false);
            });

            // session choosing button
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmNumButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                            this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmNameButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmDateButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmDurationButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmStartTimeButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.orderPriceButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);


                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);

                    this.hallPanel.regButton.addActionListener((k) -> {
                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(false);
                        this.ticketPanel.setVisible(true);
                    });
                });

            }

            this.ticketPanel.makePaymentPrintTicket.addActionListener((e) -> {
                this.schedulePanel.setVisible(true);
                //this.hallPanel.setVisible(false);
                this.ticketPanel.setVisible(false);
            });


        }catch(NullPointerException e){
            System.err.println(e);
        }
    }

    private static class MenuBar extends JMenuBar{
    JMenu file = new JMenu("File");

    JMenu info = new JMenu("Info");
//file fields
    JMenuItem schedule = new JMenuItem("Show schedule");
    JMenuItem orders = new JMenuItem("Show all orders");
//info fields
    JMenuItem ask1 = new JMenuItem("The total number of viewers");
    JMenuItem ask2 = new JMenuItem("Number of films.");
    JMenuItem ask3 = new JMenuItem("The number of viewers of the specified film.");
    JMenuItem ask4 = new JMenuItem("The film that has the greatest demand.");
    JMenuItem ask5 = new JMenuItem("The total amount from the sale of tickets.");
    JMenuItem ask6 = new JMenuItem("The number of spectators who purchased tickets for the specified time.");

    MenuBar(){super();}

    public void initComponents(){

        this.file.add(this.schedule);
        this.file.add(this.orders);

        this.info.add(ask1);
        this.info.add(ask2);
        this.info.add(ask3);
        this.info.add(ask4);
        this.info.add(ask5);
        this.info.add(ask6);

        this.add(file);
        this.add(info);
    }

}

    private static abstract class AbstractPanel extends JPanel{
        protected final int  PANEL_WIDTH;
        protected final int PANEL_HEIGHT;
        AbstractPanel(int width, int height, Color color){
            super();

            this.PANEL_WIDTH = width;
            this.PANEL_HEIGHT = height;

            this.setPreferredSize (new Dimension (PANEL_WIDTH, PANEL_HEIGHT));
            this.setBackground(color);
        }

    }

    private static class BackgroundPanel extends AbstractPanel{

        BackgroundPanel(int width, int height, Color color){
            super(width, height,color);
        }
    }

    private static class RegistrationPanel extends AbstractPanel{

        JTextField nameField = new JTextField();

        JTextField surnameField = new JTextField();

        JButton goToScheduleButton = new JButton();

        RegistrationPanel(int width, int height, Color color){
            super(width, height, color);
        }

        public void initElements(){




            this.setLayout(null);
            this.nameField.setText("Print your name here: ");
            this.surnameField.setText("Print your surname here: ");
            this.goToScheduleButton.setText("Register");

            this.nameField.setBounds(300, 100, 200, 50);
            this.surnameField.setBounds(300, 200, 200, 50);
            this.goToScheduleButton.setBounds(300, 300, 200, 50);

            this.add(this.nameField);
            this.add(this.surnameField);
            this.add(this.goToScheduleButton);

            this.setVisible(true);



        }


    }

    private static class SchedulePanel extends AbstractPanel{

        Schedule schedule;
        int size;
        JButton[] filmNumButton ;
        JButton filmNameButton[];
        JButton filmDateButton[];

        JButton filmStartTimeButton[];
        JButton filmDurationButton[];
        JButton orderPriceButton[];


        SchedulePanel(int width, int height, Color color) throws FileNotFoundException {
            super( width, height, color);
            schedule = new Schedule();
            size = schedule.session.size();

            filmNumButton = new JButton[size];
            filmNameButton = new JButton[size];
            filmDateButton = new JButton[size];
            filmStartTimeButton = new JButton[size];
            filmDurationButton = new JButton[size];
            orderPriceButton = new JButton[size];

        }

        public void initElements(){
            this.setLayout(null);

            for(int i = 1 ; i<=schedule.session.size(); i++){
                int j = i-1;

                filmNumButton[j] = new JButton();
                filmNameButton[j] = new JButton();
                filmDateButton[j] = new JButton();
                filmStartTimeButton[j] = new JButton();
                filmDurationButton[j] = new JButton();
                orderPriceButton[j] = new JButton();



                String str;

                filmNumButton[j].setText( str = Integer.toString(i));
                filmNameButton[j].setText(schedule.session.get(i).getFilmName());
                filmDateButton[j].setText(schedule.session.get(i).getDate());
                filmStartTimeButton[j].setText(str = Double.toString(schedule.session.get(i).getFilmStartTime()));
                filmDurationButton[j].setText(str = Double.toString(schedule.session.get(i).getFilmDuration()));
                orderPriceButton[j].setText(str = Integer.toString(schedule.session.get(i).getOrderPrice()));
                int y;
                if(i==1) y= 0;
                else { y = (i-1)*80;}



                filmNumButton[j].setBounds(0, y, 133, 80);
                filmNameButton[j].setBounds(133, y, 133, 80 );
                filmDateButton[j].setBounds(266, y, 133, 80);
                filmStartTimeButton[j].setBounds(399, y, 133, 80);
                filmDurationButton[j].setBounds(532, y, 133, 80);
                orderPriceButton[j].setBounds(665, y, 133, 80);

                this.add(filmNumButton[j]);
                this.add(filmNameButton[j]);
                this.add(filmDateButton[j]);
                this.add(filmStartTimeButton[j]);
                this.add(filmDurationButton[j]);
                this.add(orderPriceButton[j]);


            }
            this.setVisible(true);
        }
    }

    private static class HallPanel extends AbstractPanel{

        Hall hall;
        private final int rows = hall.getRows();
        private final int number = hall.getNumberOfSeats();
        JButton[][] button = new JButton[rows][number];

        JTextField rowField = new JTextField("You row:");
        JTextField numberField = new JTextField("You place number: ");

        JButton regButton = new JButton("Register");

        HallPanel(int width , int height, Color color, int hallSessionNumber) throws FileNotFoundException {
            super( width , height, color);

            hall = new Hall(hallSessionNumber);

        }

        protected void initElements()  {

            int x;
            int y;
            this.setLayout(null);
            for(int i = 0; i<rows; i++){
                y= i*80;
                for(int j= 0; j<number; j++){
                    button[i][j] = new JButton();
                    x = j*80;
                    button[i][j].setBounds(x, y, 80, 80);

                    if(hall.getSet(i, j).getBusy()){
                        button[i][j].setBackground(Color.RED);
                    }

                    this.add(button[i][j]);
                }
            }
            rowField.setBounds(10, 400, 120, 20);
            numberField.setBounds(10, 425 , 120, 20);
            regButton.setBounds(150, 400, 100, 45);
            this.add(rowField);
            this.add(numberField);
            this.add(regButton);
            this.setVisible(true);
        }
    }

    private static class TicketPanel extends AbstractPanel{
        String cinemaName = "Cinema City";
        String cinemaAddress = "Schevchenko 32";
        String cinemaPhoneNumber = "0679281972";

        Order newOrder;
        JButton makePaymentPrintTicket;

        TicketPanel(int width , int height ,  Color color){
            super(width, height, color);
        }

        public void initElements(){
            this.setLayout(null);
            JLabel cinemaNameLabel = new JLabel(this.cinemaName);
            JLabel filmName = new JLabel("fjfjfjfjfjfjfjfjfj");
            JLabel filmDate = new JLabel("25.11.2021");
            JLabel filmStartTime = new JLabel("14.45");
            JLabel filmDuration = new JLabel("fjfjfjfjffjjfkggkgkgkgkgk");
            JLabel orderPrice = new JLabel("3");
            JLabel seatPlace = new JLabel("1");
            JLabel cinemaAddress = new JLabel("Our cinema address: " + this.cinemaAddress);
            JLabel cinemaPhoneNumber = new JLabel("Our phone: " + this.cinemaPhoneNumber);
            JLabel cashierInitials = new JLabel();
            JLabel orderID = new JLabel();
            makePaymentPrintTicket = new JButton("Make Payment & Print Ticket");

            cinemaNameLabel.setBounds(350, 0, 200, 50);
            filmName.setBounds(10, 50 , 200, 50);
            filmDate.setBounds(10, 100, 200, 50);
            filmStartTime.setBounds(10, 150, 200, 50);
            filmDuration.setBounds(10,200, 200, 50);
            orderPrice.setBounds(10, 250, 200, 50);
            seatPlace.setBounds(10, 300, 200 ,50);
            cinemaAddress.setBounds(10, 350, 200 ,50);
            cinemaPhoneNumber.setBounds(10, 400 ,200 ,50);
            cashierInitials.setBounds(10, 450, 200, 50);
            orderID.setBounds(10, 500 ,200, 50);
            makePaymentPrintTicket.setBounds(300, 150, 200, 50);

            this.add(cinemaNameLabel);
            this.add(filmName);
            this.add(filmDate);
            this.add(filmStartTime);
            this.add(filmDuration);
            this.add(orderPrice);
            this.add(seatPlace);
            this.add(cinemaAddress);
            this.add(cinemaPhoneNumber);
            this.add(cashierInitials);
            this.add(orderID);
            this.add(makePaymentPrintTicket);

            this.setVisible(true);
        }
    }


    private static class ask1 extends AbstractPanel{

        ask1(int width , int height ,  Color color){
            super(width, height, color);
        }
    }
    private static class ask2 extends AbstractPanel{

        ask2(int width , int height ,  Color color){
            super(width, height, color);
        }
    }
    private static class ask3 extends AbstractPanel{

        ask3(int width , int height ,  Color color){
            super(width, height, color);
        }
    }
    private static class ask4 extends AbstractPanel{

        ask4(int width , int height ,  Color color){
            super(width, height, color);
        }
    }
    private static class ask5 extends AbstractPanel{

        ask5(int width , int height ,  Color color){
            super(width, height, color);
        }
    }
    private static class ask6 extends AbstractPanel{

        ask6(int width , int height ,  Color color){
            super(width, height, color);
        }
    }

}

