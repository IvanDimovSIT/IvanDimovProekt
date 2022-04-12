package com.company;

public class HallsDay {
    private Hall[] halls;

    public HallsDay(){}

    public HallsDay(int numberOfHalls, int[] hallRows, int[] hallSeats) {
        halls = new Hall[numberOfHalls];
        for (int i=0; i<numberOfHalls; i++) {
            halls[i] = new Hall(null, hallRows[i], hallSeats[i]);
        }
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
}
