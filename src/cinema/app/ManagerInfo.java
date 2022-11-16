package cinema.app;

import java.awt.*;
import java.awt.List;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ManagerInfo {




     int showNumberOfClients(Schedule schedule) throws FileNotFoundException {
        int countClients = 0;

        for (int i = 1 ; i<=schedule.session.size(); i++ )
            countClients += schedule.session.get(i).getNumberOfClients();
        return countClients;
    }

     int showNumberOfFilms(Schedule schedule) throws FileNotFoundException {

        HashSet<String> h = new HashSet<String>();

        for (int i = 1 ; i<=schedule.session.size(); i++)
            h.add(schedule.session.get(i).getFilmName());
        return h.size();
    }

     int filmNumberOfViewers(String filmName, Schedule schedule) throws FileNotFoundException {
        int countClients = 0;
        boolean k = false;

        for (int i = 1 ; i<=schedule.session.size(); i++)
            if (filmName.equals(schedule.session.get(i).getFilmName())){
                countClients+= schedule.session.get(i).getNumberOfClients();
            }

    return countClients;
    }

     String mostPopularFilm(Schedule schedule) throws FileNotFoundException {

        HashMap<Integer, String> hashMap = new HashMap<>();
        int arr[] = new int[schedule.session.size()];

        for(int i = 1;i<=schedule.session.size(); i++){
            hashMap.put(schedule.session.get(i).getNumberOfClients(), schedule.session.get(i).getFilmName());
        }
        for (int i = 0; i<schedule.session.size(); i++){
            int j= i+1;
            arr[i] = schedule.session.get(j).getNumberOfClients();
        }

        IntStream intStream = Arrays.stream(arr);
        OptionalInt optionalInt = intStream.max();
        int maxAsInt = optionalInt.getAsInt();


         return hashMap.get(maxAsInt);
    }

     int sumCash(Schedule schedule) throws FileNotFoundException {
        int cash = 0;
        for (int i = 1 ; i<=schedule.session.size(); i++ )
            cash += schedule.session.get(i).getNumberOfClients() * schedule.session.get(i).getOrderPrice();


        return cash;
    }



}
