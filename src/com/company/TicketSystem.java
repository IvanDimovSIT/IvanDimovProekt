package com.company;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

//клас билетна система
//използван шаблон - singleton
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

    //стартиране на програмата
    public void start(){
        ProcessCommand processCommand = new CommandProcessor(schedule, numberOfHalls, rows, seats);
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] command;
        while(true){
            System.out.print('>');
            input = scanner.nextLine();
            if(input.equals("exit"))
                break;
            command = input.trim().split(" ", 2);

            try {
                processCommand.process(command);
            }catch (CommandException e){
                System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println("Incorrect date formatting!");
            }catch (NumberFormatException e){
                System.out.println("Incorrectly entered number!");
            }
        }
        System.out.println("Exiting the program...");
    }

}

