package com.company;

import java.time.LocalDate;

public interface Buy {
    String buy(Schedule schedule, int row, int seat, LocalDate date, String name)throws CommandException;
}
