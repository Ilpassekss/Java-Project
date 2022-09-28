package cinema.app;
/**
 *  абстрактний клас людини
 */
public abstract class Person {
     protected String name;
     protected String surname;
    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    abstract String getName();
    abstract void setName(String name);


}
/**
 *  клас клієнта нашого кінотеатру
 */
class Client extends Person{

    float cash;
    private int phoneNumber;
    private int id;
    public Client(String name ,String surname ){
        super (name, surname);
    }
    String getName() {
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    float getCash(float cash){
        this.cash = cash;
        return this.cash;
    }
}

/**
 *   клас продавца в кинотеатре
 */
class Cashier extends Person {
    private static final int cashierID = 12345;

    public String cinemaName;
    private String adress;
    int phoneNumber;
    int shiftAtJob;

    public Cashier(String name, String surname){
        super(name, surname);
    }

    String getName() {
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    boolean checkCashierID(int printedCashierID){
        boolean confirmation;

        if(cashierID == printedCashierID){
            return confirmation = true;
        }
        else{
            return confirmation = false;
        }
    }

  }

