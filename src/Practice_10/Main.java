package Practice_10;

public class Main {

    public static void main(String[]str){
        Transport tr1 = new Plane("Boeing", "747", 175000);
        tr1.printInfo();
        Nameable tr = ((Nameable)tr1);
        tr.setClassName("Plane");
        System.out.println(tr.getClassName()+"\n");

        tr1 = new Car("Honda", "Civic", 170);
        tr1.printInfo();

        //Nameable tr2 = ((Nameable) tr1);
        tr.setClassName("Car");
        System.out.println(tr.getClassName());
    }
}
