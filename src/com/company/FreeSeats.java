package com.company;

import java.time.LocalDate;
import java.util.List;

public interface FreeSeats {
    List<Seat> listFreeSeats(Schedule schedule, LocalDate date, String name)throws EventsException;
}
