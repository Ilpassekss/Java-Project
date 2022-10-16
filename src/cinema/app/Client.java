package cinema.app;

public class Client extends Person{

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
     */
    public Client(String name ,String surname ){
        super (name, surname);

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
     *  Сеттер номеру клієнта
     *  @param orderId порядковий номер клієнта
     */
    void setId(int orderId){
        this.id = orderId;
    }

    @Override
    public boolean equal(Object obj) {
        return false;
    }
}
