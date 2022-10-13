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
    private double filmStartTime;
    /**
     * Поле тривалості фільму
     */
    private double filmDuration;
    /**
     * Поле ціни квитка на фільм
     */
    private int orderPrice ;
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
     *@param filmName назва фільму
     *@param date дата початку фільму
     *@param filmStartTime час старту фільму
     *@param filmDuration тривалість фільму
     *@param orderPrice ціна квитка на фільм
     *@param numberOfClients кількість глядачів у цій сесії
     *
     */
    Session(int sessionNumber, String filmName, String date, double filmStartTime , double filmDuration,
            int orderPrice, int numberOfClients) throws FileNotFoundException {

        this.sessionNumber = sessionNumber;
        this.filmName = filmName;
        this.date = date;
        this.filmStartTime = filmStartTime;
        this.filmDuration = filmDuration;
        this.orderPrice = orderPrice;
        this.numberOfClients = numberOfClients;
        count ++;
        sessionID = count ;

    }

    /**
     * Функція у котрій ми повертаємо значення ID номеру сесії
     */
    public int getSessionID(){return sessionID;}

    int getSessionNumber(){return sessionNumber;}

    String getFilmName(){
        return  filmName;
    }

    String getDate(){
        return date;
    }

    double getFilmStartTime(){
        return filmStartTime;
    }

    double getFilmDuration(){
        return filmDuration;
    }

    int getOrderPrice(){
        return orderPrice;
    }

    int getNumberOfClients(){return numberOfClients;}
}
