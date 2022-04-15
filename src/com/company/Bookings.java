package com.company;

import java.time.LocalDate;
import java.util.List;

public interface Bookings {
    List<Seat> getBookedSeats(Schedule schedule, LocalDate date, String name)throws CommandException;
}
