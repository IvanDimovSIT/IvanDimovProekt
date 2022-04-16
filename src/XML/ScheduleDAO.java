package XML;

import com.company.Schedule;

import java.beans.*;
import java.io.*;
import java.time.LocalDate;

//https://www.edureka.co/blog/serialization-of-java-objects-to-xml-using-xmlencoder-decoder/?fbclid=IwAR2tzP9l5_o53ipMA7VcxHy5wFAiZpRADrZLhB9DyH1ASIIx9jynis7VbMk
public class ScheduleDAO implements com.company.ScheduleDAO {

    @Override
    public void saveToFile(Schedule schedule, String fileName)throws FileNotFoundException {

        XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        //https://stackoverflow.com/questions/41373566/localdate-serialization-error
        encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object oldInstance, Encoder out) {
                LocalDate localDate = (LocalDate) oldInstance;
                return new Expression(localDate, LocalDate.class, "of",
                        new Object[]{localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()});
            }
        });

        encoder.writeObject(schedule);
        encoder.close();

    }

    @Override
    public Schedule loadFromFile(String fileName)throws FileNotFoundException {
        XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        Schedule schedule = (Schedule)decoder.readObject();
        decoder.close();
        return schedule;
    }
}
