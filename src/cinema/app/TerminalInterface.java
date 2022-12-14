package cinema.app;

import exeption.SetSeatBusyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TerminalInterface {
    private static boolean programStatus = true;

     static Scanner scn = new Scanner(System.in);
    public static void main(String[]string) throws IOException {


        int jobShift;
        int number;
        System.out.println("Good morning, before starting work, You have to register in system");

        System.out.println("Print your name: ");
        String name = scn.nextLine();
        System.out.println("Print your surname: ");
        String surname = scn.nextLine();
        System.out.println("Print name of your cinema: ");
        String cinemaName = scn.nextLine();
        System.out.println("Your work shift: ");
        String jobShiftStr = scn.nextLine();
        System.out.println("Your adress: ");
        String adress = scn.nextLine();
        System.out.println("Phone number: ");
        String numberStr = scn.nextLine();

        jobShift = Integer.parseInt(jobShiftStr);
        number = Integer.parseInt(numberStr);

        Cashier cashier = new Cashier(name, surname, jobShift, cinemaName, number, adress);




        while (programStatus) {
            Order order = new Order();
            System.out.println("Print client Name: ");
            String clientName = scn.nextLine();
            System.out.println("Print client Surname: ");
            String clientSurname = scn.nextLine();
            Client client = new Client(clientName, clientSurname);
            System.out.println("Today`s schedule: ");

            Schedule sch = new Schedule();
            sch.showSessions();

            System.out.println("");
            System.out.println("Choose your session");

            String sessionNumber = scn.nextLine();
            int sesNumber = Integer.parseInt(sessionNumber);
            Hall hall = new Hall(sesNumber);
            sch.session.get(sesNumber);

            int rowInt ;
            int numInt ;
            while(true) {
                System.out.println("Choose row: ");
                String row = scn.nextLine();
                System.out.println("Choose seat number: ");
                String num = scn.nextLine();


                rowInt = Integer.parseInt(row);
                numInt = Integer.parseInt(num);



                try{
                    if (hall.getSeatStatus(rowInt, numInt)) throw new SetSeatBusyException("you chose booked place", true);
                    hall.setSeatBusy(rowInt, numInt);

                }catch(SetSeatBusyException e){
                    e.printStackTrace();//???????????????? System.err

                    continue;

                }
                break;
            }
            order.createOrder(sch.session.get(sesNumber).getFilmName(), sch.session.get(sesNumber).getSessionNumber(),
                    sch.session.get(sesNumber).getDate(), sch.session.get(sesNumber).getFilmStartTime(), sch.session.get(sesNumber).getFilmDuration(),
                    sch.session.get(sesNumber).getOrderPrice(), rowInt, numInt, cashier.getName(), cashier.getSurname(),
                    client.getName(), client.getSurname());
            order.writeOrderToList();
            client.setId(order.getOrderID());

            //close();
        }

        scn.close();
    }

private static void close(){
    System.out.println("If you want to ,create new order print 1 if you finish work 0: ");
    String breakKey = scn.nextLine();
    int breakKeyInt  = Integer.parseInt(breakKey);
    if(breakKeyInt==1){}
    if (breakKeyInt== 0){
        programStatus= false;
    }
}


}
