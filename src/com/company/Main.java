package com.company;

public class Main {

    public static void main(String[] args) {
        //създаване и стартиране на системата за билети
        TicketSystem ticketSystem = TicketSystem.getInstance();
        ticketSystem.start();
    }

}
