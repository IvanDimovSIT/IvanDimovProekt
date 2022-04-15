package com.company;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println("file saved");
    }

    private void save() throws CommandException{
        SaveAs saveAs = new Commands.SaveAs();
        if(lastFile != null) {
            saveAs.saveAs(schedule, lastFile);
            System.out.println("file saved");
        }
        else
            System.out.println("No file found!");
    }

    private void close(){
        CloseFile closeFile = new Commands.CloseFile();
        closeFile.closeFile(schedule, numberOfHalls, rows, seats);
        lastFile = null;
        System.out.println("file closed");
    }

    private void open(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        OpenFile openFile = new Commands.OpenFile();
        String fileName = params[1].replace("\"", "");
        lastFile = fileName;
        openFile.open(schedule, fileName);
        System.out.println("file opened");
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
        try {
            addEvent.addEvent(schedule, date, hall-1 , name);
        } catch (EventsException e) {
            throw new CommandException(e.getMessage());
        }
        System.out.println("event added");
    }

    public void help(){
        System.out.println("The following commands are supported:");
        Help help = new Commands.Help();
        for (String i: help.allCommands()) {
            System.out.println(i);
        }
    }

    public void freeSeats(String[] params)throws CommandException{
        if(params.length !=2)
            throw new CommandException("Incorrect parameters");
        String[] params1 = params[1].split(" ", 2);
        if(params.length!=2)
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
        String[] params1 = params[1].split(" ", 5);
        if(params1.length != 5)
            throw new CommandException("Incorrect parameters");
        int row = Integer.parseInt(params1[0])-1;
        int seat = Integer.parseInt(params1[1])-1;
        LocalDate date = LocalDate.parse(params1[2]);
        String name = params1[3];
        String note = params1[4];
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
            default:
                throw new CommandException("Command not recognised!");
        }
    }
}
