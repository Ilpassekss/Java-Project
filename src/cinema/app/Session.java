package cinema.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.*;

/**
 *клас Сессії кінотеатру
 */
public class Session {
    //info for date
    /**
     * Поле номеру сесії
     */
    int sessionNumber;
    /**
     * Поле назви фільму
     */
    private String filmName;

    /**
     * Поле дати початку фільму
     */
    private String date;
    /**
     * Поле часу старту фільму
     */
    private float filmStartTime;
    /**
     * Поле тривалості фільму
     */
    private float filmDuration;
    /**
     * Поле ціни квитка на фільм
     */
    private float orderPrice ;
    /**
     * Поле кількості глядачів у цій сесії
     */
    private int numberOfClients;
    /**
     * Поле кількості сеансів
     */
    private static int count = 0;
    /**
     * Поле ID цієї сесії
     */
    private int sessionID;
    /**
     * Конструктор об'єкту класу сесії в якому ми ініціалізуємо наступні поля:
     * @param sessionNumber номер сесії у цьому залі
     * @param filmName назва фільму
     * @param date дата початку фільму
     * @param filmStartTime час старту фільму
     * @param filmDuration тривалість фільму
     * @param orderPrice ціна квитка на фільм
     * @param numberOfClients кількість глядачів у цій сесії
     *
     */
    Session(int sessionNumber, String filmName, String date, float filmStartTime, float filmDuration, float orderPrice, int numberOfClients){
        this.sessionNumber = sessionNumber;
        this.filmName = filmName;
        this.date = date;
        this.filmStartTime = filmStartTime;
        this.filmDuration = filmDuration;
        this.orderPrice = orderPrice;
        this.numberOfClients = numberOfClients;

        count++;
        sessionID = count;


    }

    public static void main(String[] str) throws FileNotFoundException {
        File file = new File("resourses/sheldue/scheldue.txt");
        if (file.length() == 0) {
            System.out.println("Файл пуст");
        } else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            String [][] arr = new String[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < arr.length; i++) {
                System.out.println("");
                String[] s = iter.next();
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = s[j];
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
    }

    /**
     * Функція у якій при замовленні нового квитка ми ітеруємо загальну кількість глядачів у цій сесії
     */
    public void iterateNewOrder(){}

    /**
     * Функція у котрій ми повертаємо значення ID номеру сесії
     */
    public int getSessionID(){return sessionID;}
}
