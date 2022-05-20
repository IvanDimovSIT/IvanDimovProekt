package Commands;

import com.company.*;

import java.time.LocalDate;

public class AddEvent implements com.company.AddEvent {
    //добавяне на събитие
    @Override
    public void addEvent(Schedule schedule, LocalDate date, int hallNumber, String name) throws CommandException {
        //проверяваме за валидни данни
        if(name == null || name.isBlank())
            throw new CommandException("Show name is not entered!");

        if(schedule.getHallsForDay(date) == null) {
            try {
                schedule.addHallsForDay(date);
            } catch (EventsException e) {
                throw new CommandException("Hall's booked!");
            }
        }
        //намираме залата за съответната дата
        HallsDay hallsDay = schedule.getHallsForDay(date);

        for (Hall i: hallsDay.getHalls()) {
            if(i != null && i.getShowName() != null && i.getShowName().equals(name))
                throw new CommandException("Same show on that date!");
        }

        Hall hall = hallsDay.getHall(hallNumber);

        if(hall != null)
            throw new CommandException("Hall's booked!");

        try {
            hallsDay.add(hallNumber, name);
        } catch (EventsException e) {
            e.printStackTrace();
        }

    }
}

