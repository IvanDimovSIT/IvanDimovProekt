package com.company;

import java.time.LocalDate;

public interface Unbook {
    void unbook(Schedule schedule, int row, int seat, LocalDate date, String name)throws CommandException;
}
