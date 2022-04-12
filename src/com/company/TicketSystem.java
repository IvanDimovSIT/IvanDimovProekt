package com.company;

public class TicketSystem {
    private static TicketSystem instance;
    private static final int numberOfHalls = 4;
    private static final int[] rows = {5, 6, 8, 9};
    private static final int[] seats = {12, 10, 14, 19};
    private Schedule schedule;
    private TicketSystem(){
        schedule = new Schedule(TicketSystem.numberOfHalls, TicketSystem.rows, TicketSystem.seats);
    }

    public static TicketSystem getInstance(){
        if(instance==null)
            instance = new TicketSystem();
        return instance;
    }

}
