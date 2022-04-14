package Commands;

import com.company.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FreeSeats implements com.company.FreeSeats {
    @Override
    public List<Seat> listFreeSeats(Schedule schedule, LocalDate date, String name) throws EventsException {
        HallsDay hallsDay = schedule.getHallsForDay(date);
        if(hallsDay==null)throw new EventsException("No Events on this date");
        Hall hall = null;
        for (Hall i: hallsDay.getHalls()) {
            if(i.getShowName()!=null && i.getShowName().equals(name))
                hall = i;
        }
        if(hall == null)throw new EventsException("No \""+name+"\" shows on this date");

        List<Seat> freeSeats = new ArrayList<>();
        for (int row = 0; row < hall.getMaxRows(); row++) {
            for (int seat = 0; seat < hall.getMaxSeats(); seat++) {
                try {
                    if (hall.getSeat(row, seat).getSeatState() == SeatState.Free)
                        freeSeats.add(hall.getSeat(row, seat));
                }catch (SeatException e){
                    e.printStackTrace();
                }
            }
        }

        return freeSeats;
    }
}
