package cinema.app;

import java.io.*;
import java.util.*;

public class Schedule implements Cloneable{

    /**
     * Поле масиву усіх сесій у залі
     */
    static public Map<Integer, Session> session = new HashMap<Integer, Session>();

    /**
     * Поле напрямку до файлу у якому ми зберігаємо всі дані про сесії залу
     */
    String path = "resourses/sheldue/scheldue.txt";



    Schedule() throws FileNotFoundException {
        initSessions();
    }

    /**
     * Функція, що виводить у консоль всі сесії з розкладу
     * @throws FileNotFoundException
     */
    public void showSessions() throws FileNotFoundException {

        File file = new File(path);
        if (file.length() == 0) {
            System.out.println("Файл пуст");
        } else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            String [][] sessions = new String[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < sessions.length; i++) {
                System.out.println("");
                String[] s = iter.next();
                for (int j = 0; j < columns; j++) {
                    sessions[i][j] = s[j];
                    switch (j) {
                        case (0) -> System.out.print(sessions[i][j] + " ");
                        case (1) -> System.out.print("Film name: " + sessions[i][j] + " ");
                        case (2) -> System.out.print("Film date: " + sessions[i][j] + " ");
                        case (3) -> System.out.print("Film start time: " + sessions[i][j] + " ");
                        case (4) -> System.out.print("Film duration: " + sessions[i][j] + " ");
                        case (5) -> System.out.print("Ticket price: " + sessions[i][j] + "\u20B4" + " ");
                    }
                }
            }
        scn.close();
        }
    }
    /**
     * Функція у котрій ми будемо зчитувати всі дані про наші сесії з файлу та виводити
     * інформацію про всі доступні сесії
     */
    private void initSessions() throws FileNotFoundException {
        int fNumber = 0;
        String fName ="";
        String fDate = "";
        double fStartTime = 0.0;
        double fDuration = 0.0;
        int fTicketCost = 0;

        File file = new File(path);
        if (file.length() == 0) {
            System.out.println("Файл пуст");
        } else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            String [][] info = new String[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < info.length; i++) {

                String[] s = iter.next();

                for (int j = 0; j < columns; j++) {
                    info[i][j] = s[j];
                    switch (j) {
                        case (1) -> fName = info[i][j];
                        case (2) -> fDate = info[i][j];
                        case (3) -> fStartTime = Double.parseDouble(info[i][j]);
                        case (4) -> fDuration = Double.parseDouble(info[i][j]);
                        case (5) -> fTicketCost = Integer.parseInt(info[i][j]);
                    }


                }
                Hall hall = new Hall(i+1);
                Session ses;
                session.put(i+1, ses = new Session(fNumber = i+1, fName , fDate, fStartTime, fDuration , fTicketCost, hall.getBusyPlaces()));
            }
        scn.close();
        }
    }

    public static <T> HashMap<Integer, Session> deepCopyWorkAround(Map<Integer, Session> original) throws IOException, ClassNotFoundException {
        HashMap<Integer, Session> copy = new HashMap<>();
        for (Map.Entry<Integer, Session> entry : original.entrySet()) {
            Session ses = original.get(entry.getKey()).streamClone();
            copy.put(entry.getKey(), ses);
        }
        return copy;
    }

}


