package Commands;

import java.time.LocalDate;

public class Decoder implements Decode{
    private int row;
    private int seat;
    private int hallNumber;
    private LocalDate date;

    @Override
    public void decode(String code) throws TicketCodeException {
        if(code==null)throw new TicketCodeException();
        String[] codeElements = code.split("-", 4);
        try {
            row = Integer.parseInt(codeElements[0]);
            seat = Integer.parseInt(codeElements[1]);
            hallNumber = Integer.parseInt(codeElements[2]);
            date = LocalDate.parse(codeElements[3]);
        }catch (Exception e){
            throw new TicketCodeException();
        }
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getSeat() {
        return seat;
    }

    @Override
    public int getHallNumber() {
        return hallNumber;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

}
