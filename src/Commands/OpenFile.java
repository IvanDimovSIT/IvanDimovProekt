package Commands;

import com.company.CommandException;
import com.company.Schedule;

import java.io.FileNotFoundException;

public class OpenFile implements com.company.OpenFile {
    @Override
    public void open(Schedule schedule ,String fileName) throws CommandException {
        com.company.ScheduleDAO scheduleDAO = new XML.ScheduleDAO();
        Schedule schedule1 = null;
        try {
            schedule1 = scheduleDAO.loadFromFile(fileName);
        } catch (FileNotFoundException e) {
            throw new CommandException("File not found!");
        }
        schedule.setHalls(schedule1.getHalls());
        schedule.setNumberOfHalls(schedule1.getNumberOfHalls());
        schedule.setHallRows(schedule1.getHallRows());
        schedule.setHallSeats(schedule1.getHallSeats());
    }
}
