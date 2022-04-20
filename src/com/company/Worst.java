package com.company;

import java.time.LocalDate;
import java.util.Map;

public interface Worst {
    Map<String, Double> getWorst(Schedule schedule, LocalDate from, LocalDate to);
    void removeWorst(Schedule schedule, LocalDate from, LocalDate to);
}
