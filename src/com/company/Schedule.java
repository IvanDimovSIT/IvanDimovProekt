package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private Map<LocalDate, HallsDay> halls;
    private int numberOfHalls;
    private int[] hallRows;
    private int[] hallSeats;

    public Schedule(){}

    public Schedule(int numberOfHalls, int[] hallRows, int[] hallSeats) {
        this.numberOfHalls = numberOfHalls;
        this.hallRows = hallRows;
        this.hallSeats = hallSeats;
        halls = new HashMap<>();
    }

    public void reset(int numberOfHalls, int[] hallRows, int[] hallSeats){
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


    public Map<LocalDate, HallsDay> getHalls() {
        return halls;
    }

    public void setHalls(Map<LocalDate, HallsDay> halls) {
        this.halls = halls;
    }

    public int getNumberOfHalls() {
        return numberOfHalls;
    }

    public int[] getHallRows() {
        return hallRows;
    }

    public int[] getHallSeats() {
        return hallSeats;
    }

    public void setNumberOfHalls(int numberOfHalls) {
        this.numberOfHalls = numberOfHalls;
    }

    public void setHallRows(int[] hallRows) {
        this.hallRows = hallRows;
    }

    public void setHallSeats(int[] hallSeats) {
        this.hallSeats = hallSeats;
    }
}
