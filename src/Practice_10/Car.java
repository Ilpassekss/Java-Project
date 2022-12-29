package Practice_10;

public class Car implements Nameable, Transport{

    String className ;
    String modelName;
    String developerName;
    int enginePower;
    int maxSpeed;

    Car(String developerName, String modelName, int enginePower){
        this.developerName = developerName;
        this.modelName = modelName;
        this.enginePower = enginePower;
        //this.maxSpeed = maxSpeed;
    }
    @Override
    public void printInfo() {
        System.out.println("Car developer: "+this.developerName+"\n"+"Car model: "+this.modelName+"\n"+ "Car engine power: "+this.enginePower+"hp");
    }


    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }


}
