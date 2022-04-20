package com.company;

public class Hall {
    private String showName;
    private int maxRows;
    private int maxSeats;
    private Seat[][] seats;
    private int numberBought;

    public Hall(){}

    public Hall(String showName, int maxRows, int maxSeats) {
        this.showName = showName;
        this.maxRows = maxRows;
        this.maxSeats = maxSeats;
        seats = new Seat[maxRows][maxSeats];
        for(int row = 0; row<maxRows; row++){
            for(int seat = 0; seat<maxSeats; seat++) {
                seats[row][seat] = new Seat(row, seat);
            }
        }
        numberBought = 0;
    }

    public String getShowName() {
        return showName;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public Seat getSeat(int row, int place)throws SeatException{
        if(row<0 || row>=maxRows || place<0 || place>=maxSeats)
            throw new SeatException("Seat ["+row+" "+place+"] does not exist");
        else
            return seats[row][place];
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }


    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public int getNumberBought() {
        return numberBought;
    }

    public void setNumberBought(int numberBought) {
        this.numberBought = numberBought;
    }
}
