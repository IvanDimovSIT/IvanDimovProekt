package Commands;

import com.company.Schedule;

public class CloseFile implements com.company.CloseFile {

    @Override
    public void closeFile(Schedule schedule ,int numberOfHalls, int[] rows, int[] seats) {
        schedule.reset(numberOfHalls, rows, seats);
    }
}
