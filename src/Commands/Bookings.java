package Commands;

import com.company.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bookings implements com.company.Bookings {

    private List<Seat> getAllBookedSeats(Schedule schedule){
        List<Seat> bookedSeats = new ArrayList<>();
        FindSeats findBooked = new ListSeats();
        for (Map.Entry<LocalDate, HallsDay> i:schedule.getHalls().entrySet()) {
            for (Hall j:i.getValue().getHalls()) {
                if(j==null || j.getShowName() == null || j.getShowName().isEmpty())continue;
                    bookedSeats.addAll(findBooked.findSeats(j, SeatState.Booked));
            }
        }
        return bookedSeats;
    }

    private List<Seat> getBookedSeatsDay(Schedule schedule, LocalDate date)throws CommandException{
        List<Seat> bookedSeats = new ArrayList<>();
        FindSeats findBooked = new ListSeats();
        if(!schedule.getHalls().containsKey(date))throw new CommandException("No shows on that date!");
        for (Hall i: schedule.getHallsForDay(date).getHalls()) {
            if(i==null|| i.getShowName() == null || i.getShowName().isEmpty())continue;
            bookedSeats.addAll(findBooked.findSeats(i, SeatState.Booked));
        }
        return bookedSeats;
    }

    private List<Seat> getBookedSeatsDayName(Schedule schedule, LocalDate date, String name)throws CommandException{
        FindSeats findBooked = new ListSeats();
        if(!schedule.getHalls().containsKey(date))throw new CommandException("No shows on that date!");
        HallsDay halls = schedule.getHallsForDay(date);
        Hall hall = null;
        for (Hall i: halls.getHalls()) {
            if(i != null && i.getShowName() != null && i.getShowName().equals(name))
                hall = i;
        }
        if(hall == null)throw new CommandException("Show \"" + name + "\" not found on that day!");
        return findBooked.findSeats(hall, SeatState.Booked);
    }

    @Override
    public List<Seat> getBookedSeats(Schedule schedule, LocalDate date, String name) throws CommandException {
        if(date == null)
            return getAllBookedSeats(schedule);
        else if(name == null)
            return getBookedSeatsDay(schedule, date);
        else
            return getBookedSeatsDayName(schedule, date, name);
    }
}
