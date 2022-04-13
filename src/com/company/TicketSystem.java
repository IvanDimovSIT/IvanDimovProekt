package com.company;

import java.util.Scanner;

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

    public void start(){
        ProcessCommand processCommand = new CommandProcessor(schedule, numberOfHalls, rows, seats);
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] command;
        while(true){
            System.out.print('>');
            input = scanner.nextLine();
            if(input.equals("exit"))break;
            command = input.split(" ", 2);
            try {
                processCommand.process(command);
            }catch (CommandException e){
                System.out.println(e.getMessage());
            }
        }

    }

}
