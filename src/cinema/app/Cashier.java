package cinema.app;

import java.util.Objects;

public class Cashier extends Person {

/**
 *  поле з назвою кінотеатру
 */
public String cinemaName;
/**
 *  поле з адресою касира
 */
private String adress;
        /**
         *  поле з номером телефону касира
         */
        long phoneNumber;
        /**
         *  поле зі значенням сьогоднішнього чергування
         */
        int shiftAtJob;

/**
 *  Конструктор класу клієнта
 *@param name ім'я людини
 *@param surname прізвище людини
 *@param jobShift чергування у яке працює касир
 *@param cinemaName назва кінотеатру
 *@param phoneNumber номер телефону
 *@param adress адреса проживання
 */
public Cashier(String name, String surname, int jobShift,
               String cinemaName, long phoneNumber, String adress){
        super(name, surname);
        this.shiftAtJob = jobShift;
        this.cinemaName = cinemaName;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
}


    @Override
    public boolean equal(Object obj) {
        if (this == obj) return true;
        if(obj==null||getClass()!=obj.getClass()) return false;

        Cashier that = (Cashier) obj;

        return name.equals(that.name) && surname.equals(that.surname) && adress.equals(that.adress)
                && cinemaName.equals(that.cinemaName) && phoneNumber== that.phoneNumber;
    }

    @Override
    public int hashCode() {

    return Objects.hash(name, surname, adress, cinemaName, phoneNumber);
    }

        /**
         *  Геттер імені касира яке буде відображатись у замолені
         */
        String getName() {
    return name;
}
        /**
         *  Сеттер імені
         *  @param name ім'я клієнта
         */
        void setName(String name){
        this.name = name;
        }

        /**
         *  Геттер прізвища касира яке буде відображатись у замолені
         */
        String getSurname() {
        return surname;
        }
        /**
         *  Сеттер прізвища
         *  @param surname прізвище клієнта
         */
        void setSurname(String surname){
        this.surname = surname;
        }
        /**
         *  Геттер зміни у яку працює касир
         */
        int getShiftAtJob(){return shiftAtJob;}


}
