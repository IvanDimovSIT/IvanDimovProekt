package Commands;

import com.company.*;

import java.time.LocalDate;

public class Unbook implements com.company.Unbook {
    //премахване на запазено място
    @Override
    public void unbook(Schedule schedule, int row, int seat, LocalDate date, String name) throws CommandException {
        //намираме мястото или даваме грешка при неправилни входни данни
        HallsDay hallsDay = schedule.getHallsForDay(date);
        if(hallsDay == null)throw new CommandException("No events on that date");
        Hall hall = null;
        for (Hall i: hallsDay.getHalls()) {
            if(i != null && i.getShowName()!=null && i.getShowName().equals(name))
                hall = i;
        }
        if(hall == null)throw new CommandException("show \""+name+"\" not found!");
        Seat seat1;
        try {
            seat1 = hall.getSeat(row, seat);
        } catch (SeatException e) {
            throw new CommandException("This seat doesn't exit!");
        }
        if(seat1.getSeatState() != SeatState.Booked)
            throw new CommandException("Seat's not booked!");
        //отбелязваме мястото като свободно
        seat1.setSeatState(SeatState.Free);
        seat1.setNote(null);
    }
}

