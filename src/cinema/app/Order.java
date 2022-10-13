package cinema.app;

import java.io.*;
import java.sql.Time;
import java.util.Calendar;

/**
 *  Клас замовлення(квитка)
 */
public class Order {
    /**
     * Поле назви фільму
     */
    private String filmName;
    /**
     * Поле номеру сесії
     */
    private int sessionNumber;
    /**
     * Поле дати початку фільму
     */
    private String startDate;
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
    private int orderPrice;
    /**
     *  Поле значення ряду в якому знаходиться наше крісло
     */
    private int rowNumber;
    /**
     * Поле номеру місця у ряду в якому знаходиться наше крісло
     */
    private int placeNumber;
    /**
     * Поле з іменем клієнта
     */
    private String clientName;
    /**
     * Поле з прізвищем клієнта
     */
    private String clientSurname;
    /**
     * Поле з іменем касира
     */
    private String cashierName;
    /**
     * Поле з прізвищем касира
     */
    private String cashierSurname;
    /**
     * Поле кількості білетів
     */
    private static int count;
    /**
     * Поле з ID номером квитка
     */
    private int orderID;
    /**
     * Поле напрямку до файлу у якому ми зберігаємо всі дані про усі замолені білети
     */
    private String path = "resourses/orders/orders.txt";

    /**
     *
     * Конструктор об'єкту класу Order в якому ми вираховуємо ID квитка та ініціалізуємо наступні поля:
     * @param filmName назва фільму
     * @param sessionNumber номер сесії
     * @param startDate дата початку фільму
     * @param filmStartTime час старту фільму
     * @param filmDuration тривалість фільму
     * @param orderPrice ціна квитка на фільм
     * @param rowNumber значення ряду в якому знаходиться крісло
     * @param placeNumber номер місця у ряду в якому знаходиться крісло
     * @param clientName ім'я клієнта
     * @param clientSurname прізвище клієнта
     * @param cashierName ім'я касира
     * @param cashierSurname прізвище касира
     */
    Order(String filmName, int sessionNumber, String startDate, double filmStartTime, double filmDuration,
          int orderPrice, int rowNumber, int placeNumber, String clientName, String clientSurname, String cashierName, String cashierSurname) throws IOException {

        this.filmName = filmName;
        this.sessionNumber = sessionNumber;
        this.startDate = startDate;
        this.filmStartTime = filmStartTime;
        this.filmDuration = filmDuration;
        this.orderPrice = orderPrice;
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.cashierName = cashierName;
        this.cashierSurname = cashierSurname;

        count = countNumberOfOrders() + 1;
        orderID = count;


    }

    /**
     * Функція яка підраховує кількість уже замовлених раніше квитків та :
     * @return lines
     */
    private int countNumberOfOrders() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();

        return lines;

    }

    /**
     * Функція яка дописує новий білет у загальний список
     */
    public void writeOrderToList(){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true))); // true добавит новые данные
            out.println(filmName + " " + sessionNumber + " " + startDate + " " + filmStartTime + " " + filmDuration
            + " " + orderPrice + " " + rowNumber + " " + placeNumber + " " + clientName + " " + clientSurname + " " +
                    cashierName + " " + cashierSurname + " " + orderID);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public int getOrderID(){
        return orderID;
    }


}
