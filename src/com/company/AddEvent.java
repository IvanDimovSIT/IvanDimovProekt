package com.company;

import java.time.LocalDate;

public interface AddEvent {
    public void addEvent(Schedule schedule, LocalDate date, int hallNumber, String name)throws EventsException;
}
