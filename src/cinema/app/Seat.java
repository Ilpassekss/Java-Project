package cinema.app;

/**
 * Клас сидіння у залі
 */
public class Seat {
    /**
     * Поле значення ряду в якому знаходиться наше крісло
     */
    private int row;

    /**
     * Поле номеру місця у ряду в якому знаходиться наше крісло
     */
    private int number;

    /**
     * Поле стану крісла, за замовчуванням воно false
     */
    private boolean busy = false;


    public Seat(int row, int number){
        this.number = number;
        this.row = row;
    }



    /**
     *  Функція перевірки зайнятості місця у залі
     *  @return busy стан місця
     */
    public boolean getBusy(){return busy;}

    /**
     * Функція встановлення стану місця з false на true
     */
    public void setBusy(){
        if(this.busy==true){
        System.out.println("Someone booked this place earlier");
    }
    else{
        this.busy = true;
    }

    }


}
