package com.company;

import java.io.FileNotFoundException;

public interface ScheduleDAO {
    void saveToFile(Schedule schedule, String fileName)throws FileNotFoundException;
    Schedule loadFromFile(String fileName)throws FileNotFoundException;
}
