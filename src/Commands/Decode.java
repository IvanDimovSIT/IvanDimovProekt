package Commands;

import java.time.LocalDate;

public interface Decode {
    void decode(String code)throws TicketCodeException;
    int getRow();
    int getSeat();
    int getHallNumber();
    LocalDate getDate();
}
