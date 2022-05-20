package Commands;

import com.company.CommandException;
import com.company.Schedule;

import java.io.FileNotFoundException;

public class SaveAs implements com.company.SaveAs {
    //запазване на файл
    @Override
    public void saveAs(Schedule schedule, String fileName) throws CommandException {
        com.company.ScheduleDAO scheduleDAO = new XML.ScheduleDAO();
        try {
            scheduleDAO.saveToFile(schedule, fileName);
        } catch (FileNotFoundException e) {
            throw new CommandException("Error when saving!");
        }
    }
}

