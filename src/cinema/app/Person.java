package cinema.app;
/**
 *  абстрактний клас людини
 */
public abstract class Person {
    /**
     *  поле з іменем людини
     */
    protected String name;
    /**
     *  поле з прізвищем людини
     */
    protected String surname;

    /**
     *  Конструктор класу людини
     *@param name ім'я людини
     *@param surname прізвище людини
     */
    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    /**
     *  Геттер імені
     */
    abstract String getName();
    /**
     *  Сеттер імені
     *  @param name ім'я людини
     */
    abstract void setName(String name);
    /**
     *  Геттер прізвища
     */
    abstract String getSurname();
    /**
     *  Сеттер прізвища
     *  @param surname прізвище людини
     */
    abstract void setSurname(String surname);

}

/**
 *  клас клієнта кінотеатру
 */
class Client extends Person{

    /**
     *  поле з номером телефону
     */
    private int phoneNumber;
    /**
     *  поле з id клієнта
     */
    private int id;
    /**
     *  Конструктор класу клієнта
     *@param name ім'я людини
     *@param surname прізвище людини
     *@param orderId id номер квитка який дорівнює id клієнта
     */
    public Client(String name ,String surname, int orderId ){
        super (name, surname);
        this.id = orderId;
    }
    /**
     *  Геттер імені клієнта яке буде відображатись у замолені
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
     *  Геттер прізвища клієнта яке буде відображатись у замолені
     */
    String getSurname() {
        return name;
    }
    /**
     *  Сеттер прізвища
     *  @param surname прізвище клієнта
     */
    void setSurname(String surname){
        this.surname = surname;
    }

}

/**
 *   Клас касира в кінотеатрі
 */
class Cashier extends Person {
    private static final int cashierID = 12345;
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
    int phoneNumber;
    /**
     *  поле зі значенням сьогоднішнього чергування
     */
    int shiftAtJob;

    /**
     *  Конструктор класу клієнта
     *@param name ім'я людини
     *@param surname прізвище людини
     *@param jobShift чергування у яке працює касир
     */
    public Cashier(String name, String surname, int jobShift){
        super(name, surname);
        this.shiftAtJob = jobShift;
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
        return name;
    }
    /**
     *  Сеттер прізвища
     *  @param surname прізвище клієнта
     */
    void setSurname(String surname){
        this.surname = surname;
    }




   }

