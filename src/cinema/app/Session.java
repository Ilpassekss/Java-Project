package cinema.app;

import java.io.*;
import java.sql.Time;
import java.util.*;

/**
 *клас Сессії кінотеатру
 */
public class Session implements Serializable, Cloneable{
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
     *@param sessionNumber номер сесії у цьому залі
     *@param filmName назва фільму
     *@param date дата початку фільму
     *@param filmStartTime час старту фільму
     *@param filmDuration тривалість фільму
     *@param orderPrice ціна квитка на фільм
     *@param numberOfClients кількість глядачів у цій сесії
     *
     */
    public Session(int sessionNumber, String filmName, String date, double filmStartTime , double filmDuration,
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
     * Конструктор копіювання
     */
    public Session (Session other) throws FileNotFoundException {
        this(other.sessionNumber, other.filmName, other.date, other.filmStartTime, other.filmDuration, other.orderPrice,
                other.numberOfClients);
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

    @Override
    public String toString() {
        return "Session{" +
                "sessionNumber=" + sessionNumber +
                ", filmName='" + filmName + '\'' +
                ", date='" + date + '\'' +
                ", filmStartTime=" + filmStartTime +
                ", filmDuration=" + filmDuration +
                ", orderPrice=" + orderPrice +
                ", numberOfClients=" + numberOfClients +
                ", sessionID=" + sessionID +
                '}';
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
    /**
     * Функція копіювання серіалізацією
     */
    public Session streamClone() throws IOException, ClassNotFoundException {
        Session ses;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        //зберігаємо стан і закриваємо потік
        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //створюємо та ініціалізуємо
        ses = (Session)ois.readObject();
        return ses;
    }
    /**
     * Стандартне перевизначення функції клонування
     */
    @Override
    public Session clone() throws CloneNotSupportedException {
        return (Session)super.clone();
    }

}
