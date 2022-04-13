package Commands;

import com.company.Schedule;

import java.io.FileNotFoundException;

public class SaveAs implements com.company.SaveAs {
    @Override
    public void saveAs(Schedule schedule, String fileName) {
        com.company.ScheduleDAO scheduleDAO = new XML.ScheduleDAO();
        try {
            scheduleDAO.saveToFile(schedule, fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error when saving");
        }
    }
}
