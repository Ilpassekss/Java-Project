package cinema.app;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *клас Сессії кінотеатру
 */
public class Session {
    //info for date


    /**
     * Поле назви фільму
     */
    private String filmName;

    /**
     * Поле дати початку фільму
     */
    private Calendar date = new GregorianCalendar();
    /**
     * Поле часу старту фільму
     */
    private int filmStartTime;
    /**
     * Поле тривалості фільму
     */
    private int filmDuration;
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
     * @param filmName назва фільму
     * @param date дата початку фільму
     * @param filmStartTime час старту фільму
     * @param filmDuration тривалість фільму
     * @param orderPrice ціна квитка на фільм
     * @param numberOfClients кількість глядачів у цій сесії
     *
     */
    Session(String filmName, Calendar date, int filmStartTime, int filmDuration, float orderPrice, int numberOfClients){
        this.filmName = filmName;
        this.date = date;
        this.filmStartTime = filmStartTime;
        this.filmDuration = filmDuration;
        this.orderPrice = orderPrice;
        this.numberOfClients = numberOfClients;

        count++;
        sessionID = count;
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
