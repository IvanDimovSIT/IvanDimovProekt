package com.company;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private Map<LocalDate, HallsDay> halls;
    private final int numberOfHalls;
    private final int[] hallRows;
    private final int[] hallSeats;


    public Schedule(int numberOfHalls, int[] hallRows, int[] hallSeats) {
        this.numberOfHalls = numberOfHalls;
        this.hallRows = hallRows;
        this.hallSeats = hallSeats;
        halls = new HashMap<>();
    }

    public HallsDay getHallsForDay(LocalDate date){
        return halls.get(date);
    }

    public void addHallsForDay(LocalDate date)throws EventsException{
        if(halls.containsKey(date))
            throw new EventsException("Already initialised");
        else{
            halls.put(date, new HallsDay(numberOfHalls,hallRows, hallSeats));
        }
    }


}
