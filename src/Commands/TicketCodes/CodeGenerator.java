package Commands.TicketCodes;

import Commands.GenerateCode;
import com.company.Seat;

import java.time.LocalDate;

public class CodeGenerator implements GenerateCode {
    @Override
    public String getCode(Seat seat, int hallNumber, LocalDate date) {
        return seat.getRow()+"-"+seat.getPlace()+"-"+hallNumber+"-"+date.getYear()+"-"+
                date.getMonth().getValue()+"-"+date.getDayOfMonth();
    }
}
