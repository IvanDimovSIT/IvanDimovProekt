package Commands;

import com.company.*;

import java.time.LocalDate;

public class Book implements com.company.Book {

    @Override
    public void book(Schedule schedule, int row, int seat, LocalDate date, String name, String note)throws CommandException {
        HallsDay hallsDay = schedule.getHallsForDay(date);
        if(hallsDay == null)throw new CommandException("No events on that date");
        Hall hall = null;
        for (Hall i: hallsDay.getHalls()) {
            if(i.getShowName()!=null && i.getShowName().equals(name))
                hall = i;
        }
        if(hall == null)throw new CommandException("show \""+name+"\" not found!");
        Seat seat1;
        try {
            seat1 = hall.getSeat(row, seat);
        } catch (SeatException e) {
            throw new CommandException("This seat doesn't exit!");
        }
        if(seat1.getSeatState() != SeatState.Free)
            throw new CommandException("Seat's taken!");
        seat1.setSeatState(SeatState.Booked);
        seat1.setNote(note);

    }
}
