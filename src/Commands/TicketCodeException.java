package Commands;

public class TicketCodeException extends Exception{
    public TicketCodeException(){
        super("invalid code");
    }
}
