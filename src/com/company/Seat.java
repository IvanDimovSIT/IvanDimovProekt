package com.company;

public class Seat {
    private SeatState seatState;
    private int row;
    private int place;
    private String note;

    public Seat(){}

    public Seat(int row, int place) {
        this.row = row;
        this.place = place;
        seatState = SeatState.Free;
        note = "";
    }

    public SeatState getSeatState() {
        return seatState;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public void setSeatState(SeatState seatState) {
        this.seatState = seatState;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "[row:"+(row+1)+" seat:"+(place+1)+"]";
    }


    public void setRow(int row) {
        this.row = row;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
