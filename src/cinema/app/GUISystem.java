package cinema.app;

import exeption.SetSeatBusyException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUISystem extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    protected  MenuBar menuBar = new MenuBar();
    protected  BackgroundPanel backgroundPanel = new BackgroundPanel(WIDTH, HEIGHT, Color.WHITE);
    protected  RegistrationPanel regPanel = new RegistrationPanel(WIDTH, HEIGHT, Color.WHITE);
    protected  SchedulePanel schedulePanel = new SchedulePanel(WIDTH, HEIGHT, Color.WHITE);
    Schedule schedule= new Schedule();
    HallPanel hallPanel;
    Order order;
    NewClientPanel newClientPanel = new NewClientPanel(WIDTH, HEIGHT, Color.WHITE);
    TicketPanel ticketPanel = new TicketPanel(WIDTH, HEIGHT, Color.WHITE);
    //extra fields
    int  rowNumber, placeNumber;
    Cashier cashier;Client client;

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
            this.backgroundPanel.add(newClientPanel);
            this.backgroundPanel.add(schedulePanel);
            this.backgroundPanel.add(ticketPanel);

            this.regPanel.initElements();
            this.newClientPanel.initElements();
            this.schedulePanel.initElements();
            this.ticketPanel.initElements();


            this.regPanel.setVisible(true);
            this.newClientPanel.setVisible(false);
            this.schedulePanel.setVisible(false);
            this.ticketPanel.setVisible(false);

            //cashier registration
            this.regPanel.goToScheduleButton.addActionListener((e) -> {

                cashier = new Cashier(this.regPanel.nameField.getText(), this.regPanel.surnameField.getText());

                this.regPanel.setVisible(false);
                this.newClientPanel.setVisible(true);
                this.schedulePanel.setVisible(false);
                //this.hallPanel.setVisible(false);
                this.ticketPanel.setVisible(false);
            });

            //client registration
            this.newClientPanel.goToScheduleButton.addActionListener((e)->{

                client = new Client(this.newClientPanel.nameField.getText(), this.newClientPanel.surnameField.getText());

                this.regPanel.setVisible(false);
                this.newClientPanel.setVisible(false);
                this.schedulePanel.setVisible(true);
                //this.hallPanel.setVisible(false);
                this.ticketPanel.setVisible(false);
            });


            // session choosing buttons
            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmNumButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                    order =new Order();
                    this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                    this.ticketPanel.setClientName(this.client.getName());
                    this.ticketPanel.setClientSurname(this.client.getSurname());
                    this.ticketPanel.setCashierName(cashier.getName());
                    this.ticketPanel.setCashierSurname(cashier.getSurname());

                    this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                    this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                    this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                    this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                    this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                    this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                    this.hallPanel.initElements();
                    this.backgroundPanel.add(this.hallPanel);

                    this.regPanel.setVisible(false);
                    this.schedulePanel.setVisible(false);
                    this.hallPanel.setVisible(true);
                    this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);


                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);



                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            try {
                                hall.setSeatBusy( rowNumber, placeNumber);
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }
                    });
                });

            }

            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmNameButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        order =new Order();
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                        this.ticketPanel.setClientName(this.client.getName());
                        this.ticketPanel.setClientSurname(this.client.getSurname());
                        this.ticketPanel.setCashierName(cashier.getName());
                        this.ticketPanel.setCashierSurname(cashier.getSurname());

                        this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                        this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                        this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                        this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                        this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                        this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                        this.hallPanel.initElements();
                        this.backgroundPanel.add(this.hallPanel);

                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(true);
                        this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);
                            hall.setSeatBusy( rowNumber, placeNumber);

                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }

                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                    });
                });

            }

            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmDateButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        order =new Order();
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                        this.ticketPanel.setClientName(this.client.getName());
                        this.ticketPanel.setClientSurname(this.client.getSurname());
                        this.ticketPanel.setCashierName(cashier.getName());
                        this.ticketPanel.setCashierSurname(cashier.getSurname());

                        this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                        this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                        this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                        this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                        this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                        this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                        this.hallPanel.initElements();
                        this.backgroundPanel.add(this.hallPanel);

                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(true);
                        this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);
                            hall.setSeatBusy( rowNumber, placeNumber);

                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }

                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                    });
                });

            }

            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmDurationButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        order =new Order();
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                        this.ticketPanel.setClientName(this.client.getName());
                        this.ticketPanel.setClientSurname(this.client.getSurname());
                        this.ticketPanel.setCashierName(cashier.getName());
                        this.ticketPanel.setCashierSurname(cashier.getSurname());

                        this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                        this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                        this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                        this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                        this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                        this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                        this.hallPanel.initElements();
                        this.backgroundPanel.add(this.hallPanel);

                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(true);
                        this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);
                            hall.setSeatBusy( rowNumber, placeNumber);

                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }

                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                    });
                });

            }

            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.filmStartTimeButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        order =new Order();
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                        this.ticketPanel.setClientName(this.client.getName());
                        this.ticketPanel.setClientSurname(this.client.getSurname());
                        this.ticketPanel.setCashierName(cashier.getName());
                        this.ticketPanel.setCashierSurname(cashier.getSurname());

                        this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                        this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                        this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                        this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                        this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                        this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                        this.hallPanel.initElements();
                        this.backgroundPanel.add(this.hallPanel);

                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(true);
                        this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);
                            hall.setSeatBusy( rowNumber, placeNumber);

                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }

                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                    });
                });

            }

            for (int i = 0; i < schedulePanel.size; i++) {
                int finalI = i;
                this.schedulePanel.orderPriceButton[i].addActionListener((e) -> {
                    int j = finalI + 1;

                    try {
                        order =new Order();
                        this.hallPanel = new HallPanel(WIDTH, HEIGHT, Color.DARK_GRAY, j);

                        this.ticketPanel.setClientName(this.client.getName());
                        this.ticketPanel.setClientSurname(this.client.getSurname());
                        this.ticketPanel.setCashierName(cashier.getName());
                        this.ticketPanel.setCashierSurname(cashier.getSurname());

                        this.ticketPanel.setFilmName(schedule.session.get(j).getFilmName());
                        this.ticketPanel.setFilmDuration(schedule.session.get(j).getFilmDuration());
                        this.ticketPanel.setFilmStartTime(schedule.session.get(j).getFilmStartTime());
                        this.ticketPanel.setStartDate(schedule.session.get(j).getDate());
                        this.ticketPanel.setOrderPrice(schedule.session.get(j).getOrderPrice());
                        this.ticketPanel.setSessionNumber(schedule.session.get(j).getSessionNumber());

                        this.hallPanel.initElements();
                        this.backgroundPanel.add(this.hallPanel);

                        this.regPanel.setVisible(false);
                        this.schedulePanel.setVisible(false);
                        this.hallPanel.setVisible(true);
                        this.ticketPanel.setVisible(false);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.hallPanel.regButton.addActionListener((k) -> {

                        rowNumber = Integer.parseInt(this.hallPanel.rowField.getText());
                        placeNumber = Integer.parseInt(this.hallPanel.numberField.getText());

                        try {
                            Hall hall = new Hall(j);
                            if (hall.getSeatStatus(rowNumber, placeNumber)) throw new SetSeatBusyException("you chose booked place", true);
                            hall.setSeatBusy( rowNumber, placeNumber);

                            order.createOrder(schedule.session.get(j).getFilmName(), schedule.session.get(j).getSessionNumber(),
                                    schedule.session.get(j).getDate(), schedule.session.get(j).getFilmStartTime(), schedule.session.get(j).getFilmDuration(),
                                    schedule.session.get(j).getOrderPrice(), this.rowNumber, this.placeNumber, this.client.getName(),
                                    this.client.getSurname(),cashier.getName(), cashier.getSurname());

                            this.ticketPanel.setPlaceNumber(placeNumber);
                            this.ticketPanel.setRowNumber(rowNumber);

                            this.ticketPanel.updateElements();

                            this.regPanel.setVisible(false);
                            this.schedulePanel.setVisible(false);
                            this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(true);

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }catch(SetSeatBusyException | IOException ep){
                            ep.printStackTrace();//виводить System.err
                            this.hallPanel.problemLabel();
                        }

                        this.ticketPanel.makePaymentPrintTicket.addActionListener((es) -> {
                            order.writeOrderToList();
                            this.regPanel.setVisible(false);
                            this.newClientPanel.setVisible(true);
                            this.schedulePanel.setVisible(false);
                            //this.hallPanel.setVisible(false);
                            this.ticketPanel.setVisible(false);
                            this.order = null;
                        });
                    });
                });

            }

            this.menuBar.ask2.addActionListener((e)-> {

                Schedule shc;
                try {
                    Ask2 ask2 = new Ask2(300, 200);
                    shc = new Schedule();

                    ask2.initElements(shc);

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                shc = null;
            });

            this.menuBar.ask1.addActionListener((e)->{
                Schedule shc;
                Ask1 ask1 = new Ask1(200, 300);
                try {
                    shc = new Schedule();
                    ask1.initElements(shc);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                shc = null;
            });

            this.menuBar.ask3.addActionListener((e)->{
                    Schedule shc;
                    Ask3 ask3 = new Ask3(400, 300);
                try {
                    shc = new Schedule();
                    ask3.initElements(shc);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                shc = null;
            });

            this.menuBar.ask4.addActionListener((e)->{
                Schedule shc;
                Ask4 ask4 = new Ask4(400, 300);
                try {
                    shc = new Schedule();
                    ask4.initElements(shc);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                shc = null;
            });

            this.menuBar.ask5.addActionListener((e)->{
                Schedule shc;
                Ask5 ask5 = new Ask5(300,200);
                try {
                    shc = new Schedule();
                    ask5.initElements(shc);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                shc = null;
            });

            this.menuBar.ask6.addActionListener((e)->{
                try {
                    Schedule shc= new Schedule();
                    Ask6 ask6 = new Ask6(400, 300, shc);
                    ask6.init();
                    shc = null;
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            });

        }catch(NullPointerException e){
            e.printStackTrace();
            System.err.println(e);
        }
    }

    private static class MenuBar extends JMenuBar{


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



        this.info.add(ask1);
        this.info.add(ask2);
        this.info.add(ask3);
        this.info.add(ask4);
        this.info.add(ask5);
        this.info.add(ask6);


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
    private static class NewClientPanel extends AbstractPanel{

        JTextField nameField = new JTextField();

        JTextField surnameField = new JTextField();

        JButton goToScheduleButton = new JButton();

        NewClientPanel(int width, int height, Color color){
            super(width, height, color);
        }

        public void initElements(){

            this.setLayout(null);
            this.nameField.setText("Print client name here: ");
            this.surnameField.setText("Print client surname here: ");
            this.goToScheduleButton.setText("Next");

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
        JButton[][] buttons = new JButton[rows][number];

        JTextField rowField = new JTextField("You row:");
        JTextField numberField = new JTextField("You place number:");
        JButton regButton = new JButton("Register");

        JLabel problem = new JLabel("You chose busy place!!!");

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
                    buttons[i][j] = new JButton();
                    x = j*80;
                    buttons[i][j].setBounds(x, y, 80, 80);

                    if(hall.getSet(i, j).getBusy()){
                        buttons[i][j].setBackground(Color.RED);
                    }

                    this.add(buttons[i][j]);
                }
            }
            rowField.setBounds(10, 400, 120, 20);
            numberField.setBounds(10, 425 , 120, 20);
            regButton.setBounds(150, 400, 100, 45);
            problem.setBounds(400,400, 200, 20 );
            this.add(rowField);
            this.add(numberField);
            this.add(regButton);
            this.add(problem);
            this.problem.setVisible(false);

            this.setVisible(true);
        }
        protected void problemLabel(){

            this.problem.setVisible(true);
        }

        void setSeatBusy(int row, int number) throws FileNotFoundException {
                hall.setSeatBusy(row, number);
        }
    }

    private static class TicketPanel extends AbstractPanel{
        String cinemaName = "Cinema City";
        String cinemaAddress = "Schevchenko 32";
        String cinemaPhoneNumber = "0679281972";
        JButton makePaymentPrintTicket = new JButton();
        String filmName, startDate, clientName, clientSurname, cashierName, cashierSurname;
        double filmStartTime, filmDuration;
        int orderPrice, rowNumber, placeNumber, sessionNumber;
        JLabel cinemaNameLabel= new JLabel();;
        JLabel filmNameLabel= new JLabel();;
        JLabel filmDate= new JLabel();;
        JLabel filmStartTimeLabel= new JLabel();;
        JLabel filmDurationLabel= new JLabel();;
        JLabel orderPriceLabel= new JLabel();;
        JLabel seatPlace= new JLabel();;
        JLabel cinemaAddressLabel= new JLabel();;
        JLabel cinemaPhoneNumberLabel= new JLabel();;
        JLabel cashierInitials= new JLabel();;
        JLabel orderID= new JLabel();;
        Order order = new Order();
        TicketPanel(int width , int height ,  Color color){super(width, height, color);}


        public void initElements(){
            this.setLayout(null);


            cinemaNameLabel.setBounds(350, 0, 500, 50);
            filmNameLabel.setBounds(10, 50 , 500, 50);
            filmDate.setBounds(10, 100, 500, 50);
            filmStartTimeLabel.setBounds(10, 150, 500, 50);
            filmDurationLabel.setBounds(10,200, 500, 50);
            orderPriceLabel.setBounds(10, 250, 500, 50);
            seatPlace.setBounds(10, 300, 500 ,50);
            cinemaAddressLabel.setBounds(10, 350, 500 ,50);
            cinemaPhoneNumberLabel.setBounds(10, 400 ,500 ,50);
            cashierInitials.setBounds(10, 450, 500, 50);
            orderID.setBounds(10, 500 ,500, 50);
            makePaymentPrintTicket.setBounds(600, 450, 200, 50);

            this.add(cinemaNameLabel);
            this.add(filmNameLabel);
            this.add(filmDate);
            this.add(filmStartTimeLabel);
            this.add(filmDurationLabel);
            this.add(orderPriceLabel);
            this.add(seatPlace);
            this.add(cinemaAddressLabel);
            this.add(cinemaPhoneNumberLabel);
            this.add(cashierInitials);
            this.add(orderID);
            this.add(makePaymentPrintTicket);

            this.setVisible(true);
        }

        void updateElements(){
            cinemaNameLabel.setText(this.cinemaName);
            filmNameLabel.setText("Film: "+this.filmName);
            filmDate.setText("Date: "+this.startDate);
            filmStartTimeLabel.setText("Start time: "+Double.toString(this.filmStartTime));
            filmDurationLabel.setText("Film duration: "+ Double.toString(this.filmDuration));
            orderPriceLabel.setText("Ticket price: "+Integer.toString(this.orderPrice));
            seatPlace.setText("You row:"+ Integer.toString(rowNumber) + " "+ "You place:"+ Integer.toString(placeNumber));
            cinemaAddressLabel.setText("Our cinema address: " + this.cinemaAddress);
            cinemaPhoneNumberLabel.setText("Our phone: " + this.cinemaPhoneNumber);
            cashierInitials.setText("Cashier: "+ this.cashierName+" "+this.cashierSurname);
            orderID.setText("");
            makePaymentPrintTicket.setText("Make Payment & Print Ticket");
        }

        void writeOrderToList() throws IOException {


        }

        //film specifications
        public void setFilmName(String filmName) {
            this.filmName = filmName;
        }
        public void setFilmDuration(double filmDuration) {
            this.filmDuration = filmDuration;
        }
        public void setFilmStartTime(double filmStartTime) {
            this.filmStartTime = filmStartTime;
        }
        public void setStartDate(String startDate){this.startDate = startDate;}
        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
        }
        public void setPlaceNumber(int placeNumber) {
            this.placeNumber = placeNumber;
        }
        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }
        public void setSessionNumber(int sessionNumber) {
            this.sessionNumber = sessionNumber;
        }

        //cashier info
        public void setCashierName(String cashierName) {
            this.cashierName = cashierName;
        }
        public void setCashierSurname(String cashierSurname) {
            this.cashierSurname = cashierSurname;
        }

        //client info
        public void setClientName(String clientName) {
            this.clientName = clientName;
        }
        public void setClientSurname(String clientSurname) {
            this.clientSurname = clientSurname;
        }
    }


    private static class Ask1 extends JFrame{

        JLabel label ;
        ManagerInfo managerInfo;


        Ask1(int width , int height ){
            super("Number of films");

            //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("Number of films");
        }

        void initElements(Schedule schedule) throws FileNotFoundException {
            this.managerInfo = new ManagerInfo();
            this.label = new JLabel("Total number of viewers: " + managerInfo.showNumberOfClients(schedule));

            this.label.setBounds(350, 250, 300, 50);

            this.add(this.label);
            this.setVisible(true);

        }
    }
    private static class Ask2 extends JFrame{

        JLabel label ;
        ManagerInfo managerInfo;


        Ask2(int width , int height ){
            super("Total number of viewers");

            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("Total number of viewers");
        }

        void initElements(Schedule schedule) throws FileNotFoundException {
            this.managerInfo = new ManagerInfo();
            this.label = new JLabel("Number of films: " + managerInfo.showNumberOfFilms(schedule));

            this.label.setBounds(350, 250, 300, 50);

            this.add(this.label);
            this.setVisible(true);

        }
    }
    private static class Ask3 extends JFrame{


        JLabel label ;
        ManagerInfo managerInfo;

        JTextField textField;

        JButton button;


        Ask3(int width , int height ){
            super("The number of viewers of the specified film");

            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("The number of viewers of the specified film");
        }

        void initElements(Schedule schedule) throws FileNotFoundException {
            this.setLayout(null);
            this.managerInfo = new ManagerInfo();
            this.button = new JButton("Find info");
            this.textField = new JTextField("Print film name here:");

            this.label = new JLabel();

            this.button.addActionListener((e)-> {
                try {
                    this.label.setText("The number of viewers of the specified film: " + managerInfo.filmNumberOfViewers(textField.getText(), schedule));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            this.textField.setBounds(0, 0, 300, 50);
            this.button.setBounds(0, 50, 300, 50);
            this.label.setBounds(0, 100, 300, 50);

            this.add(this.textField);
            this.add(this.button);
            this.add(this.label);

            this.setVisible(true);

        }
    }
    private static class Ask4 extends JFrame{

        JLabel label ;
        ManagerInfo managerInfo;


        Ask4(int width , int height ){
            super("The movie that has the most demand");

            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("The movie that has the most demand");
        }

        void initElements(Schedule schedule) throws FileNotFoundException {
            this.managerInfo = new ManagerInfo();
            this.label = new JLabel("The most demand movie: " + managerInfo.mostPopularFilm(schedule));

            this.label.setBounds(350, 250, 300, 50);

            this.add(this.label);
            this.setVisible(true);

        }
    }
    private static class Ask5 extends JFrame {
        JLabel label;
        ManagerInfo managerInfo;


        Ask5(int width, int height) {
            super("Total amount from ticket sales");

            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.pack();

            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("Total amount from ticket sales");
        }

        void initElements(Schedule schedule) throws FileNotFoundException {
            this.managerInfo = new ManagerInfo();
            this.label = new JLabel("Total amount from ticket sales: " + managerInfo.sumCash(schedule));

            this.label.setBounds(350, 250, 300, 50);

            this.add(this.label);
            this.setVisible(true);
        }
    }
    private static class Ask6 extends JFrame{
        JLabel label ;
        ManagerInfo managerInfo;

        JTextField textField;

        JButton button;

        Schedule schedule;

        int i;
        Ask6(int width , int height ,Schedule schedule){
            super("The number of spectators who purchased tickets for the specified time");

            this.pack();
            this.schedule = schedule;
            //Frame size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, width, height);
            this.setTitle("The number of viewers of the specified film");
        }
        void init(){
            this.setLayout(null);
            this.managerInfo = new ManagerInfo();
            this.button = new JButton("Find info");
            this.textField = new JTextField("Print film number here:");

            this.label = new JLabel();

            this.button.addActionListener((e)-> {
                    this.i = Integer.parseInt(this.textField.getText());
                    this.label.setText("The number of viewers of the specified film: " + schedule.session.get(i).getNumberOfClients());
            });

            this.textField.setBounds(0, 0, 300, 50);
            this.button.setBounds(0, 50, 300, 50);
            this.label.setBounds(0, 100, 300, 50);

            this.add(this.textField);
            this.add(this.button);
            this.add(this.label);

            this.setVisible(true);
        }
    }

}

