package cinema.app;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * Клас Зали у кінотеатрі який складається з окремих сидінь
 */
public class Hall{
    /**
     *  Поле значення кількості рядів у залі
     */
    private static final int rows = 3;

    /**
     * Поле значення кількості місць у кожному ряду
     */
    private static final int numberOfSeats = 10;

    /**
     *  Поле значення того яка зараз сесія у залі
     */
    private int hallSessionNumber ;

    /**
     *  Поле масиву всіх крісел у залі
     */
    private Seat[][] seat = new Seat[rows][numberOfSeats];

    /**
     *  Поле шляху до файлу зі значенням зайнятості місць у залі який визначається в конструкторі за допомогою
     *  поля класу hallSeatNumber
     */
    private String path ;

    /**
     *  Конструктор класу який приймає hallSessionNumber у залі та після цього викликаємо функцію initSeats
     *  для обраного нами сеансу.
     *  @param hallSessionNumber  номер сесії у залі в залежності від якого ми вибираємо номер нашої сесії
     *
     *
     */
    public Hall(int hallSessionNumber) throws FileNotFoundException {
        this.hallSessionNumber = hallSessionNumber;
        switch (this.hallSessionNumber) {
            case (1) -> this.path = "resourses/hall_1/hall_Session_1.txt";
            case (2) -> this.path = "resourses/hall_1/hall_Session_2.txt";
            case (3) -> this.path = "resourses/hall_1/hall_Session_3.txt";
            case (4) -> this.path = "resourses/hall_1/hall_Session_4.txt";
            case (5) -> this.path = "resourses/hall_1/hall_Session_5.txt";
            case (6) -> this.path = "resourses/hall_1/hall_Session_6.txt";
        }
        initSeats();
    }

    /**
     * Функція у котрій бронюємо своє місце
     * У цій функції ми спочатку ініціалізуємо наш масив, встановлюємо значення місця з вільного на зайняте та перезаписуємо
     * наші данні у файл зі станом кожного місця
     * @param row  значення ряду у якому ми займаемо місце.
     * @param number  порядковий номер місця у ряді з ліво на право.
     **/
    void setSeatBusy(int row, int number) throws FileNotFoundException {
        row = row-1;
        number = number-1;
        File file = new File(path);

        if (file.length() == 0) {
            System.out.println("the file is empty");
        }
        else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            int[][] arr = new int[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < arr.length; i++) {
                String[] s = iter.next();
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = Integer.parseInt(s[j]);
                }

                scn.close();
            }

            if ((row >= rows) || (number >= numberOfSeats) || (row < 0) || (number < 0)) {
                getSeatProblem();
            } else {
                arr[row][number] = 1;
                seat[row][number].setBusy();

            }

            File file1 = new File(path);
            PrintWriter pw = new PrintWriter(file1);
            for (int i=0; i<rows; i++){
                if(i>0) pw.println("");

                for(int j=0; j<numberOfSeats; j++){
                    pw.print(arr[i][j]+" ");
                }
            }
            pw.close();
        }
    }

    boolean getSeatStatus(int i, int j){
        i--;j--;
        return seat[i][j].getBusy();
    }

    /**
     * Функція котра повертає значення кількості зайнятих місць у залі
     **/
    public int getBusyPlaces() throws FileNotFoundException {
        int countPlaces = 0;

        File file = new File(path);
        if (file.length() == 0) {
            System.out.println("Файл пуст");
        } else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            int[][] arr = new int[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < arr.length; i++) {
                String[] s = iter.next();
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < numberOfSeats; j++) {
                    if (arr[i][j] == 1) {
                        countPlaces ++;
                    }

                }
            }
        scn.close();
        }
        return countPlaces;
    }

    /**
     *  Ініціалізація кожного сидіння
     *  У цій функції ми заповнюємо наш масив seat об'єктами класу Seat та встановлюємо чи було це місце зайнятим.
     */
    private void initSeats() throws FileNotFoundException {

        File file = new File(this.path);
        if (file.length() == 0) {
            System.out.println("Файл пуст");
        }
        else {
            Scanner scn = new Scanner(file);
            ArrayList<String[]> nums = new ArrayList<>();

            while (scn.hasNext()) {
                nums.add(scn.nextLine().split(" "));
            }

            int columns = nums.get(0).length;
            int[][] arr = new int[nums.size()][columns];
            Iterator<String[]> iter = nums.iterator();
            for (int i = 0; i < arr.length; i++) {
                String[] s = iter.next();
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }

    try {
        for (int i = 0; i < rows+1; i++) {
            for (int j = 0; j < numberOfSeats; j++) {

                if (arr[i][j] == 1) {
                    seat[i][j] = new Seat(i, j);
                    seat[i][j].setBusy();
                }
                if (arr[i][j] == 0) {
                    seat[i][j] = new Seat(i, j);
                }

            }
        }
    }catch (ArrayIndexOutOfBoundsException e){
        System.err.println("Invalid array index");
    }

        scn.close();
        }
    }

    /**
     * Внутрішня функція класу, що виконується при вводу невірних даних у функції setSeatBusy
     */
    private void getSeatProblem(){
        System.out.println("You print wrong row or place");
    }

    public static int getNumberOfSeats() {
        return numberOfSeats;
    }
    public static int getRows() {
        return rows;
    }

    public Seat getSet(int i, int j){
        return seat[i][j];
    }


}
