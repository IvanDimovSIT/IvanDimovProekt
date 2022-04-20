package com.company;

public class HallsDay {
    private Hall[] halls;
    private static int[] hallRows;
    private static int[] hallSeats;

    public HallsDay(){}

    public HallsDay(int numberOfHalls) {
        halls = new Hall[numberOfHalls];
        for (int i=0; i<numberOfHalls; i++) {
            halls[i] = null;
        }
    }

    public static int[] getHallRows() {
        return hallRows;
    }

    public static void setHallRows(int[] hallRows) {
        HallsDay.hallRows = hallRows;
    }

    public static int[] getHallSeats() {
        return hallSeats;
    }

    public static void setHallSeats(int[] hallSeats) {
        HallsDay.hallSeats = hallSeats;
    }

    public Hall getHall(int hallNum){
        return halls[hallNum];
    }

    public void setHalls(Hall hall, int hallNum){
        halls[hallNum] = hall;
    }


    public Hall[] getHalls() {
        return halls;
    }

    public void setHalls(Hall[] halls) {
        this.halls = halls;
    }

    public void add(int hallNum, String name)throws EventsException{
        if(hallNum>=hallRows.length || hallNum<0)
            throw new EventsException("Hall doesn't exit");
        halls[hallNum] = new Hall(name ,hallRows[hallNum] , hallSeats[hallNum]);
    }

    public void remove(int hallNum) throws EventsException{
        if(hallNum>=hallRows.length || hallNum<0)
            throw new EventsException("Hall doesn't exit");
        halls[hallNum] = null;
    }

}
