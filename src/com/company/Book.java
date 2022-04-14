package com.company;

import java.time.LocalDate;

public interface Book {
    void book(Schedule schedule, int row, int seat, LocalDate date, String name, String note)throws CommandException;
}
