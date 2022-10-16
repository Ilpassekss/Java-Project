package cinema.app;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class ManagerInfo {




    static int showNumberOfClients() throws FileNotFoundException {
        int countClients = 0;
        Schedule schedule = new Schedule();
        for (int i = 1 ; i<=schedule.session.size(); i++ )
            countClients += schedule.session.get(i).getNumberOfClients();
        return countClients;
    }

    static int showNumberOfFilms() throws FileNotFoundException {

        HashSet<String> h = new HashSet<String>();
        Schedule schedule = new Schedule();
        for (int i = 1 ; i<=schedule.session.size(); i++)
            h.add(schedule.session.get(i).getFilmName());
        return h.size();
    }

    static int filmNumberOfViewers(String filmName) throws FileNotFoundException {
        int countClients = 0;
        boolean k = false;
        Schedule schedule = new Schedule();
        for (int i = 1 ; i<=schedule.session.size(); i++)
            if (filmName.equals(schedule.session.get(i).getFilmName())){
                countClients+= schedule.session.get(i).getNumberOfClients();
            }

    return countClients;
    }

    String mostPopularFilm(){
        return "";
    }

    static int sumCash() throws FileNotFoundException {
        int cash = 0;
        Schedule schedule = new Schedule();
        for (int i = 1 ; i<=schedule.session.size(); i++ )
            cash += schedule.session.get(i).getNumberOfClients() * schedule.session.get(i).getOrderPrice();


        return cash;
    }

    public static  void main(String[]string ) throws FileNotFoundException {
        System.out.println(sumCash());
    }
}
