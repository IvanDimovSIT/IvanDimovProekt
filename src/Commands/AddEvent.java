package Commands;

import com.company.EventsException;
import com.company.Hall;
import com.company.HallsDay;
import com.company.Schedule;

import java.time.LocalDate;

public class AddEvent implements com.company.AddEvent {
    @Override
    public void addEvent(Schedule schedule, LocalDate date, int hallNumber, String name) throws EventsException{

        if(schedule.getHallsForDay(date) == null) schedule.addHallsForDay(date);

        HallsDay hallsDay = schedule.getHallsForDay(date);
        Hall hall = hallsDay.getHall(hallNumber);

        if(hall.getShowName() == null){
            hall.setShowName(name);
        }else throw new EventsException("Can't add show");

    }
}
