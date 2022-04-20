package com.company;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandProcessor implements ProcessCommand{
    private Schedule schedule;
    private int numberOfHalls;
    private int[] rows;
    private int[] seats;
    private String lastFile;

    public CommandProcessor(Schedule schedule, int numberOfHalls, int[] rows, int[] seats) {
        this.schedule = schedule;
        this.numberOfHalls = numberOfHalls;
        this.rows = rows;
        this.seats = seats;
        lastFile = null;
    }

    private void saveAs(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        SaveAs saveAs = new Commands.SaveAs();
        String fileName = params[1].replace("\"", "");
        lastFile = fileName;
        saveAs.saveAs(schedule, fileName);
        System.out.println("Successfully saved to "+fileName);
    }

    private void save() throws CommandException{
        SaveAs saveAs = new Commands.SaveAs();
        if(lastFile != null) {
            saveAs.saveAs(schedule, lastFile);
            System.out.println("Successfully saved "+lastFile);
        }
        else
            System.out.println("No file found!");
    }

    private void close(){
        CloseFile closeFile = new Commands.CloseFile();
        closeFile.closeFile(schedule, numberOfHalls, rows, seats);
        lastFile = null;
        System.out.println("File closed");
    }

    private void open(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        OpenFile openFile = new Commands.OpenFile();
        String fileName = params[1].replace("\"", "");
        lastFile = fileName;
        openFile.open(schedule, fileName);
        System.out.println("Successfully opened "+fileName);
    }

    private void addEvent(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        AddEvent addEvent = new Commands.AddEvent();
        String[] params1 = params[1].split(" ", 3);
        if(params1.length!=3)
            throw new CommandException("Incorrect parameters");
        LocalDate date = LocalDate.parse(params1[0]);
        int hall = Integer.parseInt(params1[1]);
        String name = params1[2].replace("\"","");
        addEvent.addEvent(schedule, date, hall-1 , name);

        System.out.println("Event added");
    }

    private void help(){
        System.out.println("The following commands are supported:");
        Help help = new Commands.Help();
        for (String i: help.allCommands()) {
            System.out.println(i);
        }
    }

    private void freeSeats(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 2);
        if(params1.length!=2)
            throw new CommandException("Incorrect parameters");
        FreeSeats freeSeats = new Commands.FreeSeats();
        LocalDate date = LocalDate.parse(params1[0]);
        String name = params1[1].replace("\"", "");
        List<Seat> free;
        try {
            free = freeSeats.listFreeSeats(schedule, date, name);
        } catch (EventsException e) {
            throw new CommandException(e.getMessage());
        }
        SeatsOutput seatsOutput = new Console.SeatsOutput();
        seatsOutput.print(free);
    }

    private void book(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 4);
        if(params1.length != 4)
            throw new CommandException("Incorrect parameters");
        int row = Integer.parseInt(params1[0])-1;
        int seat = Integer.parseInt(params1[1])-1;
        LocalDate date = LocalDate.parse(params1[2]);
        String nameAndNote = params1[3];
        if(nameAndNote.charAt(0) != '\"')
            throw new CommandException("Not using \" \"");
        String[] params2 = nameAndNote.replaceFirst("\"", "").split("\"", 2);
        if(params2.length != 2)
            throw new CommandException("Incorrect parameters");

        String name = params2[0];
        String note = params2[1].trim().replace("\"", "");
        Book book = new Commands.Book();
        book.book(schedule, row, seat, date, name, note);
        System.out.println("Seat Booked");
    }

    private void unbook(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 4);
        if(params1.length != 4)
            throw new CommandException("Incorrect parameters");
        int row = Integer.parseInt(params1[0])-1;
        int seat = Integer.parseInt(params1[1])-1;
        LocalDate date = LocalDate.parse(params1[2]);
        String name = params1[3].replace("\"", "");
        Unbook unbook = new Commands.Unbook();
        unbook.unbook(schedule, row, seat, date, name);
        System.out.println("Seat Unbooked");
    }

    private void buy(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 4);
        if(params1.length != 4)
            throw new CommandException("Incorrect parameters");
        int row = Integer.parseInt(params1[0])-1;
        int seat = Integer.parseInt(params1[1])-1;
        LocalDate date = LocalDate.parse(params1[2]);
        String name = params1[3].replace("\"", "");
        Buy buy = new Commands.Buy();
        String code = buy.buy(schedule, row, seat, date, name);
        System.out.println("Seat code: "+code);
    }

    private void bookings(String[] params)throws CommandException{
        Bookings bookings = new Commands.Bookings();
        SeatsOutput seatsOutput = new Console.SeatsOutput();
        List<Seat> booked;
        LocalDate date = null;
        String name = null;
        if(params.length == 2){
            String[] params1 = params[1].split(" ", 2);
            date = LocalDate.parse(params1[0]);
            if(params1.length==2 && params1[1] != null)
                name = params1[1].replace("\"", "");
        }
        booked = bookings.getBookedSeats(schedule, date, name);
        seatsOutput.print(booked);
    }

    private void check(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        Check checkCode = new Commands.Check();
        Seat seat = checkCode.checkCode(schedule, params[1]);
        if(seat != null) {
            System.out.println("Ticket is valid");
            System.out.println("Seat:"+seat);
        }
        else
            System.out.println("Ticket is NOT valid!");
    }

    private void report(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 3);
        if(params1.length !=2 && params1.length != 3)
            throw new CommandException("Incorrect parameters");
        LocalDate from = LocalDate.parse(params1[0]);
        LocalDate to = LocalDate.parse(params1[1]);
        Integer hallNumber = null;
        if(params1.length == 3)
            hallNumber = Integer.parseInt(params1[2])-1;

        Report report = new Commands.Report();

        Map<String, Integer> sales = report.reportSales(schedule, from, to, hallNumber);
        if(sales.size() == 0)
            System.out.println("No shows!");
        else {
            for (Map.Entry<String, Integer> i: sales.entrySet()) {
                System.out.println("Show:"+i.getKey()+" Sales:"+i.getValue());
            }
        }
    }

    private void top(String[] params) throws CommandException{
        Integer num = null;
        Top top = new Commands.Top();
        if(params.length == 2)
            num = Integer.parseInt(params[1]);
        if(num != null && num<=0)
            throw new CommandException("Invalid parameter");
        List<Map.Entry<String, Integer>> statistics = top.topShows(schedule, num);
        System.out.println("Top "+(num==null?"":num+" ")+"shows:");
        for (Map.Entry<String, Integer> i: statistics) {
            System.out.println("\""+i.getKey() + "\":" + i.getValue()+" sales");
        }
    }

    private void worst(String[] params)throws CommandException{
        final double threshold = 10.0;
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 3);
        if(params1.length !=2)
            throw new CommandException("Incorrect parameters");


        LocalDate from = LocalDate.parse(params1[0]);
        LocalDate to = LocalDate.parse(params1[1]);

        Worst worst = new Commands.Worst(threshold);
        System.out.println("Shows with less than "+threshold+"% of seats bought:");
        for (Map.Entry<String, Double> i: worst.getWorst(schedule, from, to).entrySet()) {
            System.out.println(i.getKey() + ":" + Math.round(i.getValue()) + "%");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Remove(Y/N)?");
        String input;
        do{
            input = scanner.nextLine();
        }while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));

        if(input.equalsIgnoreCase("Y")){
            worst.removeWorst(schedule, from, to);
            System.out.println("Removed");
        }
    }

    @Override
    public void process(String[] command)throws CommandException {
        switch (command[0]){
            case "saveas":
                saveAs(command);
                break;
            case "save":
                save();
                break;
            case "close":
                close();
                break;
            case "open":
                open(command);
                break;
            case "help":
                help();
                break;
            case "addevent":
                addEvent(command);
                break;
            case "freeseats":
                freeSeats(command);
                break;
            case "book":
                book(command);
                break;
            case "unbook":
                unbook(command);
                break;
            case "buy":
                buy(command);
                break;
            case "bookings":
                bookings(command);
                break;
            case "check":
                check(command);
                break;
            case "report":
                report(command);
                break;
            case "top":
                top(command);
                break;
            case "worst":
                worst(command);
                break;
            default:
                throw new CommandException("Command not recognised!");
        }
    }
}
