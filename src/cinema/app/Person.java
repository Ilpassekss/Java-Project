package cinema.app;
/**
 *  абстрактний клас людини
 */
public abstract class Person extends Order {
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

    public abstract boolean equal(Object obj);

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







