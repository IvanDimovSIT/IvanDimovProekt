package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {

    public static void main(String[] args) {
        /*
        int[] rows = {4, 5};
        int[] seats = {8, 12};

        Schedule schedule = new Schedule(2,rows, seats );
        AddEvent addEvent = new Commands.AddEvent();
        try {
            addEvent.addEvent(schedule, LocalDate.of(2022, 4, 11), 0, "Example1");
            addEvent.addEvent(schedule, LocalDate.of(2022, 4, 11), 1, "Example2");
            addEvent.addEvent(schedule, LocalDate.of(2022, 4, 11), 0, "Example3");
        } catch (EventsException e) {
            e.printStackTrace();
        }
        */
        TicketSystem ticketSystem = TicketSystem.getInstance();
        ticketSystem.start();

    }
}
