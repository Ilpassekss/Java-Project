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
     * назва фільму
     */
    private String filmName;

    /**
     * поле дати та часу початку фільму
     */
    Calendar date = new GregorianCalendar();

    /**
     * номер сессії у кінотеатрі
     */
    private int hallSessionNumber;

    /**
     * ціна квитка
     */
    private float price ;

    /**
     *  порядковий номер сессії
     */
    private int id;

    Session(){

    }







    /**
     *  встановлення часу фільму
     */
    void setTime(){}
    /**
     * встановлення ціни квитка
     */
    void setPriceTicket(float price){
        this.price = price;
    }





}
