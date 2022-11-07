package exeption;

public class SetSeatBusyException extends RuntimeException{
    private boolean busyStatus;
    public boolean getBusyStatus(){return  busyStatus;}

    public SetSeatBusyException(String message, boolean busyStatus){
        super("Something went wrong: "+message);
        this.busyStatus = busyStatus;

    }
}
