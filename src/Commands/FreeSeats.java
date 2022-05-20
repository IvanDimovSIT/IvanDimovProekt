package Commands;

import com.company.*;

import java.time.LocalDate;
import java.util.List;

public class FreeSeats implements com.company.FreeSeats {
    //намиране на свободните места
    @Override
    public List<Seat> listFreeSeats(Schedule schedule, LocalDate date, String name) throws EventsException {
        HallsDay hallsDay = schedule.getHallsForDay(date);
        if(hallsDay==null)throw new EventsException("No Events on this date");
        Hall hall = null;
        for (Hall i: hallsDay.getHalls()) {
            if(i != null && i.getShowName()!=null && i.getShowName().equals(name))
                hall = i;
        }
        if(hall == null)throw new EventsException("No \""+name+"\" shows on this date");

        FindSeats freeSeats = new ListSeats();

        return freeSeats.findSeats(hall, SeatState.Free);
    }
}

