package XML;

import com.company.Schedule;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class ScheduleDAO implements com.company.ScheduleDAO {

    @Override
    public void saveToFile(Schedule schedule, String fileName)throws FileNotFoundException {
        XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        encoder.writeObject(schedule);
        encoder.close();
    }

    @Override
    public Schedule loadFromFile(String fileName)throws FileNotFoundException {
        XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        return (Schedule)decoder.readObject();
    }
}
