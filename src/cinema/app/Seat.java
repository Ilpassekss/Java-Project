package cinema.app;

/**
 * Клас сидіння у залі
 */
public class Seat {
    /**
     * @param row значення ряду в якому знаходиться наше крісло
     */
    private int row;
    /**
     * @param number номер місця у ряду в якому знаходиться наше крісло
     */
    private int number;
    /**
     * @param busy визначає чи є наше місце занятим чи ні
     */
    private boolean busy = false;


    public Seat(int row, int number){
        this.number = number;
        this.row = row;

        //count ++;
        //id = count;
    }

    public Seat(){}

    /**
     *
     * @return busy
     * для перевірки стану місця
     */
    public boolean getBusy(){return busy;}

    /**
     * Перевіряє чи не є місце занятим та встановлює його зайнятим
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
