package com.company;

import java.io.FileNotFoundException;

public interface ScheduleDAO {
    public void saveToFile(Schedule schedule, String fileName)throws FileNotFoundException;
    public Schedule loadFromFile(String fileName)throws FileNotFoundException;
}
