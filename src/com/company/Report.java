package com.company;

import java.time.LocalDate;
import java.util.Map;

public interface Report {
    Map<String, Integer> reportSales(Schedule schedule, LocalDate from, LocalDate to, Integer hallNumber)throws CommandException;
}
