package Practice_10;

public class Plane implements Nameable, Transport {

    String className ;

    String modelName;
    String developerName;
    int enginePower;
    Plane(String developerName, String modelName, int enginePower){
        this.developerName = developerName;
        this.modelName = modelName;
        this.enginePower = enginePower;
    }

    @Override
    public void printInfo() {
        System.out.println("Plane developer: "+this.developerName+"\n"+"Plane model: "+this.modelName+"\n"+ "Plane engine power: "+this.enginePower+"hp");
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
