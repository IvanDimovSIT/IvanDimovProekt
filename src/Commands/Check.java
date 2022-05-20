package Commands;

import Commands.TicketCodes.Decoder;
import com.company.*;

import java.time.LocalDate;

public class Check implements com.company.Check {
    //проверка на валидността на код
    //ако е валиден връщаме мястото
    //в противен случай връщаме null
    @Override
    public Seat checkCode(Schedule schedule, String code) {
        Decode decode = new Decoder();
        int row;
        int seat;
        int hallNumber;
        LocalDate date;
        try {
            decode.decode(code);
        }catch (TicketCodeException e){
            return null;
        }
        row = decode.getRow();
        seat = decode.getSeat();
        hallNumber = decode.getHallNumber();
        date = decode.getDate();

        HallsDay halls = schedule.getHallsForDay(date);
        if(halls == null)return null;
        Hall hall = halls.getHall(hallNumber);
        if(hall == null)return null;
        try {
            Seat seat1 = hall.getSeat(row, seat);
            if(seat1.getSeatState() != SeatState.Bought)
                return null;
            else
                return seat1;
        }catch (SeatException seatException){
            return null;
        }

    }
}
