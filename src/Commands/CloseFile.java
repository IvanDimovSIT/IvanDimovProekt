package Commands;

import com.company.Schedule;

public class CloseFile implements com.company.CloseFile {
    //затваряме текущия файл - занулирваме разписанието
    @Override
    public void closeFile(Schedule schedule ,int numberOfHalls, int[] rows, int[] seats) {
        schedule.reset(numberOfHalls, rows, seats);
    }

}
