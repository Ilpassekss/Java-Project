package cinema.app;

import java.sql.Time;

/**
 *  Класс замовлення(квитка)
 */
public class Order {

    /**
     *  поле сессії на яку оформдене замовлення
     */
    private Session thisSession;
    /**
     *  сидіння у залі
     */
    private Hall seat ;
    /**
     *  ціна квитка на сеанс
     */
    private float price ;
    /**
     *  кількість білетів
     */
    private static long count ;
    /**
     *  id квитка
     */
    private long id ;





    /**
     *  ініціалізація всії замовлень
     */
    void initOrders(){}
    /**
     *  створення новго квитка
     */
    void createOrder(){}
    /**
     *  встановлення ціни квитка
     */
    void setPrice(float price){this.price= price;}
    /**
     *  повернення значення ціни квитку
     */
    float getPrice(){return this.price;}


}
