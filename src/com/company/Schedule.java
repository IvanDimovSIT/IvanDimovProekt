package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//клас разписание на представленията
public class Schedule {
    private Map<LocalDate, HallsDay> halls;
    private int numberOfHalls;
    private static int[] hallRows;
    private static int[] hallSeats;

    public Schedule(){}

    public Schedule(int numberOfHalls, int[] hallRows, int[] hallSeats) {
        this.numberOfHalls = numberOfHalls;
        Schedule.hallRows = hallRows;
        Schedule.hallSeats = hallSeats;
        halls = new HashMap<>();
        HallsDay.setHallRows(hallRows);
        HallsDay.setHallSeats(hallSeats);
    }

    //клас за анулиране на разписанието - използва се при затваряне на файл
    public void reset(int numberOfHalls, int[] hallRows, int[] hallSeats){
        this.numberOfHalls = numberOfHalls;
        Schedule.hallRows = hallRows;
        Schedule.hallSeats = hallSeats;
        halls = new HashMap<>();
    }

    public HallsDay getHallsForDay(LocalDate date){
        return halls.get(date);
    }

    public void addHallsForDay(LocalDate date)throws EventsException{
        if(halls.containsKey(date))
            throw new EventsException("Already initialised");
        else{
            halls.put(date, new HallsDay(numberOfHalls));
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
        Schedule.hallRows = hallRows;
    }

    public void setHallSeats(int[] hallSeats) {
        Schedule.hallSeats = hallSeats;
    }

    private boolean allEmpty(HallsDay hall){
        for (Hall h: hall.getHalls()) {
            if(h != null && h.getShowName() != null)
                return false;
        }
        return true;
    }

    //премахване на записите в датите където няма представления
    public void removeEmpty(){
        Set<LocalDate> toRemove = new HashSet<>();
        for (Map.Entry<LocalDate, HallsDay> i: halls.entrySet()) {
            if(allEmpty(i.getValue())){
               toRemove.add(i.getKey());
            }
        }
        for (LocalDate i: toRemove) {
            halls.remove(i);
        }
    }

}
