package Commands;

import com.company.*;

import java.time.LocalDate;

public class AddEvent implements com.company.AddEvent {
    @Override
    public void addEvent(Schedule schedule, LocalDate date, int hallNumber, String name) throws CommandException {
        if(name == null || name.isBlank())
            throw new CommandException("Show name is not entered!");

        if(schedule.getHallsForDay(date) == null) {
            try {
                schedule.addHallsForDay(date);
            } catch (EventsException e) {
                throw new CommandException("Hall's booked!");
            }
        }

        HallsDay hallsDay = schedule.getHallsForDay(date);

        for (Hall i: hallsDay.getHalls()) {
            if(i.getShowName() != null && i.getShowName().equals(name))
                throw new CommandException("Same show on that date!");
        }

        Hall hall = hallsDay.getHall(hallNumber);

        if(hall.getShowName() == null){
            hall.setShowName(name);
        }else throw new CommandException("Hall's booked!");

    }
}
